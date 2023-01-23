package com.redpanda577.engine.src.data;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform {
    public Transform parent = null;

    public Vector3f position;
    public float rotation;
    public Vector2f scale;

    public float width, height;

    private Matrix4f matrix;

    public Transform(){
        position = new Vector3f(0.0f, 0.0f, 0.0f);
        rotation = 0.0f;
        scale = new Vector2f(1.0f, 1.0f);

        matrix = new Matrix4f();
    }
    
    public Matrix4f recalculate(){
        matrix.identity().translate(position).
            rotateZ((float)Math.toRadians(rotation)).
            scale(new Vector3f(scale, 1.0f));

        if(parent != null)
            matrix.mul(parent.recalculate(), matrix);

        //System.out.println(matrix.toString());
        return matrix;
    }
}
