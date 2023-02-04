package com.redpanda577.engine.src.input;

public class Time {
    private static long lastTime;
    public static float deltaTime;

    public static void tick(){
        long time = System.nanoTime();
        deltaTime = ((time - lastTime) / 1000000) / 1000.0f;
        lastTime = time;
    }
}
