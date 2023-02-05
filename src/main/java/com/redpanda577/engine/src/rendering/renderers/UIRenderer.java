package com.redpanda577.engine.src.rendering.renderers;

import com.redpanda577.engine.src.rendering.CameraNode;
import com.redpanda577.engine.src.rendering.IRenderable;

public class UIRenderer extends Renderer {

    public UIRenderer(CameraNode cam) {
        super(cam);
    }

    @Override
    public void render(){
        cam.genUIOrthographic();
        for(int i = 0; i < objects.size(); i++){
            IRenderable obj = objects.get(i);
            obj.render(cam);
        }
    }
}
