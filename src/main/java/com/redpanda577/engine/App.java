package com.redpanda577.engine;

import com.redpanda577.engine.src.Defaults;
import com.redpanda577.engine.src.Window;
import com.redpanda577.engine.src.data.Shapes;
import com.redpanda577.engine.src.data.basics.Font;
import com.redpanda577.engine.src.data.basics.Texture;
import com.redpanda577.engine.src.input.Input;
import com.redpanda577.engine.src.rendering.Camera;
import com.redpanda577.engine.src.rendering.UIObject;
import com.redpanda577.engine.src.rendering.renderers.Renderer;
import com.redpanda577.engine.src.rendering.renderers.UIRenderer;
import com.redpanda577.engine.src.rendering.Object;

import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Slay.
 *
 */
public class App{
    public static void main(String[] args){
        boolean wireframe = false;

        Window window = new Window();
        window.init();
        Defaults.load();

        Camera cam = new Camera(0.01f, 1000);
        cam.position.z = 15;
        Renderer renderer = new Renderer(cam);
        UIRenderer uiRenderer = new UIRenderer(window.width, window.height);

        Object obj = new Object(Shapes.rectangle(), renderer);
        obj.mesh.texture = new Texture("assets/scribbles.png");
        obj.mesh.shader = Defaults.defaultRender;

        Object child = new Object(Shapes.rectangle(), renderer);
        child.mesh.texture = new Texture("defaults/white.png");
        child.mesh.shader = Defaults.defaultRender;
        child.transform.position.y += 1;
        child.transform.scale = new Vector2f(0.5f, 0.5f);

        child.transform.parent = obj.transform;

        UIObject uiPanel = new UIObject(Shapes.rectangle(90, 25), uiRenderer);
        uiPanel.mesh.texture = new Texture("defaults/white.png");
        uiPanel.mesh.shader = Defaults.defaultUI;

        Font font = new Font("defaults/Consolas.png", 16, 16);
        UIObject uiText = new UIObject(font.getStringMesh("Slayed!", 13), uiRenderer);
        uiText.mesh.shader = Defaults.defaultUI;
        uiText.tint = new Vector4f(0, 0, 0, 1);
        uiText.transform.position.x += 2;

        uiText.transform.parent = uiPanel.transform;

        System.out.println("Slay.");
        
        while(!window.close()){
            window.clear(wireframe);

            cam.updateDims(window.width, window.height);
            uiRenderer.updateDims(window.width, window.height);

            renderer.render();
            uiRenderer.render();

            if(Input.mouseButtons[GLFW_MOUSE_BUTTON_1]){
                System.out.println(Input.mousePos);
            }

            if(Input.keys[GLFW_KEY_ESCAPE]) return;
            if(Input.keys[GLFW_KEY_M]) wireframe = !wireframe;

            window.update();
        }
        window.end();
    }
}
