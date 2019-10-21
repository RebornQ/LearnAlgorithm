# 栈
## 什么是栈
举个很简单的生活例子：

装羽毛球的圆筒，一端封闭，另一端开口。

我们放羽毛球进去的时候是一个一个放进去的（从筒底叠到筒口），但是取的时候，我们是从最上面的一个依次向下取，**先取出后放入的，再取出先放入的**，而不可能把最里面最先放入的羽毛球优先取出。

## 栈的特点
栈是一种线性数据结构，从上面的例子我们可以引出栈的特点：
1. 栈中元素只能**后进先出**(Last In First Out, 简称**LIFO**)，**先进后出**(First In Last Out, 简称 **FILO**)。
2. 限定**只能在栈顶**进行插入和删除操作。

## 栈的相关概念
1. 栈顶与栈底：
    - 一般而言，把允许操作（元素插入与删除）的一端称为栈顶(Top)，也是**最后**进入的元素存放位置。
    - 不可操作的一端称为栈底(Bottom)，也是**最早**进入的元素存放位置。
2. 压栈(Push)：栈的元素插入操作，叫做进栈，也称压栈、入栈。
3. 弹栈(Pop)：栈的元素删除操作，也叫出栈。

## 栈的存储结构
上面说了栈是一种线性存储结构，它是一种用于存储数据的简单数据结构，有点类似链表或者顺序表（统称线性表），但栈与线性表的最大区别是数据的存取操作；我们可以这样认为栈(Stack)是一种特殊的线性表，其插入和删除操作都**只允许**在线性表的**一端**进行。

栈既然是一种线性结构，就能够以数组或链表（单向链表、双向链表或循环链表）作为底层数据结构。

本文我们以`数组`、`单向链表`为底层数据结构来构建栈。

## 栈的操作
栈主要有入栈和出栈两个操作，若栈中没有任何元素，则称为空栈。

如下图示：

![栈的基本操作](/images/15657493129652.jpg)

由上图我们可以看成栈只能从栈顶存取元素，同时，先进入的元素反而是后出（这就是我们前面一直强调的栈的特点），而**栈顶(Top)永远指向栈内最顶部的元素**。

<!--于是我们可以得出栈的正式定义：栈(Stack)是一种有序特殊的线性表，只能在表的一端（称为栈顶，top，总是指向栈顶元素）执行插入和删除操作，最后插入的元素将第一个被删除，因此栈也称为后进先出(Last In First Out,LIFO)或先进后出(First In Last Out FILO)的线性表。-->

但是要是实现栈(Stack)，至少应该包括以下：
1. `pop()` 出栈操作，弹出栈顶元素；
2. `push(T t)` 入栈操作；
3. `peek()` 获取栈顶元素，不弹栈
4. `isEmpty()` 判断栈是否为空

有时我们根据需求可能还得求栈的大小：
- `size()` 求栈的大小

> 注意：栈不支持对指定位置进行删除、插入

## 栈的实现
### 栈的抽象数据类型
栈提供了如上所述操作的相应接口。
```java
public interface MyStack<T> {
    /**
     * 栈是否为空
     */
    boolean isEmpty();

    /**
     * 元素入栈
     * @param data 入栈的元素
     */
    void push(T data) throws StackException;

    /**
     * 返回栈顶元素，未出栈
     * @return 栈顶元素
     */
    T peek();

    /**
     * 出栈，返回栈顶元素，同时从栈中移除该元素
     * @return 栈顶元素
     */
    T pop();
}
```

在这里我把栈顶指针、栈初始容量和栈大小等封装成抽象类了。
```java
public abstract class AbstractStack<T, R> implements MyStack<T>{

    /**
     * 栈顶指针，-1或null代表空栈
     */
    protected R top;

    /**
     * 栈的初始容量为10
     */
    protected final int CAPACITY_DEFAULT = 10;

    /**
     * 栈的大小
     */
    protected int size = 0;

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
        output("输出当前栈：");
    }

}
```

泛型T是栈元素的数据类型，R是栈顶指针的数据类型

（虽然泛型R不知道是不是多此一举，脑子一热突然想到就去实现了hhh...）

### 基于数组的栈(顺序栈)的设计与实现
当以数组为底层数据结构时，通常以数组头为栈底，数组头到数组尾为栈顶的生长方向：

![基于数组的栈](/images/15657557167270.jpg)

