package com.redpanda577.engine.src.rendering;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.redpanda577.engine.src.nodes.Node;

public class CameraNode extends Node {
    private float width, height;
    private float near, far;
    private Matrix4f projection;
    private Matrix4f view;

    private Vector2f camRot;

    public float zoom = 50;

    public CameraNode(float near, float far){
        width = 16;
        height = 9;
        this.near = near;
        this.far = far;

        genOrthographic();

        transform.position = new Vector3f(0.0f, 0.0f, 0.0f);
        camRot = new Vector2f(0.0f, 0.0f);
    }

    public CameraNode(){
        width = 16;
        height = 9;
        this.near = 0;
        this.far = 1;

        genUIOrthographic();

        transform.position = new Vector3f(0.0f, 0.0f, 0.0f);
        camRot = new Vector2f(0.0f, 0.0f);
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

    public void genUIOrthographic(){
        projection = new Matrix4f().ortho(0, width,
            height, 0,
            -1, 1);
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
        Vector3f cameraPos = transform.position;

        view = new Matrix4f().identity();
        view.rotate((float)Math.toRadians(camRot.x), new Vector3f(1, 0, 0))
            .rotate((float)Math.toRadians(camRot.y), new Vector3f(0, 1, 0))
            .rotate((float)Math.toRadians(transform.rotation), new Vector3f(0, 0, 1));
        view.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
    }

    public Matrix4f getView(){
        return view;
    }
}
