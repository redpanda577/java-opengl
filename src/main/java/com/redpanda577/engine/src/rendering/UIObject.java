package com.redpanda577.engine.src.rendering;

import org.joml.Vector4f;

import com.redpanda577.engine.src.data.Transform;
import com.redpanda577.engine.src.rendering.renderers.UIRenderer;

public class UIObject {
    public Transform transform;
    public Mesh mesh;

    public Vector4f tint = new Vector4f(1, 1, 1, 1);

    public UIObject(Mesh mesh, UIRenderer renderer){
        this.mesh = mesh;
        this.transform = new Transform();
        this.tint = new Vector4f(1, 1, 1, 1);

        renderer.addRenderable(this);
    }
}
