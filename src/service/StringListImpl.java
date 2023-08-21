package service;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.arraycopy;


public class StringListImpl implements StringListService {

    private String[] items;
    private static final int STRING_MAX_SIZE = 10;
    private int size = 0;

    public StringListImpl(String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                throw new RuntimeException("NULL IS NOT ACCEPTED");
            }
        }
        if (items.length >= STRING_MAX_SIZE) {
            size = STRING_MAX_SIZE;
        } else {
            size = items.length;
        }
        this.items = Arrays.copyOf(items, STRING_MAX_SIZE);
    }

    private void extendArray() {
        String[] buffer = new String[items.length + 1];
        arraycopy(items, 0, buffer, 0, items.length);
        items = buffer;
    }

    private void reduceArray() {
        String[] reduce = new String[items.length - 1];
        arraycopy(items, 0, reduce, 0, reduce.length);
        items = reduce;
    }


    @Override
    public String add(String item) {
        if (item == null) {
            throw new RuntimeException("NULL IS NOT ACCEPTED");
        }
        extendArray();
        items[items.length - 1] = item;

        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index > STRING_MAX_SIZE || index < 0) {
            throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
        }
        if (item == null) {
            throw new RuntimeException("NULL IS NOT ACCEPTED");
        }
        arraycopy(items, index, items, index + 1, items.length - index - 1);
        items[index] = item;

        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index > STRING_MAX_SIZE || index < 0) {
            throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
        }
        if (item == null) {
            throw new RuntimeException("NULL IS NOT ACCEPTED");
        }
        items[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new RuntimeException("NO ITEM WAS FOUND");
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                arraycopy(items, i + 1, items, i, items.length - i - 1);
                reduceArray();
                i--;
            }
        }
        return item;
    }

    @Override
    public String remove(int index) {
        if (index > STRING_MAX_SIZE || index < 0) {
            throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
        }
        var item = items[index];
        arraycopy(items, index + 1, items, index, items.length - index - 1);
        reduceArray();

        return item;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new RuntimeException("NO ITEM WAS FOUND");
        }
        for (String entry : items) {
            if (entry.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new RuntimeException("NO ITEM WAS FOUND");
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new RuntimeException("NO ITEM WAS FOUND");
        }
        for (int i = items.length - 1; i >= 0; i--) {
            if (items[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public String get(int index) {
        for (int i = 0; i < items.length; i++) {
            if (index > STRING_MAX_SIZE) {
                throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
            }
        }
        return items[index];
    }

    @Override
    public boolean equals(StringListService otherList) {
        if (this == otherList) {
            return true;
        }
        if (otherList == null) {
            throw new RuntimeException("NULL IS NOT ACCEPTED");
        }
        if (getClass() != otherList.getClass())
            return false;
        StringListImpl that = (StringListImpl) otherList;
        return size == that.size && Arrays.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public int size() {
        return items.length;
    }

    @Override
    public boolean isEmpty() {
        return items.length == 0;
    }

    @Override
    public void clear() {
        this.items = new String[0];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(items, items.length);
    }
}

