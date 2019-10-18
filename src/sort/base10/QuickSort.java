package sort.base10;

import sort.AbstractArraySort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序（递归实现）
 */
public class QuickSort extends AbstractArraySort {

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        array = Arrays.copyOf(sourceArray, sourceArray.length);
        durationNanoStart = System.nanoTime();
        durationStart = System.currentTimeMillis();
        int[] sortedArray = quickSort(array, 0, sourceArray.length - 1);
        durationNano = System.nanoTime() - durationNanoStart;
        duration = System.currentTimeMillis() - durationStart;
        return sortedArray;
    }

    public int[] quickSort(int[] arr, int left, int right) {
        // 递归结束条件 left >= right
        if (left < right) {
            // 得到基准元素位置
            int pivotIndex = partition(arr, left, right);
            // 根据基准元素，分成两部分进行递归排序
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
        return arr;
    }

    /**
     * 分治(单边循环法)
     *
     * @param arr   待交换的的数组
     * @param left  起始下标
     * @param right 结束下标
     * @return 排序后的数组
     */
    public int partition(int[] arr, int left, int right) {
        // 设定基准值
        int pivot = (new Random()).nextInt(right) % (right - left + 1) + left;  // 可以数列开头，也可以随机值(new Random()).nextInt(arr.length-1)，但是随机值的话基准元素必须与数列头交换
        swap(arr, left, pivot);
        pivot = left;
        int mark = left;    // 设置一个mark指针指向数列起始位置，表示 小于基准元素的区域边界
        // 从基准元素的下一个位置开始遍历数组
        for (int i = pivot + 1; i <= right; i++) {
            if (arr[i] < arr[pivot]) {  // 遍历到的元素小于基准元素，需做两件事
                mark++; // 1.mark指针右移一位，因为小于基准元素的区域边界增大了1
                swap(arr, mark, i); // 2.让最新遍历到的元素和mark指针所在位置的元素交换位置，因为最新遍历的元素要归属于小于pivot的区域
            }
        }
        // 最后把pivot交换到mark指针所在位置
        swap(arr, pivot, mark);
        return mark;
    }

    /**
     * 分治(双边循环法)
     *
     * @param arr   待交换的的数组
     * @param left  起始下标
     * @param right 结束下标
     * @return 排序后的数组
     */
    public int partitionV2(int[] arr, int left, int right) {
        return left;
    }
}
