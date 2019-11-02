package base.sort;

import java.util.Arrays;

/**
 * 1. 十大经典排序算法（动图演示）：https://www.cnblogs.com/onepixel/p/7674659.html
 * 2. 这或许是东半球讲十大排序算法最好的一篇文章：https://juejin.im/post/5cff49e75188257a6b40de80
 * 3. 十大经典排序算法动画，看我就够了！：https://juejin.im/post/5c027bb6e51d45195061f135
 * 4. 必学十大经典排序算法，看这篇就够了(附完整代码动图优质文章)：https://zhuanlan.zhihu.com/p/57088609
 *
 * （还有归并排序、堆排序）
 */
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

    public int minIndex(int[] arr, int min, int j) {
        return arr[min] < arr[j] ? min : j;
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
