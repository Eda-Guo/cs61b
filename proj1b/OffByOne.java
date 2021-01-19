import java.util.Stack;

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == 1) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        return false;
    }
}
