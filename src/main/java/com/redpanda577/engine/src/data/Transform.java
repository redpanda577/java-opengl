package com.redpanda577.engine.src.data;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform {
    public Transform parent = null;
    public List<Transform> children;

    public Vector3f position;
    public float rotation;
    public Vector2f scale;

    //used for inheriting from parent
    public Vector3f finalPosition = position;
    float finalRotation = rotation;
    Vector2f finalScale = scale;

    public float width, height;

    private Matrix4f matrix;

    public Transform(){
        children = new ArrayList<Transform>();

        position = new Vector3f(0.0f, 0.0f, 0.0f);
        rotation = 0.0f;
        scale = new Vector2f(1.0f, 1.0f);

        matrix = new Matrix4f();
    }

    public void setDimensions(float width, float height){
        this.width = width;
        this.height = height;
    }
    
    public Matrix4f recalculate(){
        finalPosition = new Vector3f(position);
        finalRotation = 0 + rotation;
        finalScale = new Vector2f(scale);

        if(parent != null){
            parent.recalculate();
            finalPosition.add(parent.finalPosition);
            finalRotation += parent.finalRotation;
            finalScale.mul(parent.finalScale);
        }

        matrix.identity().translate(finalPosition).
            rotateZ((float)Math.toRadians(finalRotation)). //TODO: rotate around the parents origin if it exists.
            scale(new Vector3f(finalScale, 1.0f));

        //System.out.println(matrix.toString());
        return matrix;
    }

    public void setParent(Transform parent){
        this.parent = parent;
        parent.children.add(this);
    }

    public void addChild(Transform child){
        children.add(child);
        child.parent = this;
    }
}
