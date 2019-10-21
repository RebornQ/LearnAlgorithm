# 队列
## 什么是队列
我们同样举一个生活例子：单行隧道

假如公路上有一条单行隧道，所有通过隧道的车辆只允许从隧道入口驶入，从隧道出口驶出，不允许逆行。

![队列例子](/images/queue_info_1.png)

因此，想要让车辆驶出隧道，只能按照他们驶入隧道的顺序，先驶入的车辆先驶出，后驶入的车辆后驶出，任何车辆都无法跳过它前面的车辆提前驶出。

![队列例子](/images/queue_info_2.png)

由此，我们可以得出队列的一般结构如下：

![队列的一般结构](/images/15658729998129.jpg)

**没有任何元素的队列则称为空队。**

## 队列的特点
队列(Queue)与栈一样，是一种线性存储的数据结构，它的特征和行驶车辆的单行隧道很相似。

从上面的例子我们可以引出队列的特点：
1. 队列中的元素遵循“**先入先出**”(**First In First out**)的原则，简称 **FIFO** 结构（不同于栈的先入后出）。
2. 在队尾添加元素，在队头移出元素。

## 队列的相关概念
1. 队头与队尾：
    - 允许元素插入的一端称为队尾，也是队列的入口端。
    - 允许元素删除的一端称为队头，也是队列的出口端。
2. 入队：队列的插入操作
3. 出队：队列的删除操作

## 队列的存储结构
队列与栈一样是一种线性结构，一种特殊的线性表，因此以常见的线性表如数组、链表作为底层的数据结构。

本文我们以`数组`、`链表`为底层数据结构来构建队列。

## 队列的操作
队列(Queue)主要有入队和出队两个操作，但要实现队列(Queue)，至少应该包括以下：
1. `enQueue(T t)` 入队操作
2. `deQueue()` 出队操作
3. `isEmpty()` 判断队列是否为空

有时我们根据需求可能还得：
- `size()` 求队列的大小
- `peek()` 获取队首元素

## 队列的实现
### 队列的抽象数据类型
队列提供了如上所述操作的相应接口。
```java
public interface MyQueue<T> {
    /**
     * 入队
     *
     * @param data 入队元素
     */
    void enQueue(T data);

    /**
     * 出队
     *
     * @return 出队元素
     */
    T deQueue();

    /**
     * 队列是否为空
     */
    boolean isEmpty();
}
```

在这里我把队头队尾指针和输出等封装成抽象类了：
```java
public abstract class AbstractQueue<T, R> implements MyQueue<T> {

    protected R front, rear;

    /**
     * 自定义输出
     *
     * @param message 输出的信息
     */
    abstract void output(String message);

    /**
     * 默认输出
     */
    public void output() {
        output("输出当前队列：");
    }
}
```

泛型T是队列元素的数据类型，R是队头队尾指针的数据类型

（跟栈的实现一样，虽然泛型R不知道是不是多此一举，脑子一热突然想到就去实现了hhh...）

### 基于数组的循环队列的设计与实现
以数组作为底层数据结构时，一般讲队列实现为**循环队列**。

在给出实现方案前我们先来分析一下为什么不直接使用顺序表作为底层容器来实现。

实际上，这是因为队列在顺序存储上的不足：采用顺序表实现队列时，入队操作直接执行顺序表尾部插入操作，其时间复杂度为 O(1) ；但是，每次从顺序表头部删除元素（出队）后，需要将头部以后的**所有元素往前移动一个位置**，这是一个时间复杂度为 O(n) 的操作，效率低：

![队列的顺序存储](/images/15658741533665.jpg)

可能有人说，把队首标志往后移动不就不用移动元素了吗？的确，这样出队的时间复杂度也降为 O(1) 了，但那样会造成数组空间的“流失”，如下图所示：

![顺序表插入过程](/images/15658746154555.jpg)

解释一下上面的过程：

首先我们在顺序表中添加一个头指向下标front和尾指向下标rear，出队和入队时只要改变front、rear的下标指向取值即可，此时无需移动元素，因此出队的时间复杂度也就变为O(1)。

我们不难发现，此时数组的0和1的空间已经被人为浪费了。从图中的 d 和 e 操作可以发现，20和30出队后，**遗留下来的空间并没有被重新利用**，反而是空着，所以导致执行 f 操作时，出现队列已满的假现象，这种假现象我们称之为**假溢出**，之所以出现这样假溢出的现象是因为顺序表队列的存储单元没有重复利用机制。

