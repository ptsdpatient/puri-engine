package engine;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array<T> implements Iterable<T> {

    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public Array() {
        items = (T[]) new Object[16];
        size = 0;
    }

    public void add(T item) {
        ensureCapacity(size + 1);
        items[size++] = item;
    }

    public T get(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        return items[index];
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            items[i] = null;
        size = 0;
    }

    private void ensureCapacity(int required) {
        if (required > items.length) {
            int newCapacity = Math.max(items.length * 2, required);
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(items, 0, newArray, 0, size);
            items = newArray;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return items[index++];
            }
        };
    }
}
