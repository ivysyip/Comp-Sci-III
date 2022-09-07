import java.io.*;
import java.util.*;

// USACO US Open 2018 Silver Problem 2: Lemonade Line
// Problem statement: http://www.usaco.org/index.php?page=viewproblem2&cpid=835
/*
    Let the larger number of cows to get in line first to make sure it's the most minimum amount of cows.
    Sort the cows and starting counting from the largest ones and check if it meets the conditions.
*/
public class LemonadeLine {
    public static void main (String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));

        int N = Integer.parseInt(data.readLine());
        int [] cows = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(data.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(cows);

        int count = 0;
        for (int i = N-1; i >= 0; i--) {
            if (cows[i] >= count)
                count++;
            else
                break;
        }

        out.println(count);
        data.close();
        out.close();
    }
}
