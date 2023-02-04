package com.redpanda577.engine.apps;

import org.joml.Vector4f;

import com.redpanda577.engine.src.Defaults;
import com.redpanda577.engine.src.Window;
import com.redpanda577.engine.src.data.Shapes;
import com.redpanda577.engine.src.data.basics.Texture;
import com.redpanda577.engine.src.data.basics.TextureRegion;
import com.redpanda577.engine.src.input.Input;
import com.redpanda577.engine.src.nodes.MeshNode;
import com.redpanda577.engine.src.nodes.SpriteNode;
import com.redpanda577.engine.src.nodes.UISpriteNode;
import com.redpanda577.engine.src.rendering.Camera;
import com.redpanda577.engine.src.rendering.renderers.Renderer;
import com.redpanda577.engine.src.rendering.renderers.UIRenderer;

public class Game1 {
    Window window = new Window();
    Camera cam = new Camera(0.01f, 1000.0f);
    Camera uicam = new Camera();

    Renderer main = new Renderer(cam);
    UIRenderer ui = new UIRenderer(uicam);

    SpriteNode object;
    MeshNode object2;

    UISpriteNode textObject;

    public Game1() {
        window.init();
        Defaults.load();
    }
    
    public void start(){
        cam.position.z = 15;
        
        object = new SpriteNode(Shapes.rectangle(), main); //Object(Mesh, Renderer);
        object.shader = Defaults.defaultRender; //set shader
        object.texture = new Texture("assets/scribbles.png"); //override the default texture

        object.useTexRegion = true;
        object.texRegion = new TextureRegion(object.texture, 2, 1, 2, 2);
        object.mesh.recalculateUVs();

        object2 = new MeshNode(Shapes.rectangle(3, 1), main);
        object2.shader = Defaults.defaultRender;
        object2.transform.parent = object.transform;
        object2.transform.position.y += 1;

        textObject = Defaults.defaultFont.getStringMesh("YAAAAAS BITCH!", ui);
        textObject.shader = Defaults.defaultUI;
        textObject.tint = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        
        update();
    }
    
    public void update(){
        while(!window.close()){
            if(Input.keys[Input.KEY_ESCAPE]) break;
            
            window.clear(false);
            cam.updateDims(window.width, window.height);
            uicam.updateDims(window.width, window.height);

            main.render();
            ui.render();

            if(Input.keys[Input.KEY_SPACE]) object.transform.rotation += 0.1f;
            
            window.update();
        }
        end();
    }

    public void end(){
        window.end();
    }
}
