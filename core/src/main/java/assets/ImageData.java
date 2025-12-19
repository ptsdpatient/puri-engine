package assets;

import java.nio.ByteBuffer;

public class ImageData {
    public final int width;
    public final int height;
    public final ByteBuffer pixels;

    public ImageData(int width, int height, ByteBuffer pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }
}
