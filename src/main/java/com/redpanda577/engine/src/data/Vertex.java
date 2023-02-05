package com.redpanda577.engine.src.data;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex{
    private Vector3f position;
    private Vector2f uv;
    private Vector3f normal;

    public Vertex(float x, float y, float z){
        position = new Vector3f(x, y, z);
        uv = new Vector2f(0, 0);
        normal = new Vector3f(0, 0, 1);
    }

    public Vertex(Vector3f pos, Vector2f uvs, Vector3f norm){
        position = pos;
        uv = uvs;
        normal = norm;
    }

    public Vertex(Vector3f pos, Vector2f uvs){
        position = pos;
        uv = uvs;
        normal = new Vector3f(0, 0, 1);
    }

    public Vertex(Vector3f pos) {
        position = pos;
        uv = new Vector2f(0, 0);
        normal = new Vector3f(0, 0, 1);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getUv() {
        return uv;
    }

    public void setUv(Vector2f uv) {
        this.uv = uv;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}

