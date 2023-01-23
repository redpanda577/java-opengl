package com.redpanda577.engine.src.rendering;

import java.nio.FloatBuffer;
import java.util.*;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import com.redpanda577.engine.src.data.basics.ShaderFile;

public class Shader{
    private int programID;
    private List<Integer> shaderIDs;

    public static final int VERTEX = GL20.GL_VERTEX_SHADER;
    public static final int FRAGMENT = GL20.GL_FRAGMENT_SHADER;

    public Shader(){
        programID = GL20.glCreateProgram();
        shaderIDs = new ArrayList<Integer>();
    }

    public void addShader(int type, ShaderFile file){
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, file.getContents());
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader " + file.getPath());
            System.exit(-1);
        }
        GL20.glAttachShader(programID, shaderID);
        shaderIDs.add(shaderID);
    }

    public void addShader(int type, String filePath){
        int shaderID = GL20.glCreateShader(type);
        ShaderFile file = new ShaderFile(filePath);
        GL20.glShaderSource(shaderID, file.getContents());
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader " + file.getPath());
            System.exit(-1);
        }
        GL20.glAttachShader(programID, shaderID);
        shaderIDs.add(shaderID);
    }

    public void completeShader(){
        GL20.glLinkProgram(programID);
        for(int i = 0; i < shaderIDs.size(); i++){
            GL20.glDetachShader(programID, shaderIDs.get(i));
            GL20.glDeleteShader(shaderIDs.get(i));
        }
    }

    public void bind(){
        GL20.glUseProgram(programID);
    }

    public void unbind(){
        GL20.glUseProgram(0);
    }

    public int getUniformLocation(String name){
        return GL20.glGetUniformLocation(programID, name);
    }

    public void setVector4f(String name, Vector4f value){
        GL20.glUniform4f(getUniformLocation(name), 
            value.x, value.y, value.z, value.w);
    }

    public void setMatrix4f(String name, Matrix4f value){
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            GL20.glUniformMatrix4fv(getUniformLocation(name), false, fb);
        }
    }

    public void setSampler2D(String name, int value){
        GL20.glUniform1i(getUniformLocation(name), value);
    }
}
