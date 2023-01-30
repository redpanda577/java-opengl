package com.redpanda577.engine.apps;

import com.redpanda577.engine.src.Defaults;
import com.redpanda577.engine.src.Window;
import com.redpanda577.engine.src.data.Shapes;
import com.redpanda577.engine.src.data.basics.Texture;
import com.redpanda577.engine.src.data.basics.TextureRegion;
import com.redpanda577.engine.src.input.Input;
import com.redpanda577.engine.src.rendering.Camera;
import com.redpanda577.engine.src.rendering.Object;
import com.redpanda577.engine.src.rendering.renderers.Renderer;

public class Game1 {
    Window window = new Window();
    Camera cam = new Camera(0.01f, 1000.0f);

    Renderer main = new Renderer(cam);

    Object object;
    
    public Game1() {
        window.init();
        Defaults.load();
    }
    
    public void start(){
        cam.position.z = 15;
        
        object = new Object(Shapes.rectangle(), main);
        object.mesh.shader = Defaults.defaultRender;
        object.mesh.texture = new Texture("assets/scribbles.png");
        object.mesh.useTexRegion = true;
        object.mesh.texRegion = new TextureRegion(object.mesh.texture, 2, 1, 2, 3);
        object.mesh.recalculateUVs();

        update();
    }

    public void update(){
        while(!window.close()){
            if(Input.keys[Input.KEY_ESCAPE]) break;

            window.clear(false);
            cam.updateDims(window.width, window.height);

            main.render();

            if(Input.keyDown(Input.KEY_SPACE)) System.out.println("Input slay.");
            
            window.update();
        }
        end();
    }

    public void end(){
        window.end();
    }
}