我们希望队列的插入与删除操作都是 O(1) 的时间复杂度，同时不会造成数组空间的浪费。

而解决该问题的最合适的方式就是将顺序队列设计为循环结构，接下来我们就通过循环顺序表来实现顺序队列。

顺序循环队列就是将顺序队列设计为在逻辑结构上首尾相接的循环结构。

所谓的循环队列，可以把数组看出一个首尾相连的圆环，删除元素时将队首标志往后移动，添加元素时若数组尾部已经没有空间，则考虑数组头部的空间是否空闲，如果是，则在数组头部进行插入，这样我们就可以重复利用存储单元，其过程如下所示： 

![循环队列操作过程](/images/15658757551453.jpg)

简单分析一下： 

其中采用循环结构的顺序表，可以循环利用存储单元，因此有如下计算关系(其中 size 为队列长度)：
```java
// 其中front、rear的下标的取值范围是0~size-1，不会造成假溢出。
front = (front + 1) % size;   // 队头下标
rear = (rear + 1) % size;   // 指向下一个入队元素的下标，不是队尾下标
```

那么我们如何判断队列是空队列还是已满呢？
- 队空： `队首标志front = 队尾标志rear` 时，表示队列为空。
- 队满： 约定`front= (rear + 1) % size` 时，表示队列为满，即 `(队尾下标+1) % 数组长度 = 队头下标`。图中 e队列 即为一个满队列。注意此时队列中仍有一个空的位置，队尾指针也**永远要空出1位**，尽管我们不存储元素，所以队列最大容量比数组长度小1，主要用于避免与队列空的条件 `front = rear` 相同。

定义顺序循环队列的代码实现：
```java
public class SequenceQueue<T, R extends Integer> extends AbstractQueue<T, R> {

    private Class<T> type;

    private T[] queue;

    /**
     * 队列的初始长度为10
     */
    private final int CAPACITY_DEFAULT = 10;

    private int front, rear;

    @SuppressWarnings("unchecked")
    public SequenceQueue(Class<T> type) {
        this.type = type;
        queue = (T[]) Array.newInstance(type, CAPACITY_DEFAULT);
        front = rear = 0;
    }

    @SuppressWarnings("unchecked")
    public SequenceQueue(Class<T> type, int capacity) {
        this.type = type;
        queue = (T[]) Array.newInstance(type, capacity);
        front = rear = 0;
    }
    
    // ...省略更多代码...
}
```
跟栈一样，泛型数组的类型擦除原本是使用强转类型的，后来出现了点问题，就改用`Class`对象作为类型标识，避免一些泛型类型擦除出现的问题，搭配`Array.newInstance()`方法实例化泛型数组。

#### 入队
入队(enqueue)就是把新元素放入队列中，只允许在队尾的位置放入元素，新元素的下一个位置将会成为新的队尾。

实现代码如下：
```java
@Override
public void enQueue(T data) throws QueueException {
    // 约定 front=(rear+1)%size 时队列为满
    if ((rear + 1) % queue.length == front) {
        throw new QueueOverFlowError();
    }
    queue[rear] = data;
    // 入队操作改变rear下标指向
    rear = (rear + 1) % queue.length;
}
```

#### 出队
出队操作(dequeue)就是把元素移出队列，只允许在队头一侧移出元素，出队元素的后一个元素将会成为新的队头。

实现代码如下：
```java
@Override
public T deQueue() throws QueueException {
    if (isEmpty()) {
        throw new EmptyQueueException();
    }
    T deQueueElement = queue[front];
    // 出队操作改变front下标指向
    front = (front + 1) % queue.length;
    return deQueueElement;
}
```

#### 判断队列是否为空

很简单，判断是否满足队列为空的条件(front == rear)即可。

实现代码如下：
```java
@Override
public boolean isEmpty() {
    return front == rear;
}
```

### 链式队列的设计与实现
分析完顺序队列，我们接着看看链式队列的设计与实现，对于链式队列，将使用带 头指针front 和 尾指针rear 的单链表实现，front 直接指向队头的第一个元素，rear 指向队尾的最后一个元素，其结构如下： 

![链式队列结构](/images/15658845841441.jpg)

