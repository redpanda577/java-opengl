package com.redpanda577.engine.src;

import com.redpanda577.engine.src.rendering.Shader;

public class Defaults {
    public static final String PROJECT_LOC = "Users/morgan/Documents/projects/gl java/java-opengl/src/main/java/com/redpanda577/engine/";
    
    public static Shader defaultRender;
    public static Shader defaultUI;

    public static void load(){
        defaultRender = new Shader();
        defaultRender.addShader(Shader.VERTEX, PROJECT_LOC + "defaults/defVertex.glsl");
        defaultRender.addShader(Shader.FRAGMENT, PROJECT_LOC + "defaults/defFragment.glsl");
        defaultRender.completeShader();

        defaultUI = new Shader();
        defaultUI.addShader(Shader.VERTEX, PROJECT_LOC + "assets/uiVertex.glsl");
        defaultUI.addShader(Shader.FRAGMENT, PROJECT_LOC + "assets/uiFragment.glsl");
        defaultUI.completeShader();
    }
}
