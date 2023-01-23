package com.redpanda577.engine.src.rendering.renderers;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import com.redpanda577.engine.src.rendering.Mesh;
import com.redpanda577.engine.src.rendering.UIObject;

public class UIRenderer {
    private float width, height;
    private List<UIObject> objects;

    public UIRenderer(float width, float height){
        updateDims(width, height);
        objects = new ArrayList<UIObject>();
    }

    public void addRenderable(UIObject object){
        objects.add(object);
    }

    public void updateDims(float width, float height){
        this.width = width;
        this.height = height;
    }

    public void render(){
        for(int i = 0; i < objects.size(); i++){
            UIObject obj = objects.get(i);
            Mesh active = obj.mesh;

            active.texture.bind();

            active.shader.bind();
            active.shader.setMatrix4f("projection", new Matrix4f().ortho(0, width, height, 0, -1, 1));
            active.shader.setMatrix4f("model", obj.transform.recalculate());

            active.shader.setVector4f("color", obj.tint);

            active.bind(0, 1, 2);

            GL11.glDrawElements(GL11.GL_TRIANGLES, 
                active.getIndices().length, 
                GL11.GL_UNSIGNED_INT, 
                0);
            
            active.unbind(0, 1, 2);
            active.shader.unbind();
            active.texture.unbind();
        }
    }
}
