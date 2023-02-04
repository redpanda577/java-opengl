package com.redpanda577.engine.src.rendering.renderers;

import java.util.ArrayList;
import java.util.List;

import com.redpanda577.engine.src.rendering.Camera;
import com.redpanda577.engine.src.rendering.IRenderable;

public class UIRenderer {
    private List<IRenderable> objects;

    private Camera cam;

    public UIRenderer(Camera cam){
        this.cam = cam;
        objects = new ArrayList<IRenderable>();
    }

    public void addRenderable(IRenderable object){
        objects.add(object);
    }

    public void render(){
        cam.genUIOrthographic();
        for(int i = 0; i < objects.size(); i++){
            IRenderable obj = objects.get(i);
            obj.render(cam);
        }
    }
}
