package desktop;

import engine.Sprite;
import gfx.Renderer;
import org.lwjgl.opengl.GL11;

public class LWJGLRenderer implements Renderer {

    @Override
    public void clear() {
        GL11.glClearColor(0.1f, 0.1f, 0.15f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void draw(Sprite sprite) {
        DesktopTexture tex = (DesktopTexture) sprite.getTexture();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getId());

        float x = sprite.getX();
        float y = sprite.getY();
        float w = sprite.getWidth();
        float h = sprite.getHeight();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0); GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0); GL11.glVertex2f(x + w, y);
        GL11.glTexCoord2f(1, 1); GL11.glVertex2f(x + w, y + h);
        GL11.glTexCoord2f(0, 1); GL11.glVertex2f(x, y + h);
        GL11.glEnd();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
