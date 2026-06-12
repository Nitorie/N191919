import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int size;
        int min;
        int max;

        try {
            System.out.print("Введіть розмір масиву: ");

            size = scanner.nextInt();

            System.out.print("Введіть мінімальне значення: ");
            min = scanner.nextInt();

            System.out.print("Введіть максимальне значення: ");
            max = scanner.nextInt();

            if (min > max) {
                System.out.println("Помилка: мінімум не може бути більшим за максимум.");
                return;
            }
            if (size <= 0) {
                System.out.println("Помилка: довжина массиву не може бути <= 0");
                return;
            }

        } catch (Exception e) {
            System.out.println("Помилка введення даних.");
            return;
        }

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        System.out.println("Початковий масив:");
        System.out.println(Arrays.toString(array));

        int[] insertionArray = Arrays.copyOf(array, array.length);
        int[] selectionArray = Arrays.copyOf(array, array.length);
        int[] mergeArray = Arrays.copyOf(array, array.length);
        int[] countingArray = Arrays.copyOf(array, array.length);
        int[] quickArray = Arrays.copyOf(array, array.length);

        LocalTime startTime = LocalTime.now();
        insertionSort(insertionArray);
        LocalTime endTime = LocalTime.now();
        Duration duration = Duration.between(startTime, endTime);

        System.out.println("\nСортування вставками:");
        System.out.println(Arrays.toString(insertionArray));
        System.out.println("Час: " + duration.toNanos() + " нс");

        startTime = LocalTime.now();
        selectionSort(selectionArray);
        endTime = LocalTime.now();
        duration = Duration.between(startTime, endTime);

        System.out.println("\nСортування вибором:");
        System.out.println(Arrays.toString(selectionArray));
        System.out.println("Час: " + duration.toNanos() + " нс");

        startTime = LocalTime.now();
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        endTime = LocalTime.now();
        duration = Duration.between(startTime, endTime);

        System.out.println("\nСортування злиттям:");
        System.out.println(Arrays.toString(mergeArray));
        System.out.println("Час: " + duration.toNanos() + " нс");

        startTime = LocalTime.now();
        countingSort(countingArray);
        endTime = LocalTime.now();
        duration = Duration.between(startTime, endTime);

        System.out.println("\nСортування підрахунками:");
        System.out.println(Arrays.toString(countingArray));
        System.out.println("Час: " + duration.toNanos() + " нс");

        startTime = LocalTime.now();
        quickSort(quickArray, 0, quickArray.length - 1);
        endTime = LocalTime.now();
        duration = Duration.between(startTime, endTime);

        System.out.println("\nШвидке сортування (Quick Sort):");
        System.out.println(Arrays.toString(quickArray));
        System.out.println("Час: " + duration.toNanos() + " нс");
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void countingSort(int[] array) {
        int min = array[0];
        int max = array[0];

        for (int num : array) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        int[] count = new int[max - min + 1];

        for (int num : array) {
            count[num - min]++;
        }

        int index = 0;

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                array[index++] = i + min;
                count[i]--;
            }
        }
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    public static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;

        return i + 1;
    }
}