package base.sort.base10;

import base.sort.AbstractArraySort;
import util.aspects.annotations.RuntimeLogAnnotation;

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
        return quickSort(array);
    }

    @RuntimeLogAnnotation
    private int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        // 递归结束条件 left >= right
        if (left < right) {
            // 得到基准元素位置
            int pivotIndex = randomPartition(arr, left, right);
            // 根据基准元素，分成两部分进行递归排序
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
        return arr;
    }

    private int randomPartition(int[] arr, int left, int right) {
        // 随机设定基准值
        int pivotIndex = (new Random()).nextInt(right) % (right - left + 1) + left;  // 可以数列开头，也可以随机值(new Random()).nextInt(arr.length-1)，但是随机值的话基准元素必须与数列头交换
        // 把基准值元素交换到序列头部，完成分治后再换回去
        swap(arr, left, pivotIndex);
        return partitionV2(arr, left, right);
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
        // 设定基准值，由于前面已经随机并且交换到序列头部，因此此处只需要选取头部位置即可
        int pivotIndex = left;
        int mark = left;    // 设置一个mark指针指向数列起始位置，表示 小于基准元素的区域边界
        // 从基准元素的下一个位置开始遍历数组
        for (int i = pivotIndex + 1; i <= right; i++) {
            if (arr[i] < arr[pivotIndex]) {  // 遍历到的元素小于基准元素，需做两件事
                mark++; // 1.mark指针右移一位，因为小于基准元素的区域边界增大了1
                swap(arr, mark, i); // 2.让最新遍历到的元素和mark指针所在位置的元素交换位置，因为最新遍历的元素要归属于小于pivot的区域
            }
        }
        // 最后把pivot交换到mark指针所在位置
        swap(arr, pivotIndex, mark);
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
    private int partitionV2(int[] arr, int left, int right) {
        // 设定基准值，由于前面已经随机并且交换到序列头部，因此此处只需要选取头部位置即可
        int pivotIndex = left;
        int start = pivotIndex + 1;  // 因为基准值元素是有序的，所以不需要比较
        int end = right;
        while (true) {
            // 从左往右扫描，找到第一个大于等于 基准值(pivot) 的元素位置
            while (start <= end && arr[start] <= arr[pivotIndex]) start++;
            // 从右往左扫描，找到第一个小于等于 基准值(pivot) 的元素位置
            while (start <= end && arr[end] >= arr[pivotIndex]) end--;
            // 左右指针相遇
            if (start >= end) break;
            // 交换左右数据，使得左边的元素不大于pivot，右边的不小于pivot
            swap(arr, start, end);
        }
        // 将基准值元素插入序列
        swap(arr, pivotIndex, end);
        return end;
    }
}
