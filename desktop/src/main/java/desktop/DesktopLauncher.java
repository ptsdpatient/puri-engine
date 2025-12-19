package desktop;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import core.GameApp;
import core.screen.GameScreen;
import core.Application;
import core.screen.Screen;
import gfx.Renderer;
import gfx.TextureFactory;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DesktopLauncher {

    private long window;
    Application app;
    Renderer renderer;
    TextureFactory textureFactory;

    public static void main(String[] args) {
        new DesktopLauncher().run();
    }

    public void run() {
        init();

        this.renderer = new LWJGLRenderer();
        this.textureFactory = new DesktopTextureFactory();
        this.app = new Application(this.textureFactory);
        
        loop(app);
        cleanup();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(800, 600, "LWJGL Desktop", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create GLFW window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glViewport(0, 0, 800, 600);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }


    private void loop(Application game) {
        while (!glfwWindowShouldClose(window)) {
            game.update(0.016f);  
            game.render(this.renderer);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void cleanup() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
