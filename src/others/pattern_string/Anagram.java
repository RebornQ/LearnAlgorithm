package others.pattern_string;

import util.aspects.annotations.RuntimeLogAnnotation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by reborn on 2019-10-20 22:23
 * <p>
 * 题目描述：
 * 编写一个函数，检查两个单词是否有字母异位。两个词如果包含相同的字母，但次序不同，则称为字母异位词。
 * <p>
 * 例如：
 * "slient"和"listen"是字母异位词。
 */
public class Anagram {

    @RuntimeLogAnnotation(description = "解法一：极度暴力的方法")
    /*
     * 时间复杂度：最高O(n+n^2)
     */
    public boolean isAnagram(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>(),
                map2 = new HashMap<>();
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return false;
        // 1. 先存好字符出现次数
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            // 若为 c1 在 map1 中不存在，则默认值为1；否则+1
            map1.merge(c1, 1, Integer::sum);

            char c2 = s2.charAt(i);
            map2.merge(c2, 1, Integer::sum);
        }
        // 2. 检查字符及其出现次数是否一致
        Iterator<Character> map2Iter = map2.keySet().iterator();
        for (Character key1 : map1.keySet()) {
            while (true) {
                if (!map2Iter.hasNext()) {
                    return false;
                }
                Character key2 = map2Iter.next();
                if (key1 != key2) continue;
                else {
                    if (!map1.get(key1).equals(map2.get(key2))) return false;
                    else break;
                }
            }
        }
        return true;
    }

    @RuntimeLogAnnotation(description = "解法二：大致思路与上面一致，但是技巧上用了ASCII码")
    /*
     * 思路：
     * 1. 先存好其中一个字符串的字符出现次数
     * 2. 比较上一个字符串的字符及其出现次数与另一个字符串是否一致
     *
     * 时间复杂度：最高O(2n)
     *
     * 用例一：
     * a b c
     * a c b
     *
     * 用例二：
     * a b c
     * a c c
     */
    public boolean isAnagramV2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return false;
        // 1. 先存好其中一个字符串的字符出现次数，利用ascii码判断26个字母在这个数组的下标，数组元素则存对应次数
        int[] charCount = new int[26]; // 自动初始化所有数组元素为0
        for (char c1 : s1.toCharArray()) {
            charCount[c1 - 'a']++;
        }
        // 2. 比较上一个字符串的字符及其出现次数与另一个字符串是否一致；
        //    每遍历第二个字符串的一个字符，charCount数组对应的元素值减1；
        //    若两个字符串字符出现次数一致，那么该字符减完后应当等于0；
        //    如果该元素值小于0，说明出现次数不一致，即不是字母异位词，直接结束不需要再遍历后面的字符。
        for (char c2 : s2.toCharArray()) {
            if (--charCount[c2 - 'a'] < 0) return false;
        }
        /*
         * 用例一结果：
         * a b c
         * 0 0 0
         *
         * 用例二结果：
         * a b c
         * 0 1 -1
         */
        return true;
    }
}
