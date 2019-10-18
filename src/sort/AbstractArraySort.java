package sort;

import java.util.Arrays;

public abstract class AbstractArraySort implements IArraySort {

    protected int[] array;

    protected long durationNano, durationNanoStart;

    protected long duration, durationStart;

    @Override
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
        if (duration > 0) {
            System.out.println("Total: " + duration + "ms");
        } else {
            System.out.println("Total: " + durationNano + "ns");
        }
    }
}
