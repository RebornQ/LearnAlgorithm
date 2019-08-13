package datastructure._1_linkedlist;

public class Client {
    public static void main(String[] args) {
        try {
            MyLinkedList linkedList = new MyLinkedList();
            linkedList.insert(3);
            linkedList.insert(5);
            linkedList.insert(6);
            linkedList.insert(7);
            linkedList.output();
            linkedList.edit(0, 0);
            linkedList.insert(1, 0);
            linkedList.output();
            linkedList.remove(0);
            linkedList.output();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
