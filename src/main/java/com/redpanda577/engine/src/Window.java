package com.redpanda577.engine.src;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import com.redpanda577.engine.src.input.Input;
import com.redpanda577.engine.src.input.Time;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window{
    private long window;

	public int width = 300;
	public int height = 300;

	private Input inputCallbacks;

    public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		// Create the window
		window = glfwCreateWindow(width, height, "Slayed.", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		inputCallbacks = new Input();
		glfwSetKeyCallback(window, inputCallbacks.keyboard);
		glfwSetCursorPosCallback(window, inputCallbacks.mousePosition);
		glfwSetMouseButtonCallback(window, inputCallbacks.mouseButton);
		glfwSetScrollCallback(window, inputCallbacks.mouseScroll);
		
		glfwSetWindowCloseCallback(window, new GLFWWindowCloseCallback() {
			@Override
			public void invoke(long window) {
				end();
			}
		});

		glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int w, int h) {
				width = w;
				height = h;
			}
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		GL.createCapabilities();

		// Make the window visible
		glfwShowWindow(window);
	}

    public void clear(boolean wireframe){
        GL.createCapabilities();
		//GL11.glViewport(0, 0, width, height);

		if(!wireframe)
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		else glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void update(){
        glfwPollEvents();
        glfwSwapBuffers(window);

		Time.tick();
    }

    public boolean close(){
        return glfwWindowShouldClose(window);
    }

    public void end(){
		inputCallbacks.keyboard.free();
		inputCallbacks.mousePosition.free();
		inputCallbacks.mouseButton.free();
		inputCallbacks.mouseScroll.free();

        // Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		
		// Terminate GLFW and free the error callback
		glfwSetErrorCallback(null).free();
		glfwSetWindowCloseCallback(window, null).free();
		glfwSetWindowSizeCallback(window, null).free();
		glfwDestroyWindow(window);
		
		glfwTerminate();
		
		Defaults.end();
		System.out.println("Closed correctly.");
    }
}