定义栈的代码实现：
```java
import java.lang.reflect.Array;

public class SequenceStack<T, R extends Integer> extends AbstractStack<T, R> {

    /**
     * 栈顶指针，-1代表空栈
     */
    private int top = -1;

    /**
     * 数组实现顺序栈
     */
    private T[] array;

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public SequenceStack(Class<T> type) {
        this.type = type;
        array = (T[]) Array.newInstance(type, this.CAPACITY_DEFAULT);
    }

    @SuppressWarnings("unchecked")
    public SequenceStack(Class<T> type, int capacity) {
        this.type = type;
        array = (T[]) Array.newInstance(type, capacity);
    }
    
    // ...省略更多代码...
}
```
泛型数组的类型擦除原本是使用强转类型的，后来出现了点问题，就改用`Class`对象作为类型标识，避免一些泛型类型擦除出现的问题，搭配`Array.newInstance()`方法实例化泛型数组。

#### 入栈
入栈操作(push)就是把新元素放入栈中，只允许从栈顶一侧放入元素，新元素的位置将会成为新的栈顶。

入栈操作的过程如下：

![顺序栈入栈操作过程](/images/15657697120253.jpg)

实现代码如下：
```java
@Override
public void push(T data) {
    // 判断是否达到容量限制
    if (top.equals(array.length - 1))
        // 扩容
        ensureCapacity(array.length * 2 + 1);
    // 更新栈顶指针，并向栈顶添加元素
    array[++top] = data;
    // 更新栈大小
    size++;
}
```

当容量不足时，会对栈进行扩容，实现代码如下：
```java
@SuppressWarnings("unchecked")
public void ensureCapacity(int capacity) {
    // 如果需要扩展的容量比现在数组容量还小，则无需扩容
    if (capacity < size) return;

    T[] oldArray = array;
    array = (T[]) Array.newInstance(type, capacity);
    // 复制元素到扩容后的新数组
    for (int i = 0; i < size; i++) {
        array[i] = oldArray[i];
    }
    oldArray = null;
}
```

#### 判断栈是否为空
在出栈和获取栈顶元素前都要先判断栈是否为空才能进行下一步操作，实现代码如下：
```java
@Override
public boolean isEmpty() {
    return top == -1;
}
```
很简单，只要判断一下**栈顶指针是否为-1**就可以了。

#### 出栈
出栈操作(pop)就是把元素从栈中弹出，只有栈顶元素才允许出栈，出栈元素的前一个元素将会成为新的栈顶。

出栈操作的过程如下：

![顺序栈出栈操作过程](/images/15657711697078.jpg)

实现代码如下：
```java
@Override
public T pop() {
    if (isEmpty()) {    // 判断是否空栈
        throw new EmptyStackException();
    }
    size--;
    return array[top--];
}
```
**取完值必须更新栈顶指针！**

#### 获取栈顶元素
获取栈顶元素值的 peek 操作过程如下图（不弹栈只获取值）：

![顺序栈获取栈顶元素值操作过程](/images/15657716481989.jpg)

代码实现如下：
```java
@Override
public T peek() {
    if (isEmpty()) {
        throw new EmptyStackException();
    }
    return array[top];
}
```
这里只需要取完值后**栈顶指针不变**即可。

### 基于单链表的栈(链式栈)的设计与实现
了解完顺序栈，我们接着来看看链式栈，所谓的链式栈（Linked Stack），就是采用链式存储结构的栈，这里采用单链表（不带头结点）作为底层的数据结构。图示如下：

![链式栈](/images/15657852903320.jpg)

定义链栈的代码实现：
```java
public class LinkedStack<T, R extends LinkedStack.Node<T>> extends AbstractStack<T, R> {

    // 定义一个节点类
    protected static class Node<U> {
        U data;
        Node<U> next;

        public Node() {
            data = null;
            next = null;
        }

        public Node(U data, Node<U> next) {
            this.data = data;
            this.next = next;
        }

        public boolean isEmpty() {
            return data == null && next == null;
        }
    }

    // 栈顶指针
//    private Node<T> top;

    LinkedStack() {
        top = (R) new Node<>();
    }
    
    // ...省略更多代码...
}
```

#### 入栈
入栈操作的过程如下：

![链栈入栈操作过程](/images/15657857257948.jpg)

实现代码：
```java
@Override
public void push(T data) throws StackException {
    if (data == null) throw new StackException("坏人！想骗吃骗喝？");
    top = (R) new Node<>(data, top);    // 更新栈顶
}
```

#### 判断栈是否为空
在出栈和获取栈顶元素前都要先判断栈是否为空才能进行下一步操作，实现代码如下：
```java
@Override
public boolean isEmpty() {
    return top.isEmpty();
}
```
很简单，只要栈顶指针指向的结点`data`和`next`域不是`null`即不为空。

#### 出栈
出栈操作的过程如下：

