import java.io.*;
import java.util.*;

// CodeForces Round 633 (Div. 2) Problem B: Sorted Adjacent Differences
// Problem Statement: https://codeforces.com/problemset/problem/1339/B
/*
    Sort all of the numbers, start from the middle and oscillate between adding and subtracting index,
    increasing the amount each time, it will always satisfy the restriction
*/
public class SortedAdjacentDifferences {
    static class FastIO extends PrintWriter {
        private InputStream stream;
        private byte[] buf = new byte[1<<16];
        private int curChar, numChars;

        public FastIO() { this(System.in,System.out); }
        public FastIO(InputStream i, OutputStream o) {
            super(o);
            stream = i;
        }

        private int nextByte() {
            if (numChars == -1) throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars == -1) return -1;
            }
            return buf[curChar++];
        }
        public String next() {
            int c; do { c = nextByte(); } while (c <= ' ');
            StringBuilder res = new StringBuilder();
            do { res.appendCodePoint(c); c = nextByte(); } while (c > ' ');
            return res.toString();
        }
        public int nextInt() {
            int c; do { c = nextByte(); } while (c <= ' ');
            int sgn = 1; if (c == '-') { sgn = -1; c = nextByte(); }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res = 10*res+c-'0';
                c = nextByte();
            } while (c > ' ');
            return res * sgn;
        }
        public double nextDouble() { return Double.parseDouble(next()); }
    }

    public static void main (String[] args) {
        FastIO data = new FastIO();
        int T = data.nextInt();

        for (int t = 0; t < T; t++) {
            int N = data.nextInt();
            int [] nums = new int[N];

            for (int n = 0; n < N; n++) {
                nums[n] = data.nextInt();
            }

            Arrays.sort(nums);

            int i = (int)Math.ceil(N/2.0) - 1;
            int count = 1;
            boolean isAdd = true;
            while (i >= 0 && i < N) {
                if (count == 1)
                    data.print(nums[i]);
                else
                    data.print(" " + nums[i]);
                if (isAdd) {
                    i += count;
                    count++;
                    isAdd = false;
                }
                else {
                    i -= count;
                    count++;
                    isAdd = true;
                }
            }
            data.println();
        }
        data.close();
    }
}
