package engine;

public class Sprite {
    public String texture;
    public float x = 0, y = 0;
    public float rotation = 0;
    public float width = 64, height = 64;

    public Sprite(String texture) {
        this.texture = texture;
        this.rotation = 0f;
    }

    public Sprite(String texture, float x, float y, float w, float h) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.rotation = 0f;
    }

    public void translate(float dx, float dy) {
        x += dx;
        y += dy;
    }

    public void rotate(float radians) {
        rotation += radians;
    }

    public void setRotation(float radians) {
        rotation = radians;
    }
}
