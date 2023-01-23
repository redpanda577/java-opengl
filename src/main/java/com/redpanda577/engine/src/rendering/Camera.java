package com.redpanda577.engine.src.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    private float width, height;
    private float near, far;
    private Matrix4f projection;
    private Matrix4f view;

    public boolean freeRot = true;
    public Vector3f position;
    public Vector3f rotation;

    public float zoom = 100;

    public Camera(float near, float far){
        width = 16;
        height = 9;
        this.near = near;
        this.far = far;

        genOrthographic();

        position = new Vector3f(0.0f, 0.0f, 0.0f);
        rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    public void updateDims(float width, float height){
        this.width = width / zoom;
        this.height = height / zoom;
    }

    public void genOrthographic(){
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        projection = new Matrix4f().ortho(-halfWidth, halfWidth,
            -halfHeight, halfHeight,
            near, far);
    }

    public void genPerspective(){
        float fov = (float) Math.toRadians(60.0f);
        float aspectRatio = width / height;
        projection.identity();
        projection.perspective(fov, aspectRatio, near, far);
    }

    public Matrix4f getProjection(){
        return projection;
    }

    public void genView(){
        Vector3f cameraPos = position;

        view = new Matrix4f().identity();
        view.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
            .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        view.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
    }

    public Matrix4f getView(){
        return view;
    }
}
