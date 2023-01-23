package com.redpanda577.engine.src.data.basics;

import java.io.IOException;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;;

public class Texture {
    private static final String PATH_BEGINNING = "../../../";
    private String filePath;
    private boolean mipmaps;

    private int textureID;

    private int width, height;

    public Texture(String filePath){
        this.filePath = PATH_BEGINNING + filePath;
        this.mipmaps = false;

        loadTexture();
    }
    
    public Texture(String filePath, boolean mipmaps){
        this.filePath = PATH_BEGINNING + filePath;
        this.mipmaps = mipmaps;

        loadTexture();
    }

    private void loadTexture(){
        PNGDecoder decoder;
        ByteBuffer buf;
        try {
            decoder = new PNGDecoder(Texture.class.getResourceAsStream(this.filePath));
            buf = ByteBuffer.allocateDirect(
            4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
            buf.flip();

            textureID = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            width = decoder.getWidth();
            height = decoder.getHeight();
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(),
                decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);

            if(mipmaps) GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        } catch (IOException e) {
            System.out.println("Failed to load texture " + this.filePath);
            e.printStackTrace();
        }
    }

    public void bind(){
        GL30.glActiveTexture(GL30.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
    }
    
    public void unbind(){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
