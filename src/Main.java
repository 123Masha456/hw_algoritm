
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> randomList() {
        return new Random().ints(0, 200000)
                .distinct().limit(100000).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        sortBubble();
        sortSelection();
        sortInsertion();
        sortBubbleTime();
        sortSelectionTime();
        sortInsertionTime();

    }

    public static void sortBubble() {
        List<Integer> first = randomList();
        for (int i = 0; i < first.size() - 1; i++) {
            for (int j = 0; j < first.size() - 1 - i; j++) {
                if (first.get(j) > first.get(j + 1)) {
                    Collections.swap(first, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection() {
        List<Integer> first = randomList();
        for (int i = 0; i < first.size() - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < first.size(); j++) {
                if (first.get(j) < first.get(minElementIndex)) {
                    minElementIndex = j;
                }
            }
            Collections.swap(first, i, minElementIndex);
        }
    }

    public static void sortInsertion() {
        List<Integer> first = randomList();
        for (int i = 1; i < first.size(); i++) {
            int temp = first.get(i);
            int j = i;
            while (j > 0 && first.get(j - 1) >= temp) {
                Collections.swap(first, j, j - 1);
                j--;
            }
            temp = first.get(j);
        }
    }

    public static void sortBubbleTime() {
        System.out.println("TIME 1");
        long start = System.currentTimeMillis();
        sortBubble();
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortSelectionTime() {
        System.out.println("TIME 2");
        long start = System.currentTimeMillis();
        sortSelection();
        ;
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortInsertionTime() {
        System.out.println("TIME 3");
        long start = System.currentTimeMillis();
        sortInsertion();
        System.out.println(System.currentTimeMillis() - start);
    }
}



