package desktop;

import assets.ImageData;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    private static final Map<String, ImageData> images = new HashMap<>();

    public static void load() {
        images.put("ship",
            ImageLoader.load("/textures/ship.png"));
    }

    public static ImageData getImage(String name) {
        return images.get(name);
    }
}
