package core;

import core.screen.Screen;
import gfx.Renderer;
import gfx.Texture;
import gfx.TextureFactory;

public class Application {

    private Screen current;
    Screen gameScreen;
    TextureFactory textureFactory;

    public Application(TextureFactory textureFactory) {
        this.textureFactory = textureFactory;
        this.gameScreen = new core.screen.GameScreen(this.textureFactory);
        setScreen(gameScreen);
    }

    public void setScreen(Screen screen) {
        if (current != null) current.hide();
        current = screen;
        if (current != null) current.show();
    }

    public void update(float dt) {
        if (current != null) current.update(dt);
    }

    public void render(Renderer renderer) {
        if (current != null) current.render(renderer);
    }
}
