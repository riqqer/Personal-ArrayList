public class MyHeap <T extends Comparable<T>>  {

    MyArrayList<T> heapList = new MyArrayList<>();

    public MyHeap() {
    }

    public void empty() {
        heapList.clear();
    }

    public int size() {
        return heapList.size();
    }

    public T getMax() {
        return heapList.get(0);
    }

    public T extractMax() {
        T max = heapList.get(0);
        swap(0, heapList.size() - 1);
        heapList.remove(heapList.size() - 1);
        heapify(0);
        return max;
    }

    public void insert(T item) {
        heapList.add(item);
        int i = heapList.size() - 1;
        heapify(i);
    }

    private void heapify(int i) {
        int left = leftChildOf(i);
        int right = rightChildOf(i);
        int largest = i;
        if (left < heapList.size() && heapList.get(left).compareTo(heapList.get(largest)) > 0) {
            largest = left;
        }
        if (right < heapList.size() && heapList.get(right).compareTo(heapList.get(largest)) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    private void traverse(int i) {
        if (i < heapList.size()) {
            System.out.println(heapList.get(i));
            traverse(leftChildOf(i));
            traverse(rightChildOf(i));
        }
    }

    public int leftChildOf(int i) {
        return 2 * i;
    }

    public int rightChildOf(int i) {
        return 2 * i + 1;
    }

    public int parentOf(int i) {
        return i / 2;
    }

    public void swap(int i, int j) {
        T temp = heapList.get(i);
        heapList.set(i, heapList.get(j));
        heapList.set(j, temp);
    }
}
