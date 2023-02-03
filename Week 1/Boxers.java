import java.io.*;
import java.util.*;
// CodeForces Round 579 (Div. 3) Problem E: Boxers
// Problem statement: https://codeforces.com/problemset/problem/1203/E
// Have a list that sort the weights descending, have a largest possible variable
// Get the first on the list and see if the weight + 1 is possible
// If doesn't work, see if weight is possible. If that doesn't work, check weight - 1
// If one of them work, change the largest possible to that num and add one to the count, else don't do anything, go the the next largest num
public class Boxers {
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
        int N = data.nextInt();
        PriorityQueue<Integer> weights = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (int n = 0; n < N; n++) {
            weights.add(data.nextInt());
        }

        int count = 0;
        int last = weights.peek() + 2;
        int i = 0;
        while (i < N) {
            if (weights.peek() + 1 < last) {
                count++;
                last = weights.poll() + 1;
            }
            else if (weights.peek() < last) {
                count++;
                last = weights.poll();
            }
            else if (weights.peek() - 1 < last && weights.peek() - 1 != 0) {
                count++;
                last = weights.poll() - 1;
            }
            else {
                weights.poll();
            }
            i++;
        }

        data.println(count);
        data.close();
    }

    static class Comp implements Comparator<Integer> {
        public int compare (Integer a, Integer b) {
            return b.compareTo(a);
        }
    }
}
