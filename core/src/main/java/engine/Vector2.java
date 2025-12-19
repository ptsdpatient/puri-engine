package engine;

public class Vector2 {
    
    float x = 0;
    float y = 0;
    
    public Vector2() {

    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 get() {
        return new Vector2(x,y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
}

