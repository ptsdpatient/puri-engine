// desktop
package desktop;

import gfx.Texture;
import gfx.TextureFactory;
import desktop.DesktopTexture;

public class DesktopTextureFactory implements TextureFactory {

    @Override
    public Texture load(String path) {
        return new DesktopTexture(path);
    }
}
