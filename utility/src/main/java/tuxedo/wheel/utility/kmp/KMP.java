package tuxedo.wheel.utility.kmp;

public class KMP {
    private final char[] target;
    private final int[] next;

    public KMP(String target) {
        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.target = target.toCharArray();
        this.next = new int[this.target.length];
        init();
    }

    private void init() {
        for (int i = 0; i < next.length; i++) {
            if (i == 0 || target[i] != target[next[i - 1]]) {
                next[i] = 0;
            } else {
                next[i] = next[i - 1] + 1;
            }
        }
    }

    public int indexOf(String source) {
        if (source == null || source.length() < target.length) {
            return -1;
        }

        for (int i = 0, match = 0; i < source.length(); ) {
            if (source.charAt(i) == target[match]) {
                if (++match == target.length) {
                    return i + 1 - target.length;
                }
                i++;
            } else if (match > 0) {
                match = next[match - 1];
            } else {
                i++;
            }
        }
        return -1;
    }
}
