package base.sort.base10;

import base.sort.AbstractArraySort;
import util.aspects.annotations.RuntimeLogAnnotation;

import java.util.Arrays;

/**
 * Created by reborn on 2019-11-01 22:16
 * <p>
 * <p>
 * 一、冒泡排序
 * <p>
 * 1. 冒泡排序原理 Android 和 iOS 演示：https://juejin.im/post/59488390ac502e5490e9f8b6
 */
public class BubbleSort extends AbstractArraySort {

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        array = Arrays.copyOf(sourceArray, sourceArray.length);
        int[] sortedArray = bubbleSort(array);
        return sortedArray;
    }

    /**
     * 冒泡排序思想：
     * <p>
     * 对相邻的元素进行比较，如果左侧元素比右侧大/小，则交换元素，否则不交换；
     * 每一轮会将最小/最大的元素"浮"到顶端，最终达到完全有序。
     * <p>
     * 两个 for 循环分别有什么作用？
     * 外层：控制排序轮数
     * 内层：控制每一轮里的每一个比较步骤
     * <p>
     * 时间复杂度：O(n^2)
     */
    @RuntimeLogAnnotation
    public int[] bubbleSort(int[] arr) {
        // 外n-1 内n-i-1
        // 在第n-1轮的时候，只剩下一个元素没有位于有序区，但此时所有元素已经有序，所以总共遍历n-1轮
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层用于遍历无序区
            // 每轮排序中:需要比较的元素个数比上一轮少一个
            // 即外层遍历i轮，无序区元素减少i个，所以内层应遍历n-i轮；
            // 又因为j+1可以遍历到最后一个元素，所以只需要遍历n-i-1轮（否则会数组越界），或者看成(n-1)-i来理解
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    // 错误的冒泡排序（这根本不是冒泡= =，诗悦秋招笔试的时候写错了QAQ），这有点像直接选择排序？
    public int[] errorFunction(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        return arr;
    }
}
