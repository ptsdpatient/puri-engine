package desktop;

import assets.ImageData;
import gfx.Renderer;
import engine.Sprite;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLRenderer implements Renderer {

     @Override
    public void clear() {
        glClearColor(0f, 0f, 0f, 1f);
        glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void draw(Sprite sprite) {

        ImageData img = AssetManager.getImage(sprite.texture);

        // TEMP: immediate mode (fine for learning)
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(sprite.x, sprite.y);

        glTexCoord2f(1, 0);
        glVertex2f(sprite.x + sprite.width, sprite.y);

        glTexCoord2f(1, 1);
        glVertex2f(sprite.x + sprite.width, sprite.y + sprite.height);

        glTexCoord2f(0, 1);
        glVertex2f(sprite.x, sprite.y + sprite.height);

        glEnd();
    }
}
