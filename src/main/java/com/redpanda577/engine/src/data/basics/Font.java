package com.redpanda577.engine.src.data.basics;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.redpanda577.engine.src.data.Vertex;
import com.redpanda577.engine.src.rendering.Mesh;

public class Font {
    private static final float ZPOS = 0.0f;
    private static final int VERTICES_PER_QUAD = 4;
    private static final float SIZE_NORMALIZER = 1;

    private String filePath;
    private int columns, rows;
    private float charWidth;

    private Texture fontTex;

    public Font(String filePath, int columns, int rows){
        this.filePath = filePath;
        this.columns = columns;
        this.rows = rows;
        this.charWidth = 1;

        fontTex = new Texture(this.filePath);
    }
    
    public Font(String filePath, int columns, int rows, float charWidth){
        this.filePath = filePath;
        this.columns = columns;
        this.rows = rows;
        this.charWidth = charWidth;

        fontTex = new Texture(this.filePath);
    }

    public Mesh getStringMesh(String text){
        return renderString(text, charWidth);
    }

    public Mesh getStringMesh(String text, float pt){
        return renderString(text, pt);
    }

    private Mesh renderString(String text, float width){
        Mesh result = new Mesh();

        byte[] chars = text.getBytes(Charset.forName("ISO-8859-1"));
        int numChars = chars.length;

        List<Vertex> vertices = new ArrayList<Vertex>();
        List<Integer> indices = new ArrayList<Integer>();

        float ratio = (float)fontTex.getHeight() / (float)fontTex.getWidth();
        float tileWidth = width * SIZE_NORMALIZER;
        float tileHeight = tileWidth * ratio;

        for(int i=0; i<numChars; i++) {
            byte currChar = chars[i];
            int col = currChar % columns;
            int row = currChar / columns;
        
            // Build a character tile composed by two triangles
        
            // Left Top vertex
            vertices.add(new Vertex(new Vector3f((float)i * tileWidth, 0.0f, ZPOS), 
                new Vector2f((float)col / (float)columns,(float)row / (float)rows)));
            indices.add(i * VERTICES_PER_QUAD);
        
            // Left Bottom vertex
            vertices.add(new Vertex(new Vector3f((float)i * tileWidth, tileHeight, ZPOS), 
                new Vector2f((float)col / (float)columns,(float)(row + 1) / (float)rows)));
            indices.add(i * VERTICES_PER_QUAD + 1);
        
            // Right Bottom vertex
            vertices.add(new Vertex(new Vector3f((float)i * tileWidth + tileWidth, tileHeight, ZPOS), 
                new Vector2f((float)(col + 1) / (float)columns,(float)(row + 1) / (float)rows)));
            indices.add(i * VERTICES_PER_QUAD + 2);
        
            // Right Top vertex
            vertices.add(new Vertex(new Vector3f((float)i * tileWidth + tileWidth, 0.0f, ZPOS), 
                new Vector2f((float)(col + 1) / (float)columns,(float)row / (float)rows)));
            indices.add(i * VERTICES_PER_QUAD + 3);
        
            // Add indices por left top and bottom right vertices
            indices.add(i * VERTICES_PER_QUAD);
            indices.add(i * VERTICES_PER_QUAD + 2);
        }

        Vertex[] verticesArray = new Vertex[vertices.size()];
        for(int i = 0; i < vertices.size(); i++)
            verticesArray[i] = vertices.get(i);

        int[] indicesArray = new int[indices.size()];
        for(int i = 0; i < indices.size(); i++)
            indicesArray[i] = indices.get(i);

        result.setVertices(verticesArray);
        result.setIndices(indicesArray);
        result.texture = fontTex;
        return result;
    }
}
