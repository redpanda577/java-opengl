package com.redpanda577.engine.src.data;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.redpanda577.engine.src.rendering.Mesh;

public class Shapes {
    public static Mesh rectangle(){
        Mesh result = new Mesh();
        Vertex[] vertices = new Vertex[]{
            new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), 
                new Vector2f(1.0f, 1.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), 
                new Vector2f(1.0f, 0.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), 
                new Vector2f(0.0f, 0.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), 
                new Vector2f(0.0f, 1.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)) 
        };
        result.setVertices(vertices);
        int[] indices = new int[]{
            0, 1, 3,
            1, 2, 3 
        };
        result.setIndices(indices);
        return result;
    }

    public static Mesh rectangle(float width, float height){
        Mesh result = new Mesh();
        Vertex[] vertices = new Vertex[]{
            new Vertex(new Vector3f(width, 0.0f, 0.0f), 
                new Vector2f(1.0f, 1.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)),
            new Vertex(new Vector3f(width, height, 0.0f), 
                new Vector2f(1.0f, 0.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)),
            new Vertex(new Vector3f(0.0f, height, 0.0f), 
                new Vector2f(0.0f, 0.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)),
            new Vertex(new Vector3f(0.0f, 0.0f, 0.0f), 
                new Vector2f(0.0f, 1.0f), 
                new Vector3f(0.0f, 0.0f, 1.0f)) 
        };
        result.setVertices(vertices);
        int[] indices = new int[]{
            0, 1, 3,
            1, 2, 3 
        };
        result.setIndices(indices);
        return result;
    }
}
