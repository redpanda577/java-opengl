package com.redpanda577.engine.src.nodes;

import com.redpanda577.engine.src.data.Transform;
import com.redpanda577.engine.src.functionality.Scene;

public class Node {
    public boolean enabled = true;
    public String name;
    public Transform transform;

    public Scene parentScene;

    public Node(){
        this.transform = new Transform();
    }
    
    public void update(){
        //do something here!
    }

    public void registerToScene(Scene scene){
        scene.registerNode(this);
    }

    public void free(){
        
    }
}
