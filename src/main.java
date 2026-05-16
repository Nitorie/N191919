import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
public class main {

    public static void bubbleSort(int[] array, boolean ascending) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (ascending) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                } else {
                    if (array[j] < array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int size = 0;
        int max = 0;
        int min = 0;

        try {
            System.out.print("Введіть розмір: ");
            size = scanner.nextInt();

            try {
                System.out.print("Введіть мінімум: ");
                min = scanner.nextInt();

                System.out.print("Введіть максимум: ");
                max = scanner.nextInt();

                if (min > max) {
                    throw new IllegalArgumentException("min не може бути більше max");
                }


            } catch (Exception e) {
                System.out.println("Помилка при введенні min/max");
                return;
            }

        } catch (Exception e) {
            System.out.println("Помилка при введенні розміру масиву");
            return;
        }

        int choice;

        try {
            System.out.print("Оберіть спосіб сортування (1 - за зростанням, 2 - за спаданням): ");
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Некоректний ввід");
            return;
        }

        if (choice != 1 && choice != 2) {
            System.out.println("Введіть 1 або 2");
            return;
        }

        boolean ascending = choice == 1;

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        System.out.println("Масив до сортування:");
        System.out.println(Arrays.toString(array));

        LocalTime startTime = LocalTime.now();

        bubbleSort(array, ascending);

        LocalTime endTime = LocalTime.now();

        Duration duration = Duration.between(startTime, endTime);

        long milliseconds = duration.toMillis();
        long nanoseconds = duration.toNanos();

        System.out.println("Масив після сортування:");
        System.out.println(Arrays.toString(array));

        System.out.println("Було відсортовано " + size + " елементів за "
                + milliseconds + " мс (" + nanoseconds + " нс).");
    }
}