之所以选择单链表（带头尾指针）而不采用循环双链表或者双链表主要是双链表的空间开销（空间复杂度，多前继指针）相对单链表来说大了不少，而单链表只要新增头指针和尾指针就可以轻松实现常数时间内（时间复杂度为O(1)）访问头尾结点，它不存在数组的 O(n) 的元素移动问题或空间浪费问题。我们所要确定的就是链表哪头做队首，哪头做队尾。

显然我们应该以链表头部为队头，链表尾部为队尾。存储一个指向队尾的指针，方便从链表尾插入元素；存储一个指向队头的指针，方便从链表头删除元素。

下面我们来看看如何设计链式队列：
1. 以上述的图为例分别设置 front 和 rear 指向队头结点和队尾结点，使用单链表的头尾访问时间复杂度为O(1)。
2. 设置初始化空队列，使用`front=rear=null`初始化头节点，并且约定条件`front == null && rear == null`成立时，队列为空。
3. 出队操作时，若队列不为空获取队头结点元素，并删除队头结点元素，更新 front 指针的指向为`front = front.next`。
4. 入队操作时，使插入元素的结点在 rear 之后并更新 rear 指针指向新插入元素。
5. 当第一个元素入队或者最后一个元素出队时，同时更新 front 指针和 rear 指针的指向。 

定义链式队列的代码实现：
```java
public class LinkedQueue<T, R extends LinkedQueue.Node<T>> extends AbstractQueue<T, R> {

    protected static class Node<U> {
        U data;
        Node<U> next;

        public Node(U data, Node<U> next) {
            this.data = data;
            this.next = next;
        }
    }

//    private Node<T> front, rear;

    public LinkedQueue() {
        // 初始化空队列
        front = rear = null;
    }
    
    // ...省略更多代码...
}
```

#### 入队
入队操作过程如图示：

![链式队列入队操作过程](/images/15658855764538.jpg)

代码实现如下：
```java
@Override
public void enQueue(T data) {
    R node = (R) new Node<>(data, null);
    if (front == null) {    // 空队列插入元素
        front = node;
    } else {    // 非空队列，队尾插入元素
        rear.next = node;
    }
    rear = node;
}
```

#### 出队
出队操作过程如图示：

![链式队列出队操作过程](/images/15658856698487.jpg)

代码实现如下：
```java
@Override
public T deQueue() {
    if (isEmpty()) {
        throw new EmptyQueueException();
    }
    T node = front.data;
    front = (R) front.next;
    if (front == null) {
        rear = null;
    }
    return node;
}
```

#### 判断队列是否为空
判断空队列只要判断队头指针和队尾指针都不是指向 null 即可：
```java
@Override
public boolean isEmpty() {
    return front == null && rear == null;
}
```

### 算法复杂度
最后我们来看看顺序队列与链式队列中各个操作的算法复杂度对比。

- 时间复杂度：

    因为顺序队列每次出队后，需要将队头以后的**所有元素往前移动一个位置**，这是一个时间复杂度为 O(n) 的操作；其他操作都是只对队头或队尾进行操作，所以时间复杂度为 O(1) 。

    | 操作 | 顺序队列 | 顺序循环队列 | 链式队列 |
    | --- | --- | --- | --- |
    | 入队 / enQueue() | O(1) | O(1) | O(1) |
    | 出队 / deQueue() | **O(n)** | O(1) | O(1) |
    | 判队空 / isEmpty() | O(1) | O(1) | O(1) |
    
    
### 队列的应用
1. 队列的输出顺序和输入顺序相同，所以队列通常用于对“历史”的回放，也就是按照“历史”的顺序，把“历史”重演一遍。
    - 例如网络爬虫实现网站抓取时，也是把待抓取的网站URL存入队列中，再按照存入队列的顺序来依次抓取和解析的。
2. 异步数据的传输（文件输入输出、管道、嵌套字）。
3. 模拟现实世界中的队列，如售票柜台的队列以及其他先到先服务的场景。

### 完整源码以及参考来源
源码我放到 github 上了，这是链接：[点击跳转查看完整源码](https://github.com/RebornQ/LearnAlgorithm/tree/master/src/datastructure/_3_queue)

参考来源（也是很多图不想画（跑））：
- [java数据结构与算法之（Queue）队列设计与实现](https://blog.csdn.net/javazejian/article/details/53375004)
- [数据结构图文解析之：队列详解与C++模板实现](https://www.cnblogs.com/QG-whz/p/5171123.html)
- 《漫画算法：小灰的算法之旅》