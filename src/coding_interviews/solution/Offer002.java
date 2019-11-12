package coding_interviews.solution;

import util.aspects.annotations.RuntimeLogAnnotation;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.logging.log4j.util.Strings.LINE_SEPARATOR;

/**
 * Created by reborn on 2019-10-19 15:12
 * <p>
 * 替换空格
 * <p>
 * 题目描述：
 * 请实现一个函数，将一个字符串中的每个空格替换成"%20"。
 * <p>
 * 例如，当字符串为We Are Happy. 则经过替换之后的字符串为We%20Are%20Happy。
 * <p>
 * 时间限制：1秒 空间限制：32768K
 * <p>
 * 本题知识点：字符串
 */
public class Offer002 {


    public static final Logger logger = LoggerFactory.getLogger(Offer002.class);

    @RuntimeLogAnnotation(description = "解法一：用 String 自带的函数 str.toString().replace(CharSequence target, CharSequence replacement)")
    /*
     * 解法一：用 String 自带的函数 str.toString().replace(" ", "%20")。
     */
    public String replaceSpace(StringBuffer str) {
        return str.toString().replace(" ", "%20");
    }

    @RuntimeLogAnnotation(description = "解法二：用 StringBuffer 自带的函数 str.replace(int start, int end, String str)")
    /*
     * 解法二：用 StringBuffer 自带的函数 str.replace(i, i+1, "%20")。
     */
    public String replaceSpaceV2(StringBuffer str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, "%20");
            }
        }
        return str.toString();
    }

    @RuntimeLogAnnotation(description = "解法三：在当前字符串上进行替换")
    /*
     * 解法三：在当前字符串上进行替换
     *
     * 考虑到题目给的参数是 StringBuffer 可变字符串，猜测题目原意是要在当前字符串做替换
     *
     * 那么，我们就要考虑怎么替换更有效率（不考虑 String 现有的 replace 方法，且 String 的 replace 方法还要经过正则表达式匹配，Java 的正则匹配性能...）
     *
     * 思路：
     * 1. 先计算替换后的字符串需要多大的空间，并对原字符串空间进行扩容；
     * 2. 从后往前替换字符串的话，每个字符只需要移动一次；
     * 3. 如果从前往后，后面的字符要不断往后移动，每个字符需要多次移动，效率较低。
     *
     */
    public String replaceSpaceV3(StringBuffer str) {
        int spaceNum = 0;   // 记录空格数
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        int oldLength = str.length();
        int oldIndex = oldLength - 1;   // 初始化值为替换前的 str 最后一个字符的索引位置
        int newLength = oldLength + spaceNum * 2; // "%20"共3个字符，减去原本占的1个字符，即空格转换成%20之后每个空格增加2个字符长度
        int newIndex = newLength - 1;   // 初始化值为替换后的 str 最后一个字符的索引位置
        str.setLength(newLength);   // 使 str 的长度扩大到转换成 %20 之后的长度,防止下标越界
        // oldIndex < newIndex 说明前面还有移位要处理。没有位移要处理时 oldIndex == newIndex，同时避免继续遍历前面所有没有空格的字符。若是 oldLength < newLength，只能避免一开始就没有空格数的情况。
        for (; oldIndex >= 0 && oldIndex < newIndex; oldIndex--) {
            if (str.charAt(oldIndex) == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String str = "We Are Happy";
        Offer002 offer002 = new Offer002();
        logger.info("offer002.replaceSpace\t 运行结果 : {}" + LINE_SEPARATOR, JSONObject.toJSON(offer002.replaceSpace(new StringBuffer(str))));
        logger.info("offer002.replaceSpaceV2 运行结果 : {}" + LINE_SEPARATOR, JSONObject.toJSON(offer002.replaceSpaceV2(new StringBuffer(str))));
        logger.info("offer002.replaceSpaceV3 运行结果 : {}" + LINE_SEPARATOR, JSONObject.toJSON(offer002.replaceSpaceV3(new StringBuffer(str))));
    }
}
