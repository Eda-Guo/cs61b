public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;


        Node(T x) {
            item = x;
            prev = null;
            next = null;

        }
    }

    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Check whether the list is empty
     * @return if empty return true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add item at the first of list
     * @param item
     */
    public void addFirst(T item) {
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }
    public int size() {
        return size;
    }
    public void addLast(T item) {
        Node node = new Node(item);
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    public void printDeque() {
        Node curr = sentinel.next;
        while (curr.next != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println(curr.item);
    }

    public T removeFirst() {
        if(size == 0){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return item;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev.next = null;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node curr = sentinel.next;
        int count = 0;
        while (count != index) {
            curr = curr.next;
            count++;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }


//    public static void main(String[] args){
//        LinkedListDeque<Integer> ld = new LinkedListDeque<>();
//        ld.addLast(0);
//        System.out.println(ld.removeLast());
//        ld.addFirst(2);
//        System.out.println(ld.getRecursive(0));
//        ld.addFirst(4);
//        ld.addFirst(5);
//        System.out.println(ld.removeLast());
//    }
}
