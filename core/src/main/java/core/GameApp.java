package core;

import engine.Sprite;
import gfx.Renderer;

public class GameApp {

    private final Renderer renderer;
    Sprite ship;

    public GameApp(Renderer renderer) {
        this.renderer = renderer;
        this.ship = new Sprite("ship");

    }

    public void update(float delta) {
    }

    public void render() {
        renderer.clear();
        renderer.draw(this.ship);
    }
}
