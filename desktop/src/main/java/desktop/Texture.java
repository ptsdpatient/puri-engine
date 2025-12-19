package desktop;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private int id;
    private int width;
    private int height;

    public Texture(String path) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (in == null) throw new RuntimeException("Resource not found: " + path);
        initFromStream(in);
    }

    public Texture(InputStream inputStream) {
        initFromStream(inputStream);
    }

    private void initFromStream(InputStream inputStream) {
        ByteBuffer imageBuffer;
        try {
            imageBuffer = ioResourceToByteBuffer(inputStream, 8 * 1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            stbi_set_flip_vertically_on_load(true);
            ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, comp, 4);
            if (image == null) throw new RuntimeException("Failed to load texture");

            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(), h.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glBindTexture(GL_TEXTURE_2D, 0);

            width = w.get(0);
            height = h.get(0);

            stbi_image_free(image);
        }
    }

    public int getId() { return id; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private static ByteBuffer ioResourceToByteBuffer(InputStream source, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
        int bytesRead;
        while ((bytesRead = source.read(buffer, 0, buffer.length)) != -1) {
            if (byteBuffer.remaining() < bytesRead) {
                ByteBuffer newBuffer = ByteBuffer.allocateDirect(byteBuffer.capacity() * 2);
                byteBuffer.flip();
                newBuffer.put(byteBuffer);
                byteBuffer = newBuffer;
            }
            byteBuffer.put(buffer, 0, bytesRead);
        }
        byteBuffer.flip();
        return byteBuffer;
    }
}
