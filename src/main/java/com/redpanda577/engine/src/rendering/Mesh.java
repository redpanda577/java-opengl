package com.redpanda577.engine.src.rendering;

import com.redpanda577.engine.src.data.Vertex;
import com.redpanda577.engine.src.data.basics.Texture;
import com.redpanda577.engine.src.data.basics.Vao;

public class Mesh{
    public static final String defaultTex = "defaults/white.png";
    private Vertex[] vertices;

    private float[] positions;
    private float[] uvs;
    private float[] normals;
    private int[] indices;

    public Shader shader;
    public Texture texture;

    private Vao data;

    public Mesh(){
        data = Vao.create();
        texture = new Texture(defaultTex, false);
    }

    public Mesh(String texturePath){
        data = Vao.create();
        texture = new Texture(texturePath, false);
    }

    public Mesh(String texturePath, boolean mipmaps){
        data = Vao.create();
        texture = new Texture(texturePath, mipmaps);
    }

    public Mesh(Texture tex){
        data = Vao.create();
        texture = tex;
    }

    public void setVertices(Vertex[] nVertices){
        vertices = nVertices;

        positions = new float[vertices.length * 3];
        uvs = new float[vertices.length * 2];
        normals = new float[vertices.length * 3];

        for(int i = 0; i < vertices.length; i++){
            Vertex current = vertices[i];

            positions[i * 3 + 0] = current.getPosition().x;
            positions[i * 3 + 1] = current.getPosition().y;
            positions[i * 3 + 2] = current.getPosition().z;

            uvs[i * 2 + 0] = current.getUv().x;
            uvs[i * 2 + 1] = current.getUv().y;

            normals[i * 3 + 0] = current.getNormal().x;
            normals[i * 3 + 1] = current.getNormal().y;
            normals[i * 3 + 2] = current.getNormal().z;
        }

        data.bind();
        data.createAttribute(0, positions, 3);
        data.createAttribute(1, uvs, 2);
        data.createAttribute(2, normals, 3);
        data.unbind();
    }

    public void setIndices(int[] nIndices){
        indices = nIndices;

        data.bind();
        data.createIndexBuffer(indices);
        data.unbind();
    }

    public int[] getIndices(){
        return indices;
    }

    public void bind(int... attributes){
        data.bind(attributes);
    }

    public void unbind(int... attributes){
        data.unbind(attributes);
    }
}

