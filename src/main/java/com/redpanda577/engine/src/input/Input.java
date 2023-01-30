package com.redpanda577.engine.src.input;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector4d;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Input {
    public static final int KEY_SPACE = 32;
    public static final int KEY_ESCAPE = 256;

    public static final int MB_LEFT = 0;
    public static final int MB_MIDDLE = 1;
    public static final int MB_RIGHT = 2;

    public static boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static int lastKeyPressed = -1;
    public static boolean[] mouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private static int lastMouseButtonPressed = -1;
    public static Vector4d mousePos;
    public static Vector4d scroll;

    public GLFWKeyCallback keyboard;
    public GLFWCursorPosCallback mousePosition;
    public GLFWMouseButtonCallback mouseButton;
    public GLFWScrollCallback mouseScroll;

    public Input(){
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW_RELEASE);
                if(lastKeyPressed != -1){
                    if(!keys[lastKeyPressed]){
                        lastKeyPressed = -1;
                    }
                }
            }
        };

        mousePosition = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mousePos = new Vector4d(xpos, ypos, 0, 0);
            }
        };

        mouseButton = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                mouseButtons[button] = (action != GLFW_RELEASE);
                if(lastMouseButtonPressed != -1){
                    if(!mouseButtons[lastMouseButtonPressed]){
                        lastMouseButtonPressed = -1;
                    }
                }
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scroll.x += xoffset;
                scroll.y += yoffset;
            }
        };
    }

    public static boolean keyDown(int key){
        if(key != lastKeyPressed && keys[key]){
            lastKeyPressed = key;
            return true;
        }
        else return false;
    }

    public static boolean mouseButtonDown(int button){
        if(button != lastMouseButtonPressed && mouseButtons[button]){
            lastMouseButtonPressed = button;
            return true;
        }
        else return false;
    }
}
