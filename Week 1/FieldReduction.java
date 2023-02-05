import java.util.*;
import java.io.*;
// USACO US Open 2016 Silver Problem 1: Field Reduction
// Problem statement: http://www.usaco.org/index.php?page=viewproblem2&cpid=642
// Have a list of x vals and a list for y vals, sort them from small to large
// The max/min for x and y must be the first 4/last four on both list, so there are 4^4 ways
// Loop through the different permutations and setting the max/min x & y values, calculate the num of cow removed
// If the num of cow reduce <= 3, calculate the area and see if it's the smallest
public class FieldReduction {
    static HashSet<int[]> permutations = new HashSet<>();
    public static void main (String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
        permutate();

        int N = Integer.parseInt(data.readLine());
        Cow[] xs = new Cow[N];
        Cow[] ys = new Cow[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(data.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            Cow curr = new Cow(x, y);
            xs[i] = curr;
            ys[i] = curr;
        }
        Arrays.sort(xs, new CompX());
        Arrays.sort(ys, new CompY());

        int min_area = Integer.MAX_VALUE;
        HashSet<Cow> removed = new HashSet<>();
        for (int[] permutation : permutations) {
            int min_x = xs[permutation[0]].x;
            int max_x = xs[N - 1 - permutation[1]].x;
            int min_y = ys[permutation[2]].y;
            int max_y = ys[N - 1 - permutation[3]].y;
            for (Cow cow : xs) {
                if (cow.x < min_x || cow.x > max_x) {
                    removed.add(cow);
                }
            }
            for (Cow cow : ys) {
                if (cow.y < min_y || cow.y > max_y) {
                    removed.add(cow);
                }
            }
            if (removed.size() <= 3) {
                min_area = Math.min(min_area, Math.abs(max_x - min_x) * Math.abs(max_y-min_y));
            }
            removed.clear();
        }
        out.println(min_area);
        out.close();
        data.close();
    }
    public static void permutate() {
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                for (int c = 0; c < 4; c++) {
                    for (int d = 0; d < 4; d++) {
                        int [] list = {a, b, c, d};
                        permutations.add(list);
                    }
                }
            }
        }
    }
    static class Cow {
        int x;
        int y;
        public Cow (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class CompX implements Comparator<Cow> {
        public int compare (Cow a, Cow b) {
            return Integer.compare(a.x, b.x);
        }
    }
    static class CompY implements Comparator<Cow> {
        public int compare (Cow a, Cow b) {
            return Integer.compare(a.y, b.y);
        }
    }
}
