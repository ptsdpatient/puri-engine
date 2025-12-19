package core;

import engine.Sprite;
import gfx.Renderer;
import gfx.TextureFactory;

public class GameApp {
    Renderer renderer;
    Sprite ship;
    private TextureFactory textures;


    public GameApp(Renderer renderer, TextureFactory textures) {
        this.renderer = renderer;
                this.textures = textures;
            createGame();
    }

    private void createGame() {
        this.ship = new Sprite(textures.load("sprites/ship.png"));
        this.ship.setPosition(100, 100);
    }

    public void update(float delta) {
    }

    public void render() {
        renderer.clear();
        renderer.draw(this.ship);
    }
}
