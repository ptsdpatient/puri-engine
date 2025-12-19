package core.screen;

import engine.Sprite;
import gfx.Renderer;
import gfx.TextureFactory;

public class GameScreen extends Screen {

    private final TextureFactory textures;
    private Sprite ship;

    public GameScreen(TextureFactory textures) {
        this.textures = textures;
        create();
    }

    private void create() {
        ship = new Sprite(textures.load("sprites/ship.png"));
        ship.setPosition(100, 100);
    }

    @Override
    public void update(float delta) {
        // game logic
    }

    @Override
    public void render(Renderer renderer) {
        renderer.clear();
        renderer.draw(ship);
    }
}
