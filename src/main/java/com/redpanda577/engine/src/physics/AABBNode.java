package com.redpanda577.engine.src.physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.redpanda577.engine.src.Defaults;
import com.redpanda577.engine.src.nodes.LineNode;
import com.redpanda577.engine.src.nodes.Node;

public class AABBNode extends Node{
    public Vector2f min;
    public Vector2f max;

    public float offsetX = -0.5f;
    public float offsetY = -0.5f;

    private boolean useVisual = false;
    private LineNode line1, line2, line3, line4;

    public void recalculate(){
        min = new Vector2f(transform.parent.position.x + offsetX, transform.parent.position.y + offsetY);
        max = new Vector2f(transform.parent.position.x + transform.parent.width + offsetX, transform.parent.position.y + transform.parent.height + offsetY);
        if(useVisual) updateVisual();
    }
    
    public void recalculate(Vector3f position, float width, float height){
        min = new Vector2f(position.x + offsetX, position.y + offsetY);
        max = new Vector2f(position.x + width + offsetX, position.y + height + offsetY);
        if(useVisual) updateVisual();
    }

    public void genVisual(){
        Vector2f zero = new Vector2f(0, 0);
        line1 = new LineNode(zero, zero, parentScene.primary);
        line1.shader = Defaults.defaultRender;

        line2 = new LineNode(zero, zero, parentScene.primary);
        line2.shader = Defaults.defaultRender;

        line3 = new LineNode(zero, zero, parentScene.primary);
        line3.shader = Defaults.defaultRender;

        line4 = new LineNode(zero, zero, parentScene.primary);
        line4.shader = Defaults.defaultRender;

        useVisual = true;
    }

    public void updateVisual(){
        line1.generate(min, new Vector2f(max.x, min.y));
        line2.generate(new Vector2f(max.x, min.y), max);
        line3.generate(max, new Vector2f(min.x, max.y));
        line4.generate(new Vector2f(min.x, max.y), min);
    }

    public boolean vsAABBNode(AABBNode other){
        if(max.x < other.min.x || min.x > other.max.x) return false;
        if(max.y < other.min.y || min.y > other.max.y) return false;

        return true;
    }
}
