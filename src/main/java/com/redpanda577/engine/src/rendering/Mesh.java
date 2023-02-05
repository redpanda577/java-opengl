package com.redpanda577.engine.src.rendering;

import com.redpanda577.engine.src.data.Rect;
import com.redpanda577.engine.src.data.Vertex;
import com.redpanda577.engine.src.data.basics.TextureRegion;
import com.redpanda577.engine.src.data.basics.Vao;

public class Mesh{
    public static final String defaultTex = "defaults/white.png";
    private Vertex[] vertices;

    private float[] positions;
    private float[] uvs;
    private float[] normals;
    private int[] indices;

    public Rect areaRect;

    private Vao data;

    public Mesh(){
        data = Vao.create();
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
            
            float uvx = current.getUv().x;
            float uvy = current.getUv().y;

            uvs[i * 2 + 0] = uvx;
            uvs[i * 2 + 1] = uvy;

            normals[i * 3 + 0] = current.getNormal().x;
            normals[i * 3 + 1] = current.getNormal().y;
            normals[i * 3 + 2] = current.getNormal().z;
        }

        float minX = positions[0];
        float maxX = positions[0];

        float minY = positions[1];
        float maxY = positions[1];

        for (int i = 0; i < positions.length; i += 3) {
            if(positions[i] < minX) minX = positions[i];
            if(positions[i] > maxX) maxX = positions[i];

            if(positions[i + 1] < minY) minY = positions[i + 1];
            if(positions[i + 1] > maxY) maxY = positions[i + 1];
        }

        this.areaRect = new Rect(minX, minY, Math.abs(minX - maxX), Math.abs(minY - maxY));
        //System.out.println(minX + ", " + maxX);

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

    public void recalculateUVs(){
        uvs = new float[vertices.length * 2];

        for(int i = 0; i < vertices.length; i++){
            Vertex current = vertices[i];

            float uvx = current.getUv().x;
            float uvy = current.getUv().y;

            uvs[i * 2 + 0] = uvx;
            uvs[i * 2 + 1] = uvy;
        }

        data.bind();
        data.createAttribute(1, uvs, 2);
        data.unbind();
    }

    public void recalculateUVs(TextureRegion texRegion){
        uvs = new float[vertices.length * 2];

        for(int i = 0; i < vertices.length; i++){
            Vertex current = vertices[i];

            float uvx = current.getUv().x;
            float uvy = current.getUv().y;

            uvx *= texRegion.area.width;
            uvx += texRegion.area.x;

            uvy *= texRegion.area.height;
            uvy += texRegion.area.y;

            uvs[i * 2 + 0] = uvx;
            uvs[i * 2 + 1] = uvy;
        }

        data.bind();
        data.createAttribute(1, uvs, 2);
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

