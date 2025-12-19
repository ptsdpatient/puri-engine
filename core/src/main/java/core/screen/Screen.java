package core.screen;

import gfx.Renderer;

public abstract class Screen {

    public void show() {}       // called when screen becomes active
    public void hide() {}       // called when screen is replaced
    public void resize(int w, int h) {}

    public abstract void update(float delta);
    public abstract void render(Renderer renderer);
}
