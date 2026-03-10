package project20280.stacksqueues;


import org.junit.jupiter.api.Test;
import project20280.interfaces.Stack;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ArrayStackTest {
    @Test
    void testSize() {
        Stack<Integer> s = new ArrayStack<>();

        assertEquals(0, s.size());

        int N = 16;
        for (int i = 0; i < N; ++i) s.push(i);
        assertEquals(N, s.size());
    }

    @Test
    void testIsEmpty() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        for (int i = 0; i < 10; ++i) {
            s.pop();
        }
        assertTrue(s.isEmpty());
    }

    @Test
    void testPush() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    @Test
    void testTop() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, (s.top()));
    }

    @Test
    void testPop() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, (s.pop()));
        assertEquals(9, s.size());
    }

    @Test
    void testToString() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    @Test
    void testConvertToBinary() {
        assertEquals("0", ArrayStack.convertToBinary(0));
        assertEquals("10111", ArrayStack.convertToBinary(23));
        assertEquals("111001000000101011000010011101010110110001100010000000000000",
                ArrayStack.convertToBinary(1027010000000000000L));
    }

    @Test
    void testConvertToBase() {
        assertEquals("10111", ArrayStack.convertToBase(23, 2));
        assertEquals("27", ArrayStack.convertToBase(23, 8));
        assertEquals("23", ArrayStack.convertToBase(23, 10));
        assertEquals("17", ArrayStack.convertToBase(23, 16));
        assertEquals("1Z", ArrayStack.convertToBase(71, 36));
    }
}
