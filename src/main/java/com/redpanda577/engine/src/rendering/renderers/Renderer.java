package com.redpanda577.engine.src.rendering.renderers;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.redpanda577.engine.src.rendering.Camera;
import com.redpanda577.engine.src.rendering.Mesh;
import com.redpanda577.engine.src.rendering.Object;

public class Renderer{
    private List<Object> objects;

    private Camera cam;

    public Renderer(Camera cam){
        this.cam = cam;
        objects = new ArrayList<Object>();
    }

    public void addRenderable(Object object){
        objects.add(object);
    }

    public void render(){
        cam.genOrthographic();
        cam.genView();
        for(int i = 0; i < objects.size(); i++){
            Object obj = objects.get(i);
            Mesh active = obj.mesh;

            active.texture.bind();

            active.shader.bind();
            active.shader.setMatrix4f("projection", cam.getProjection());
            active.shader.setMatrix4f("world", obj.transform.recalculate());
            active.shader.setMatrix4f("view", cam.getView());

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
