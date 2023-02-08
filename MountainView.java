import java.io.*;
import java.util.*;

// USACO January 2019 Silver Problem 3: Mountain View
// Problem Statement: http://www.usaco.org/index.php?page=viewproblem2&cpid=896
/*
    A mountain (x1, y1) is not in view if there is another mountain (x2, y2) where (x1 - y1) > (x2 - y2) and (x1 + y1) < (x2 + y2)
    Sort the mountain based on the x - y value and then by x + y value
    Loop from the start and check if the x + y value is less than the max x + y value of the previous mountains, if less than then the mountain is not in view
*/
public class MountainView {
    public static void main (String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));

        int N = Integer.parseInt(data.readLine());
        TreeSet<Mountain> mountains = new TreeSet<>();
        int max_right = Integer.MIN_VALUE;
        for (int n = 0; n < N; n++) {
            StringTokenizer tokenizer = new StringTokenizer(data.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int left = x - y;
            int right = x + y;
            mountains.add(new Mountain(left, right));
        }

        int count = 0;
        for (Mountain mountain : mountains) {
            if (mountain.right <= max_right) {
                continue;
            }
            count++;
            max_right = mountain.right;
        }

        out.println(count);
        out.close();
        data.close();
    }

    static class Mountain implements Comparable <Mountain> {
        int left;
        int right;

        public Mountain (int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Mountain other) {
            if (this.left == other.left)
                return Integer.compare(other.right, right);
            else
                return Integer.compare(left, other.left);
        }
    }
}
