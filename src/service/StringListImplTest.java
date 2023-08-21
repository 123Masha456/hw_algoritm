package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringListImpl underTest;

    @BeforeEach
    private void beforeEach() {
        underTest = new StringListImpl(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "e", "j"});
    }


    @Test
    void addItem_itemAdded_returnAddedItem() {
        var result = underTest.add("F");
        assertEquals("F", result);
    }

    @Test
    void addItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.add(null));
        assertThrows(RuntimeException.class, () -> underTest.add(12, "S"));
    }

    @Test
    void setItem_itemSet_returnSetItem() {
        var result = underTest.set(0, "Q");
        assertEquals("Q", result);
    }

    @Test
    void setItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.set(0, null));
        assertThrows(RuntimeException.class, () -> underTest.set(13, "W"));
    }

    @Test
    void removeItem_itemRemoved_returnRemovedItem() {
        var result = underTest.remove("b");
        assertEquals("b", result);
    }

    @Test
    void removeItemUsingIndex_itemRemoved_returnRemovedItem() {
        var result = underTest.remove(0);
        assertEquals("a", result);
    }

    @Test
    void removeItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.remove(null));
        assertThrows(RuntimeException.class, () -> underTest.remove(50));

    }

    @Test
    void containsItem_itemIsInArray_returnTrue() {
        var result = underTest.contains("j");
        assertEquals(true, result);
    }

    @Test
    void containsItem_exceptions_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.contains(null));
    }

    @Test
    void indexOfItem_itemFound_returnItem() {
        var result = underTest.indexOf("a");
        assertEquals(0, result);
    }

    @Test
    void indexOfItem_itemNotFound_returnSignMinus1() {
        var result = underTest.indexOf("P");
        assertEquals(-1, result);
    }

    @Test
    void indexOfItem_exception_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.indexOf(null));
    }

    @Test
    void lastIndexOfItemInArray_lastIndexWasFound_returnLastIndex() {
        var result = underTest.lastIndexOf("j");
        assertEquals(9, result);
    }

    @Test
    void lastIndexOfItemInArray_itemNotFound_returnSighMinus1() {
        var result = underTest.lastIndexOf("y");
        assertEquals(-1, result);
    }

    @Test
    void lastIndexOfItemInArray_exception_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.lastIndexOf(null));
    }

    @Test
    void getItem_itemWasFound_returnItem() {
        var result = underTest.get(2);
        assertEquals("c", result);
    }

    @Test
    void getItem_exception_thrownException() {
        assertThrows(RuntimeException.class, () -> underTest.get(90));
    }

    @Test
    void equalsStringListAndOtherList_ArraysAreEquals_returnTrue() {
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "e", "j"}, underTest.toArray());

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
        assertArrayEquals(new String[0], underTest.toArray());
    }

    @Test
    void toMakeNewArray_itemsFromPreviousArrayAreInNewArray_returnNewArray() {
        var result = underTest.toArray();
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "e", "j"}, result);
    }
}