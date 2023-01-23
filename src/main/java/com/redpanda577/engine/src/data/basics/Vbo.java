package com.redpanda577.engine.src.data.basics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public class Vbo{
    private final int id;
    private final int type;

    public Vbo(int id, int type){
        this.id = id;
        this.type = type;
    }

    public static Vbo create(int type){
        int id = GL15.glGenBuffers();
        return new Vbo(id, type);
    }

    public void bind(){
        GL15.glBindBuffer(type, id);
    }

    public void unbind(){
        GL15.glBindBuffer(type, 0);
    }

    public void storeData(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		storeData(buffer);
	}

	public void storeData(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		storeData(buffer);
	}
	
	public void storeData(IntBuffer data){
		GL15.glBufferData(type, data, GL15.GL_STATIC_DRAW);
	}
	
	public void storeData(FloatBuffer data){
		GL15.glBufferData(type, data, GL15.GL_STATIC_DRAW);
	}

    public void delete(){
		GL15.glDeleteBuffers(id);
	}
}