![链栈出栈操作过程](/images/15657859930093.jpg)

实现代码：
```java
@Override
public T pop() {
    if (isEmpty()) {
        throw new EmptyStackException();
    }
    T result = top.data;
    top = (R) top.next;
    return result;
}
```

#### 获取栈顶元素
同样是获取栈顶元素值不改变栈顶指针即可。实现代码如下：
```java
@Override
public T peek() {
    if (isEmpty()) {
        throw new EmptyStackException();
    }
    T result = top.data;
    return result;
}
```

### 代码测试
测试代码：
```java
public static void main(String[] args) {
    System.out.println("注意：栈中的箭头方向仅指入栈方向");
    AbstractStack stack = null;
    try {
        for (int i = 0; i < 11; i++) {
            Objects.requireNonNull(stack).push(i);
        }
        stack.output();
        for (int i = 0; i < 3; i++) {
            System.out.println("Pop->" + stack.pop());
        }
        stack.output();
        System.out.println("\nPeek->" + stack.peek());
        for (int i = 0; i < 3; i++) {
            stack.push(i);
        }
        stack.output("\n再次入栈后：");
        while (!stack.isEmpty()) {
            System.out.println("Pop->" + stack.pop());
        }
        System.out.println("\nEmpty? " + stack.isEmpty());
        stack.output();
    } catch (StackException e) {
        System.out.println(e.getLocalizedMessage());
        e.printStackTrace();
    }
}
```

测试结果：
```bash
注意：栈中的箭头方向仅指入栈方向
输出当前栈：
top ➡︎ | 10 → 9 → 8 → 7 → 6 → 5 → 4 → 3 → 2 → 1 → 0 | ⬅︎ bottom
Pop->10
Pop->9
Pop->8
输出当前栈：
top ➡︎ | 7 → 6 → 5 → 4 → 3 → 2 → 1 → 0 | ⬅︎ bottom

Peek->7

再次入栈后：
top ➡︎ | 2 → 1 → 0 → 7 → 6 → 5 → 4 → 3 → 2 → 1 → 0 | ⬅︎ bottom
Pop->2
Pop->1
Pop->0
Pop->7
Pop->6
#...省略更多...

Empty? true
输出当前栈：
坏了！栈里的奶酪被杰瑞偷偷吃光了！
```

### 时间复杂度和空间复杂度
最后我们来看看顺序栈与链式栈中各个操作的算法复杂度（时间和空间）对比。

- 时间复杂度：

    入栈和出栈只会影响最后一个元素，不涉及其他元素的整体移动，所以无论是以数组还是以链表实现，入栈、出栈的时间复杂度都是O(1)，由此可知栈的主要操作都可以在常数时间内完成。
  
    | 操作 | 顺序栈 | 链式栈 |
    | --- | --- | --- |
    | push() | O(1) | O(1) |
    | pop() | O(1) | O(1) |
    | peek() | O(1) | O(1) |
    | isEmpty() | O(1) | O(1) |
    
- 空间复杂度：
    
    | 操作 | 顺序栈 | 链栈 |
    | --- | --- | --- |
    | N次push | O(n) | O(n) |

### 栈的应用
- 历史回溯
    * 如浏览器中已访问页面的历史记录。
    * 还有一个很著名的应场景是面包屑导航，使用户在浏览页面时可以轻松地回溯到上一级或更上一级页面。
    
        > 面包屑导航(BreadcrumbNavigation)这个概念来自童话故事"汉赛尔和格莱特"，当汉赛尔和格莱特穿过森林时，不小心迷路了，但是他们发现在沿途走过的地方都撒下了面包屑，让这些面包屑来帮助他们找到回家的路。所以，面包屑导航的作用是告诉访问者他们目前在网站中的位置以及如何返回。   ——引自[百度百科](https://baike.baidu.com/item/%E9%9D%A2%E5%8C%85%E5%B1%91%E5%AF%BC%E8%88%AA)

- 实现递归的逻辑
- 待补充...


### 完整源码以及参考来源
源码我放到 github 上了，这是链接：[点击跳转查看完整源码](https://github.com/RebornQ/LearnAlgorithm/tree/master/src/datastructure/_2_stack)

参考来源（尤其是很多图不想画（跑））：
- [java数据结构与算法之栈（Stack）设计与实现](https://blog.csdn.net/javazejian/article/details/53362993)
- [数据结构图文解析之：栈的简介及C++模板实现](https://www.cnblogs.com/QG-whz/p/5170418.html)
- [Java编程思想-Stack的三种实现（数组，容器，链表）](https://segmentfault.com/a/1190000002516799)
- 《漫画算法：小灰的算法之旅》