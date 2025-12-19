// core
package engine;

import gfx.Texture;

public class Sprite {
    private final Texture texture;
    private float x, y;

    public Sprite(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() { return texture; }
    public float getX() { return x; }
    public float getY() { return y; }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public float getWidth() {
        return 64;
    }
    public float getHeight() {
        return 64;
    }
}
