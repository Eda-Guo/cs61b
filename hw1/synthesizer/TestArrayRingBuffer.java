package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void isEmptyTest() {
        ArrayRingBuffer<Integer> arf = new ArrayRingBuffer<>(10);
        assertTrue(arf.isEmpty());
    }
    @Test
    public void isFullTest() {
        ArrayRingBuffer<Integer> arf = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arf.capacity(); i++) {
            arf.enqueue(1);
        }
        assertTrue(arf.isFull());
    }
    @Test
    public void dequeueTest() {
        ArrayRingBuffer<Integer> arf = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arf.capacity(); i++) {
            arf.enqueue(1);
        }
        arf.dequeue();
        arf.dequeue();
        arf.dequeue();
        int expect = 7;
        assertEquals(expect, arf.fillCount());
        arf.enqueue(11);
        arf.enqueue(12);
        arf.dequeue();
        int exp2 = 8;
        assertEquals(exp2, arf.fillCount());
    }

    @Test
    public void indexTest() {
        ArrayRingBuffer<Integer> arf = new ArrayRingBuffer<>(10);
        arf.enqueue(0);
        arf.enqueue(1);
        arf.enqueue(2);
        Integer exp1 = 0;
        assertEquals(exp1, arf.peek());
        arf.dequeue();
        Integer exp2 = 1;
        assertEquals(exp2, arf.peek());
    }

    @Test
    public void iteratorTest() {
        ArrayRingBuffer<Integer> arf = new ArrayRingBuffer<>(10);
        arf.enqueue(0);
        arf.enqueue(1);
        arf.enqueue(2);
        arf.enqueue(3);
        for (Integer item : arf) {
            System.out.println(item);
        }

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
