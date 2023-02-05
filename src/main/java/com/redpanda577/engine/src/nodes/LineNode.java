package com.redpanda577.engine.src.nodes;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import com.redpanda577.engine.src.data.Vertex;
import com.redpanda577.engine.src.rendering.CameraNode;
import com.redpanda577.engine.src.rendering.Mesh;
import com.redpanda577.engine.src.rendering.renderers.Renderer;

public class LineNode extends MeshNode {
    public Vector2f start;
    public Vector2f end;

    public LineNode(Vector2f start, Vector2f end){
        super(null);

        generate(start, end);

        tint = new Vector4f(1, 0, 0, 1);
    }

    public void generate(Vector2f start, Vector2f end){
        this.start = start;
        this.end = end;

        Mesh result = new Mesh();
        Vertex[] vertices = new Vertex[]{
            new Vertex(new Vector3f(start, 0.0f)),
            new Vertex(new Vector3f(end, 0.0f))
        };
        result.setVertices(vertices);
        int[] indices = new int[]{
            0, 1
        };
        result.setIndices(indices);
        setMesh(result);
    }

    @Override
    public void render(CameraNode cam){
        shader.bind();
        shader.setMatrix4f("projection", cam.getProjection());
        shader.setMatrix4f("world", transform.recalculate());
        shader.setMatrix4f("view", cam.getView());

        shader.setBoolean("useTex", false);
        shader.setVector4f("color", tint);

        mesh.bind(0, 1, 2);

        GL11.glDrawElements(GL11.GL_LINES, 
            mesh.getIndices().length, 
            GL11.GL_UNSIGNED_INT, 
            0);
        
        mesh.unbind(0, 1, 2);
        shader.unbind();
    }
}
