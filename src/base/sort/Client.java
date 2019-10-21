package base.sort;

import base.sort.base10.QuickSort;

public class Client {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 2, 6, 0};
        AbstractArraySort arraySort = new QuickSort();
        arraySort.sort(arr);
        arraySort.print();
    }
}
