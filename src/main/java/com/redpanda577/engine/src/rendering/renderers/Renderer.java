package com.redpanda577.engine.src.rendering.renderers;

import java.util.ArrayList;
import java.util.List;

import com.redpanda577.engine.src.rendering.CameraNode;
import com.redpanda577.engine.src.rendering.IRenderable;

public class Renderer{
    public List<IRenderable> objects;

    protected CameraNode cam;

    public Renderer(CameraNode cam){
        this.cam = cam;
        objects = new ArrayList<IRenderable>();
    }

    public void addRenderable(IRenderable object){
        objects.add(object);
    }

    public void render(){
        cam.genOrthographic();
        cam.genView();
        for(int i = 0; i < objects.size(); i++){
            IRenderable obj = objects.get(i);
            obj.render(cam);
        }
    }
}
