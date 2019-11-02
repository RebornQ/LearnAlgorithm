package base.sort;

import base.sort.base10.BubbleSort;
import base.sort.base10.QuickSort;
import base.sort.base10.SelectSort;

public class Client {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2, 6, 0};
        AbstractArraySort arraySort;

        arraySort = new QuickSort();
        arraySort.sort(arr);
        arraySort.print();

        arr = new int[]{1, 4, 3, 5, 2, 6, 0};
        arraySort = new BubbleSort();
        arraySort.sort(arr);
//        arraySort.print();

        arr = new int[]{1, 4, 3, 5, 2, 6, 0};
        arraySort = new SelectSort();
        arraySort.sort(arr);
//        arraySort.print();
    }
}
