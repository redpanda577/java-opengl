package com.redpanda577.engine.src.functionality;

import java.util.ArrayList;
import java.util.List;

import com.redpanda577.engine.src.nodes.Node;
import com.redpanda577.engine.src.rendering.renderers.Renderer;

public class Scene {
    public int nodeCount = 0;
    private List<Node> nodes; 

    private List<Renderer> renderers;
    public Renderer primary;
    public Renderer primaryUI;

    public Scene(){
        nodes = new ArrayList<Node>();
        renderers = new ArrayList<Renderer>();
    }

    public Node registerNode(Node node){
        nodes.add(node);
        nodeCount = nodes.size();
        node.parentScene = this;
        return node;
    }

    public Renderer registerRenderer(Renderer renderer){
        renderers.add(renderer);
        return renderer;
    }

    public void update(){
        for (Node node : nodes) {
            node.update();
        }

        for (Renderer renderer : renderers) {
            renderer.render();
        }
    }
}
