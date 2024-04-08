import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {

        int[] array = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            array[i] = (int) Integer.valueOf(args[i]);
        }
        sortUsingSelectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sortUsingSelectionSort(int[] array) {
        int minIndex;
        int tempVar;
        for (int i = 0, n = array.length - 1; i < n; i++) {
            minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            tempVar = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tempVar;
        }
    }
}
