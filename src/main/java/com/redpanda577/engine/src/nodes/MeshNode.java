package com.redpanda577.engine.src.nodes;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import com.redpanda577.engine.src.rendering.CameraNode;
import com.redpanda577.engine.src.rendering.IRenderable;
import com.redpanda577.engine.src.rendering.Mesh;
import com.redpanda577.engine.src.rendering.Shader;
import com.redpanda577.engine.src.rendering.renderers.Renderer;

public class MeshNode extends Node implements IRenderable {
    protected Mesh mesh;

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
        if(mesh != null) transform.setDimensions(mesh.areaRect.width, mesh.areaRect.height);
    }

    public Shader shader;

    public Vector4f tint;

    public MeshNode(Mesh mesh, Renderer renderer){
        setMesh(mesh);
        this.tint = new Vector4f(1, 1, 1, 1);

        renderer.addRenderable(this);
    }

    @Override
    public void render(CameraNode cam) {
        shader.bind();
        shader.setMatrix4f("projection", cam.getProjection());
        shader.setMatrix4f("world", transform.recalculate());
        shader.setMatrix4f("view", cam.getView());

        shader.setBoolean("useTex", false);
        shader.setVector4f("color", tint);

        mesh.bind(0, 1, 2);

        GL11.glDrawElements(GL11.GL_TRIANGLES, 
            mesh.getIndices().length, 
            GL11.GL_UNSIGNED_INT, 
            0);
        
        mesh.unbind(0, 1, 2);
        shader.unbind();
    }
}
