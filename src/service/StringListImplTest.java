package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringListImpl underTest;

    @BeforeEach
    private void beforeEach() {
        underTest = new StringListImpl(new Integer[]{39, 43, 87, 1, 3, 6, 8, 98, 43, 2});
    }


    @Test
    void addItem_itemAdded_returnAddedItem() {
        var result = underTest.add(100);
        assertEquals(100, result);
    }

    @Test
    void addItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.add(12, 101));
    }

    @Test
    void setItem_itemSet_returnSetItem() {
        var result = underTest.set(0, 102);
        assertEquals(102, result);
    }

    @Test
    void setItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.set(13, 103));
    }

    @Test
    void removeItem_itemRemoved_returnRemovedItem() {
        var result = underTest.removeItem(2);
        assertEquals(2, result);
    }

    @Test
    void removeItemUsingIndex_itemRemoved_returnRemovedItem() {
        var result = underTest.removeIndex(0);
        assertEquals(39, result);
    }

    @Test
    void removeItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.removeIndex(50));

    }

    @Test
    void containsItem_itemIsInArray_returnTrue() {
        var result = underTest.contains(98);
        assertEquals(true, result);
    }

    @Test
    void indexOfItem_itemFound_returnItem() {
        var result = underTest.indexOf(43);
        assertEquals(1, result);
    }

    @Test
    void indexOfItem_itemNotFound_returnSignMinus1() {
        var result = underTest.indexOf(654);
        assertEquals(-1, result);
    }

    @Test
    void lastIndexOfItemInArray_lastIndexWasFound_returnLastIndex() {
        var result = underTest.lastIndexOf(2);
        assertEquals(9, result);
    }

    @Test
    void lastIndexOfItemInArray_itemNotFound_returnSighMinus1() {
        var result = underTest.lastIndexOf(321);
        assertEquals(-1, result);
    }

    @Test
    void getItem_itemWasFound_returnItem() {
        var result = underTest.get(3);
        assertEquals(1, result);
    }

    @Test
    void getItem_exception_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.get(90));
    }

    @Test
    void equalsStringListAndOtherList_ArraysAreEquals_returnTrue() {
        assertArrayEquals(new Integer[]
                        {39, 43, 87, 1, 3, 6, 8, 98, 43, 2},
                underTest.toArray());
    }

    @Test
    void equalsStringListAndOtherList_otherListIsNull_thrownException() {
        assertThrows(RuntimeException.class,
                () -> underTest.equals(null));
    }

    @Test
    void sizeOfArray_returnSizeOfArray() {
        var result = underTest.size();
        assertEquals(10, result);
    }

    @Test
    void arrayIsEmpty_returnFalse() {
        underTest.isEmpty();
        assertEquals(false, underTest.isEmpty());
    }

    @Test
    void clearArray_arrayHasNoItems_returnClearArray() {
        underTest.clear();
        assertArrayEquals(new Integer[0], underTest.toArray());
    }

    @Test
    void toMakeNewArray_itemsFromPreviousArrayAreInNewArray_returnNewArray() {
        var result = underTest.toArray();
        assertArrayEquals(new Integer[]{39, 43, 87, 1, 3, 6, 8, 98, 43, 2}, result);
    }
}