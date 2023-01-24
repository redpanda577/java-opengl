package com.redpanda577.engine.apps;

import com.redpanda577.engine.src.Defaults;
import com.redpanda577.engine.src.Window;
import com.redpanda577.engine.src.rendering.Camera;

public class Game1 {
    Window window = new Window();
    Camera cam = new Camera(0.01f, 1000.0f);
    
    public Game1() {
        window.init();
        Defaults.load();
    }
    
    public void start(){
        cam.position.z = 15;
        
        update();
    }

    public void update(){
        while(!window.close()){
            window.clear(false);
            cam.updateDims(window.width, window.height);

            window.update();
        }
        end();
    }

    public void end(){
        window.end();
    }
}
