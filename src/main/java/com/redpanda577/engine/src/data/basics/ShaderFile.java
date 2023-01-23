package com.redpanda577.engine.src.data.basics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShaderFile{
    private static final String FILE_SEPARATOR = "/";

    private String path;
    private String contents;

    private BufferedReader reader;

    public ShaderFile(String nPath){
        path = FILE_SEPARATOR + nPath;

        loadContents();
    }

    public String loadContents(){
        StringBuilder shaderSource = new StringBuilder();
        try{
            reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
            contents = shaderSource.toString();
        } catch (IOException e) {
            System.err.println("Could not read file " + path);
            e.printStackTrace();
            System.exit(-1);
        }

        return contents;
    }

    public String getPath(){
        return path;
    }

    public String getContents(){
        return contents;
    }
}

