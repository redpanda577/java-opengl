package com.redpanda577.engine.src.rendering;

import org.joml.Vector4f;

import com.redpanda577.engine.src.data.Transform;
import com.redpanda577.engine.src.rendering.renderers.Renderer;

public class Object {
    public Transform transform;
    public Mesh mesh;

    public Vector4f tint;

    public Object(Mesh mesh, Renderer renderer){
        this.mesh = mesh;
        this.transform = new Transform();
        this.tint = new Vector4f(1, 1, 1, 1);

        renderer.addRenderable(this);
    }
}
