import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T>{

    class Node<E> {
        E data;
        Node next;
        Node prev;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(E data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            this.data = null;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public MyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    private void checkIndex(int index) {
        if(index < 0|| index >= count)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
    }

    @Override
    public void add(T item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index);
        Node newNode = new Node(item);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        else if (index == count) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        count++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T) current.data;
    }

    @Override
    public T getFirst() {
        return (T) head.data;
    }

    @Override
    public T getLast() {
        return (T) tail.data;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            head = head.next;
            head.prev = null;
        }
        else if (index == count - 1) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        count--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(count - 1);
    }

    @Override
    public void sort() {
        for (Node i = head; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (((Comparable)j.data).compareTo(i.data) < 0) {
                    Object temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        Node current = tail;
        for (int i = count - 1; i >= 0; i--) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[count];
        Node current = head;
        for (int i = 0; i < count; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T> {
        private Node current = head;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public T next() {
            if (hasNext() != true) {
                throw new NoSuchElementException();
            }
            T element = (T) current.data;
            current = current.next;
            index++;
            return element;
        }
    }
}
