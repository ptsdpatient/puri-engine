package desktop;

import gfx.TextureLoader;
import desktop.Texture;

public class DesktopTextureLoader implements TextureLoader {
    @Override
    public Object loadTexture(String path) {
        return new Texture(path);
    }
}
