public class ArrayDeque<T> {
    private static int initialCapacity = 8;
    private static int factor = 2;
    private T[] items;
    private int capacity;
    private int size;
    private double ratio;

    public ArrayDeque() {
        items = (T[]) new Object[initialCapacity];
        size = 0;
        capacity = initialCapacity;
        ratio = 0.25;
    }

    private void resize(int newCap) {
        if (newCap <= initialCapacity){
            return;
        }
        T[] nItems = (T[]) new Object[newCap];
        System.arraycopy(items, 0, nItems, 0, size);
        this.capacity = newCap;
        items = nItems;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * factor);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = item;
        size++;

    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size++;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
    private void checkRatio() {
        if ((double) size / capacity < ratio) {
            resize(capacity / factor);
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = items[0];
        System.arraycopy(items, 1, items, 0, size - 1);
        items[size - 1]  = null;
        size--;
        checkRatio();
        return res;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = items[size - 1];
        items[size - 1]  = null;
        size--;
        checkRatio();
        return res;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }

//    public  static void main(String[] args){
//        ArrayDeque<Integer> ad = new ArrayDeque<>();
//        ad.addFirst(0);
//        ad.addFirst(1);
//        System.out.println(ad.removeLast());
//        System.out.println(ad.removeFirst());
//        ad.addFirst(4);
//        System.out.println(ad.removeFirst());
//        ad.isEmpty();
//        ad.addFirst(7);
//        System.out.println(ad.removeFirst());
//        ad.addFirst(9);
//        //ad.addLast(9);
//        System.out.println(ad.removeFirst());
//        ad.removeLast();
//        System.out.println(ad.get(3));
//        ad.printDeque();
//        ad.printDeque();
//    }

}
