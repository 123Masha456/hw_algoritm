package service;

public interface StringListService {

    Integer add(int item);

    Integer add(int index, int item);

    Integer set(int index, int item);

    Integer removeItem(int item);

    Integer removeIndex(int index);

    boolean contains(Integer item);

    int indexOf(int item);

    int lastIndexOf(int item);

    Integer get(int index);

    boolean equals(StringListService otherList);

    int size();

    boolean isEmpty();

    void clear();

    Integer[] toArray();

    void swapElements(Integer[] first, int i, int minElementIndex);

}