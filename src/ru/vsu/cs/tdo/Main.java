package Program;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int[][] array = ArrayUtils.readIntArray2FromFile("src/Program/unsorted dots");
        Dot[] dots = Dot.intMatrixToDotArray(array);

        Dot[] defaultSortDots = Arrays.copyOf(dots, dots.length);
        Arrays.sort(defaultSortDots, new Comparator<Dot>() {
            public int compare(Dot o1, Dot o2) {
                double d1 = o1.getDistance();
                double d2 = o2.getDistance();

                return (int) Math.round(d1 - d2);
            }
        });
        int[][] defaultSortInt = Dot.dotArrayToIntMatrix(defaultSortDots);
        ArrayUtils.writeArrayToFile("src/Program/defaultSorted dots", defaultSortInt);

        Dot[] heapSortDots = Arrays.copyOf(dots, dots.length);
        heapSort(heapSortDots);
        int[][] heapSortInt = Dot.dotArrayToIntMatrix(heapSortDots);
        ArrayUtils.writeArrayToFile("src/Program/heapSorted dots", heapSortInt);
    }

    /*public static void heapSort(Dot arr[]) {
        int n = arr.length;

        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Один за другим извлекаем элементы из кучи
        for (int i=n-1; i>=0; i--)
        {
            // Перемещаем текущий корень в конец
            Dot temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(arr, i, 0);
        }
    }

    public static void heapify(Dot arr[], int n, int i) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (l < n && arr[l].compareTo(arr[largest]) > 0)
            largest = l;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && arr[r].compareTo(arr[largest]) > 0)
            largest = r;
        // Если самый большой элемент не корень
        if (largest != i) {
            Dot swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largest);
        }
    }*/
    private static int heapSize;

    public static void heapSort(Dot[] a) {

        buildHeap(a);
        while (heapSize > 1) {
            swap(a, 0, heapSize - 1);
            heapSize--;
            heapify(a, 0);
        }
    }

    /**
     * Построение кучи. Поскольку элементы с номерами начиная с a.length / 2 + 1
     * это листья, то нужно переупорядочить поддеревья с корнями в индексах
     * от 0 до a.length / 2 (метод heapify вызывать в порядке убывания индексов)
     *
     * @param a - массив, из которого формируется куча
     */
    private static void buildHeap(Dot[] a) {
        heapSize = a.length;
        for (int i = a.length / 2; i >= 0; i--) {
            heapify(a, i);
        }
    }

    /**
     * Переупорядочивает поддерево кучи начиная с узла i так, чтобы выполнялось
     * основное свойство кучи - a[parent] >= a[child].
     *
     * @param a - массив, из которого сформирована куча
     * @param i - корень поддерева, для которого происходит переупорядочивание
     */
    private static void heapify(Dot[] a, int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heapSize && a[i].compareTo(a[l]) < 0) {
            largest = l;
        }
        if (r < heapSize && a[largest].compareTo(a[r]) < 0) {
            largest = r;
        }
        if (i != largest) {
            swap(a, i, largest);
            heapify(a, largest);
        }
    }

    /**
     * Возвращает индекс правого потомка текущего узла
     *
     * @param i индекс текущего узла кучи
     * @return индекс правого потомка
     */
    private static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Возвращает индекс левого потомка текущего узла
     *
     * @param i индекс текущего узла кучи
     * @return индекс левого потомка
     */
    private static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Меняет местами два элемента в массиве
     *
     * @param a массив
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private static void swap(Dot[] a, int i, int j) {
        Dot temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
/**
 * Дан набор точек (X, Y) на плоскости. Отобрать N (указывается) точек, наиболее близких
 * к началу координат (точке (0, 0)).
 * Для этого необходимо отсортировать точки по критерию близости точки в началу
 * координат, а потом взять N первых точек.
 * Воспользоваться двумя методами сортировки – пирамидальной и встроенной
 * (Arrays.sort).
 */