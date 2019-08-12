package datastructure.linkedlist;

public class MyLinkedList {
    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // 头节点指针
    private Node head;
    // 尾节点指针
    private Node last;
    // 链表长度
    private int size;


    /**
     * 链表查找元素
     *
     * @param index 查找位置
     * @return 查到的元素
     */
    public Node get(int index) throws Exception {
        if (index < 0 || index >= size) {    // 超出链表节点范围
            throw new IndexOutOfBoundsException("爆水管啦！");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 链表插入元素
     *
     * @param data  插入元素
     * @param index 插入位置
     */
    public void insert(int data, int index) throws Exception {
        if (index < 0 || index > size) {    // 超出链表节点范围
            throw new IndexOutOfBoundsException("爆水管啦！");
        }
        Node insertNode = new Node(data);
        if (size == 0) {
            // 插入头部
            head = insertNode;
            last = insertNode;
        } else if (size == index) {
            // 插入尾部
            last.next = insertNode;
//            last = insertNode;
            last = last.next;
        } else if (index == 0) {
            // 插入头部
            Node nextNode = head;
            head = insertNode;
            head.next = nextNode;
        } else {
            // 从中间插入
            Node prevNode = get(index - 1);
            Node nextNode = prevNode.next;
            prevNode.next = insertNode;
            insertNode.next = nextNode;
        }
        size++;
    }

    /**
     * 链表直接在尾部插入元素
     *
     * @param data  插入元素
     */
    public void insert(int data) throws Exception {
        insert(data, size);
    }

    /**
     * 链表删除元素
     * @param index 删除位置
     * @return 删除的元素
     */
    public Node remove(int index) throws Exception {
        if (index < 0 || index >= size) {    // 超出链表节点范围
            throw new IndexOutOfBoundsException("爆水管啦！");
        }
        Node removeNode = null;
        if (index == 0) {
            // 删除头节点
            removeNode = head;
            head = head.next;
        } else if (index == size - 1) {
            // 删除尾节点
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        } else {
            // 删除中间节点
            Node prevNode = get(index - 1);
            Node nextNode = prevNode.next.next;
            removeNode = prevNode.next;
            prevNode.next = nextNode;
        }
        size--;
        return removeNode;
    }

    /**
     * 链表修改元素
     * @param data 修改后的元素内容
     * @param index 要修改的位置
     * @return 修改后的元素
     */
    public Node edit(int data, int index) throws Exception{
        Node temp = get(index);
        temp.data = data;
        return temp;
    }

    /**
     * 输出链表
     */
    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
