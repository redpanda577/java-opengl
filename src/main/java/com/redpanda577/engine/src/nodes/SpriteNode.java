package com.redpanda577.engine.src.nodes;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import com.redpanda577.engine.src.rendering.CameraNode;
import com.redpanda577.engine.src.rendering.Mesh;
import com.redpanda577.engine.src.rendering.renderers.Renderer;
import com.redpanda577.engine.src.data.basics.Texture;
import com.redpanda577.engine.src.data.basics.TextureRegion;

public class SpriteNode extends MeshNode {
    public Texture texture;

    public boolean useTexRegion = false;
    public TextureRegion texRegion;

    public Vector4f tint;

    public SpriteNode(Mesh mesh, Renderer renderer){
        super(mesh, renderer);
        this.tint = new Vector4f(1, 1, 1, 1);
    }

    @Override
    public void render(CameraNode cam) {
        if(enabled){
            texture.bind();

            if(useTexRegion) mesh.recalculateUVs(texRegion);

            shader.bind();
            shader.setMatrix4f("projection", cam.getProjection());
            shader.setMatrix4f("world", transform.recalculate());
            shader.setMatrix4f("view", cam.getView());

            shader.setBoolean("useTex", true);
            shader.setVector4f("color", tint);

            mesh.bind(0, 1, 2);

            GL11.glDrawElements(GL11.GL_TRIANGLES, 
                mesh.getIndices().length, 
                GL11.GL_UNSIGNED_INT, 
                0);
            
            mesh.unbind(0, 1, 2);
            shader.unbind();
            texture.unbind();
        }
    }
}
