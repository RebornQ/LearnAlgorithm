package base.sort.base10;

import base.sort.AbstractArraySort;
import util.aspects.annotations.RuntimeLogAnnotation;

import java.util.Arrays;

/**
 * Created by reborn on 2019-11-01 22:44
 *
 * 三、插入排序
 */
public class InsertSort extends AbstractArraySort {

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        array = Arrays.copyOf(sourceArray, sourceArray.length);
        int[] sortedArray = insertSort(array);
        return sortedArray;
    }

    @RuntimeLogAnnotation
    private int[] insertSort(int[] arr) {
        // 遍历无序区，默认第一个元素有序，从 1 开始
        for (int i = 1; i < arr.length; i++) {
            int sortingVal = arr[i];
            // 遍历有序区（0~i-1），找到插入位置；移动有序区，为插入元素挪出一个单元
            int j = 0;
            for (j = i - 1; j >= 0; j--) {
                // 待排序元素与有序区的每一个元素进行比较，如果遇到小于有序区的元素，则有序区的那个元素之后的所有元素后移一位，往前插入元素
                if (sortingVal < arr[j]) arr[j+1] = arr[j];
                else break;
            }
            // 插入元素，因为循环最后 j-- 了，所以插入位置是 j+1
            arr[j+1] = sortingVal;
        }
        return arr;
    }
}
