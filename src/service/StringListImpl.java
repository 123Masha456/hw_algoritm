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
            grow();
        }
        arraycopy(items, index, items, index + 1, items.length - index - 1);
        items[index] = item;

        return item;
    }

    @Override
    public Integer set(int index, int item) {
        if (index > INTEGER_MAX_SIZE || index < 0) {
            throw new RuntimeException("ARRAY'S AMOUNT EXCEEDED");
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
            throw new RuntimeException("ARRAY'S AMOUNT EXCEEDED");
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
                throw new RuntimeException("ARRAY'S AMOUNT EXCEEDED");
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

    private void grow() {
        items = Arrays.copyOf(items, size + size / 2);
    }

    private void sort(Integer[] items) {
        quickSort(items, 0, items.length - 1);
    }

    private void quickSort(Integer[] items, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(items, begin, end);

            quickSort(items, begin, partitionIndex - 1);
            quickSort(items, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] items, int a1, int a2) {
        int temp = items[a1];
        items[a1] = items[a2];
        items[a2] = temp;
    }


    private static boolean binarySearch(Integer[] items, Integer item) {
        int min = 0;
        int max = items.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == items[mid]) {
                return true;
            }

            if (item < items[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}


