package desktop;

import assets.ImageData;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.stb.STBImage.*;

public class ImageLoader {

    public static ImageData load(String path) {
        try (InputStream is = ImageLoader.class.getResourceAsStream(path)) {

            if (is == null) {
                throw new RuntimeException("Image not found: " + path);
            }

            byte[] bytes = is.readAllBytes();
            ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
            buffer.put(bytes).flip();

            int[] w = new int[1];
            int[] h = new int[1];
            int[] comp = new int[1];

            stbi_set_flip_vertically_on_load(true);

            ByteBuffer pixels = stbi_load_from_memory(
                    buffer, w, h, comp, 4
            );

            if (pixels == null) {
                throw new RuntimeException("STB load failed: " + stbi_failure_reason());
            }

            return new ImageData(w[0], h[0], pixels);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
