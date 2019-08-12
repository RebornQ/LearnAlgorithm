package datastructure.stack;

public class Client {
    public static void main(String[] args) {
        SequenceStack<Integer> sequenceStack = new SequenceStack<>(Integer.class);
        System.out.println("Empty? " + sequenceStack.isEmpty());
        for (int i = 0; i < 10; i++) {
            sequenceStack.push(i);
        }
        System.out.println("Size: " + sequenceStack.size());
        System.out.println("Empty? " + sequenceStack.isEmpty());
        System.out.println("Peek->" + sequenceStack.peek());
        int size = sequenceStack.size(); // size会在循环过程中不断减小，要先记录
        for (int i = 0; i < size; i++) {
            System.out.println("Pop->" + sequenceStack.pop());
        }
        System.out.println("Size: " + sequenceStack.size());
        System.out.println("Empty? " + sequenceStack.isEmpty());
    }
}
