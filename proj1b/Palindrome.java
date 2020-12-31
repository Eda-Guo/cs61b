public class Palindrome {
    /**
     * Put word into deque
     * @param word
     * @return return a list of character
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        if (word == null || word.length() == 0) {
            return null;
        }
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
//        int left = 0;
//        int right = word.length() - 1;
//        while (left < right) {
//            if (word.charAt(left) != word.charAt(right)) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        Deque ld = wordToDeque(word);
//        while(ld.size() > 1) {
//            if (ld.removeFirst() != ld.removeLast()) {
//                return false;
//            }
//        }
        return helper(word, 0, word.length() - 1);
    }

    private boolean helper(String word, int start, int end) {
        // base case
        if (start >= end) {
            return true;
        }
        return word.charAt(start) == word.charAt(end) && helper(word, start + 1, end - 1);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (!cc.equalChars(word.charAt(left), word.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
