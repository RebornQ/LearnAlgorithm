package coding_interviews.solution;

import util.aspects.annotations.RuntimeLogAnnotation;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;

import static org.apache.logging.log4j.util.Strings.LINE_SEPARATOR;

/**
 * Created by reborn on 2019-10-20 03:06
 * <p>
 * 从尾到头打印链表
 * <p>
 * 题目描述：
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 * <p>
 * 时间限制：1秒 空间限制：32768K
 * <p>
 * 本题知识点： 链表
 */
public class Offer003 {

    public static final Logger logger = LoggerFactory.getLogger(Offer003.class);

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    @RuntimeLogAnnotation(description = "解法一：先逆置链表再遍历链表进List")
    /*
     * 解法一：先逆置链表再遍历链表进List
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        listNode = reverse(listNode);
        ListNode p = listNode;
        ArrayList<Integer> list = new ArrayList<>();
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        return list;
    }

    @RuntimeLogAnnotation(description = "解法二：先遍历链表进List，再用 Collections.reverse(List<?> list) 逆置List")
    /*
     * 解法二：先遍历链表进List，再用 Collections.reverse(List<?> list) 逆置List
     */
    public ArrayList<Integer> printListFromTailToHeadV2(ListNode listNode) {
        ListNode p = listNode;
        ArrayList<Integer> list = new ArrayList<>();
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        Collections.reverse(list);
        return list;
    }

    private ArrayList<Integer> arrayList=new ArrayList<>();
    /*
     * 解法三：递归遍历链表进List
     */
    public ArrayList<Integer> printListFromTailToHeadV3(ListNode listNode) {
        if(listNode!=null){
            this.printListFromTailToHeadV3(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    @RuntimeLogAnnotation(description = "解法三：递归遍历链表进List")
    public ArrayList<Integer> printListFromTailToHeadV3Simple(ListNode listNode) {
        return printListFromTailToHeadV3(listNode);
    }

    /*
     * 无头结点逆置链表
     *
     * 思路：
     * 相当于两条链表的操作，
     * p是新结点，rear是操作链表（rear在一轮结束时指向该链表头部p），pNext是结点暂存链表，
     * p每次从结点暂存链表中拿一个结点出来。
     */
    public ListNode reverse(ListNode head) {
        ListNode p = head, // （第一次从结点暂存链表中拿一个结点出来）
                rear = null, pNext = null ;
        while (p != null) {
            pNext = p.next; // p 的下一个结点开始的链表（更新结点暂存链表）
            p.next = rear;  // p指向rear，实现逆置（新结点p头插至操作链表rear）
            rear = p;   // 更新rear指针指向链表1头部（更新操作链表rear的头指针）
            p = pNext;  // （从结点暂存链表中拿一个结点出来）
        }
        return rear;
    }

    public static void main(String[] args) {
        Offer003 offer003 = new Offer003();
        Offer003.ListNode head = null, rear = null;
        for (int i = 0; i < 6; i++) {
            Offer003.ListNode node = new ListNode(i);
            if (head == null) head = node;
            if (rear == null) rear = node;
            else {
                rear.next = node;
                rear = rear.next;
            }
        }
        logger.info("offer003.printListFromTailToHead\t 运行结果 : {}" + LINE_SEPARATOR, JSONObject.toJSON(offer003.printListFromTailToHead(head)));
        logger.info("offer003.printListFromTailToHeadV2\t 运行结果 : {}" + LINE_SEPARATOR, JSONObject.toJSON(offer003.printListFromTailToHeadV2(rear)));
        logger.info("offer003.printListFromTailToHeadV3\t 运行结果 : {}" + LINE_SEPARATOR, JSONObject.toJSON(offer003.printListFromTailToHeadV3Simple(rear)));
    }
}
