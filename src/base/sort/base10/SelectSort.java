package base.sort.base10;

import base.sort.AbstractArraySort;
import util.aspects.annotations.RuntimeLogAnnotation;

import java.util.Arrays;

/**
 * Created by reborn on 2019-11-01 22:16
 * <p>
 * 二、选择排序
 */
public class SelectSort extends AbstractArraySort {

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        array = Arrays.copyOf(sourceArray, sourceArray.length);
        int[] sortedArray = selectSort(array);
        return sortedArray;
    }

    /**
     * 选择排序思想：
     * 遍历数组，找（选择）最大/最小元素拎出来和无序区的第一个位置交换，如此循环，直到整个数组排序完成。
     *
     * 时间复杂度：O(n^2)
     */
    @RuntimeLogAnnotation
    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            // 由于无序区的第一个元素一定会被比较，所以直接i+1开始即可
            for (int j = i + 1; j < arr.length; j++) {
                min = minIndex(arr, min, j);
            }
            swap(arr, i, min);
        }
        return arr;
    }
}
