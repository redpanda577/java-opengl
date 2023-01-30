package com.redpanda577.engine.src.data.basics;

import com.redpanda577.engine.src.data.Rect;

public class TextureRegion {
    public Texture tex;
    public Rect area;

    public TextureRegion(Texture tex, float x, float y, float width, float height){
        this.tex = tex;

        float w = tex.getWidth();
        float h = tex.getHeight();
        area = new Rect(x / w, y / h, width / w, height / h);
    }
}
