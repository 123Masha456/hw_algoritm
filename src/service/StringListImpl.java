package service;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.arraycopy;


public class StringListImpl implements StringListService {

    private Integer[] items;
    private static final int INTEGER_MAX_SIZE = 10;
    private int size = 0;

    public StringListImpl(Integer[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                throw new RuntimeException("NULL IS NOT ACCEPTED");
            }
        }
        if (items.length >= INTEGER_MAX_SIZE) {
            size = INTEGER_MAX_SIZE;
        } else {
            size = items.length;
        }
        this.items = Arrays.copyOf(items, INTEGER_MAX_SIZE);
    }

    private void extendArray() {
        Integer[] buffer = new Integer[items.length + 1];
        arraycopy(items, 0, buffer, 0, items.length);
        items = buffer;
    }

    private void reduceArray() {
        Integer[] reduce = new Integer[items.length - 1];
        arraycopy(items, 0, reduce, 0, reduce.length);
        items = reduce;
    }


    @Override
    public Integer add(int item) {
        extendArray();
        items[items.length - 1] = item;

        return item;
    }

    @Override
    public Integer add(int index, int item) {
        if (index > INTEGER_MAX_SIZE || index < 0) {
            throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
        }
        arraycopy(items, index, items, index + 1, items.length - index - 1);
        items[index] = item;

        return item;
    }

    @Override
    public Integer set(int index, int item) {
        if (index > INTEGER_MAX_SIZE || index < 0) {
            throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
        }
        items[index] = item;
        return item;
    }

    @Override
    public Integer removeItem(int item) {
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
    public Integer removeIndex(int index) {
        if (index > INTEGER_MAX_SIZE || index < 0) {
            throw new RuntimeException("EXCEEDED ARRAY'S AMOUNT");
        }
        var item = items[index];
        arraycopy(items, index + 1, items, index, items.length - index - 1);
        reduceArray();

        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);

    }

    @Override
    public int indexOf(int item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = items.length - 1; i >= 0; i--) {
            if (items[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        for (int i = 0; i < items.length; i++) {
            if (index > INTEGER_MAX_SIZE) {
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
        this.items = new Integer[0];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(items, items.length);
    }

    @Override
    public void swapElements(Integer[] first, int i, int minElementIndex) {
    }


    private void sort(Integer[] first) {
        for (int i = 0; i < first.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < first.length; j++) {
                if (first[j] < first[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(first, i, minElementIndex);
        }
    }

    private static boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}


