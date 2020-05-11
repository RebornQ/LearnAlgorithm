package coding_interviews.solution;

import util.aspects.annotations.RuntimeLogAnnotation;

/**
 * Created by reborn on 2019-10-18 20:46
 * <p>
 * 二维数组中的查找
 * <p>
 * 题目描述：
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * <p>
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 时间限制：C/C++ 1秒，其他语言2秒 空间限制：C/C++ 32M(32768K)，其他语言64M
 * <p>
 * 本题知识点：查找 数组
 */
public class Offer001 {

    @RuntimeLogAnnotation(description = "解法一：暴力法")
    /*
     * 解法一：暴力法
     *
     * 思路：循环遍历所有元素
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public boolean violentFind(int target, int[][] array) {
        for (int[] one : array) {
            for (int two : one) {
                if (target == two) return true;
            }
        }
        return false;
    }

    @RuntimeLogAnnotation(description = "解法二：二分法")
    /*
     * 解法二：二分法
     *
     * 思路：
     * 1. 把每一行看成有序递增的数组；
     * 2. 利用二分查找，通过遍历每一行得到答案。
     *
     * 时间复杂度：O(nlogn)
     */
    public boolean binaryFind(int target, int[][] array) {
        for (int[] one : array) {
            int lowIndex = 0;
            int highIndex = one.length - 1;
            while (lowIndex <= highIndex) {
                int midIndex = (lowIndex + highIndex) / 2;
                if (target > one[midIndex]) {
                    lowIndex = midIndex + 1;
                } else if (target < one[midIndex]) {
                    highIndex = midIndex - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 3, 4, 7, 8},
                {2, 5, 6, 9, 10}};
        Offer001 offer001 = new Offer001();
        offer001.violentFind(5, array);
        offer001.binaryFind(5, array);
    }
}
