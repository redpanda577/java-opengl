package com.redpanda577.engine.apps;

import org.joml.Vector3f;
import org.joml.Vector4f;

import com.redpanda577.engine.src.Defaults;
import com.redpanda577.engine.src.Window;
import com.redpanda577.engine.src.data.Shapes;
import com.redpanda577.engine.src.data.basics.Texture;
import com.redpanda577.engine.src.data.basics.TextureRegion;
import com.redpanda577.engine.src.functionality.Scene;
import com.redpanda577.engine.src.input.Input;
import com.redpanda577.engine.src.nodes.SpriteNode;
import com.redpanda577.engine.src.nodes.UISpriteNode;
import com.redpanda577.engine.src.physics.AABBNode;
import com.redpanda577.engine.src.rendering.CameraNode;
import com.redpanda577.engine.src.rendering.renderers.Renderer;
import com.redpanda577.engine.src.rendering.renderers.UIRenderer;

public class Game1 {
    Window window = new Window();
    Scene scene = new Scene();

    CameraNode cam = new CameraNode(0.01f, 1000.0f);
    CameraNode uicam = new CameraNode();

    AABBNode boundingBox;
    AABBNode boundingBox2;

    SpriteNode object2;

    public Game1() {
        window.init();
        Defaults.load();
    }
    
    public void start(){
        scene.registerNode(cam);
        scene.registerNode(uicam);

        scene.primary = scene.registerRenderer(new Renderer(cam));
        scene.primaryUI = scene.registerRenderer(new UIRenderer(uicam));

        cam.transform.position.z = 15;
        
        SpriteNode object = new SpriteNode(Shapes.rectangle(), scene.primary); //Object(Mesh, Renderer);
        scene.registerNode(object);
        object.shader = Defaults.defaultRender; //set shader
        object.texture = new Texture("assets/scribbles.png"); //override the default texture

        object.useTexRegion = true;
        object.texRegion = new TextureRegion(object.texture, 2, 1, 2, 2);
        object.getMesh().recalculateUVs();

        boundingBox = new AABBNode();
        boundingBox.transform.setParent(object.transform);
        scene.registerNode(boundingBox);
        boundingBox.genVisual();
        boundingBox.recalculate();

        object2 = new SpriteNode(Shapes.rectangle(), scene.primary); //Object(Mesh, Renderer);
        scene.registerNode(object2);
        object2.shader = Defaults.defaultRender; //set shader
        object2.texture = new Texture("assets/scribbles.png"); //override the default texture
        object2.transform.position = new Vector3f(0.5f, 3, 0);

        boundingBox2 = new AABBNode();
        boundingBox2.transform.setParent(object2.transform);
        scene.registerNode(boundingBox2);
        boundingBox2.genVisual();
        boundingBox2.recalculate();
        
        UISpriteNode textObject = Defaults.defaultFont.getStringMesh("YAAAAAS BITCH!", scene.primaryUI);
        scene.registerNode(textObject);
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

            //do stuff here!
            if(boundingBox.vsAABBNode(boundingBox2)) System.out.println("Colliding");
            object2.transform.position.y -= 0.01f;
            boundingBox2.recalculate();
            
            scene.update();
            window.update();
        }
        end();
    }

    public void end(){
        window.end();
    }
}
