import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyStack<T>  {

    private MyLinkedList<T> myStack = new MyLinkedList<>(); // based on linked list

    public MyStack() {
    }

    public T top() {
        return (T) myStack.getLast();
    }

    public T pop() {
        T item = myStack.getLast();
        myStack.removeLast();
        return item;
    }

    public void push(T item) {
        myStack.addLast(item);
    }

    public boolean isEmpty() {
        return myStack.size() == 0;
    }

    public int size() {
        return myStack.size();
    }

    public void clear() {
        myStack.clear();
    }

}
