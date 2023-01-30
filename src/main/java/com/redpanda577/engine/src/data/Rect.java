package com.redpanda577.engine.src.data;

public class Rect {
    public float x, y;
    public float width, height;

    public float top, bottom, left, right;

    public Rect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.top = y;
        this.bottom = y + height;
        this.left = x;
        this.right = x + width;
    }
}
