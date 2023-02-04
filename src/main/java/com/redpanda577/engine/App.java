package com.redpanda577.engine;

import com.redpanda577.engine.apps.Game1;

/**
 * Slay.
 *
 */
public class App{
    public static boolean dev = true;

    public static void main(String[] args){
        //TODO: Find why it prints an error on close
        if(!dev){
            // your game here
        }
        else{
            Game1 game = new Game1();
            game.start();
        }
    }
}
