import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class sleepycowherding {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("herding");
		int n = io.nextInt();
		int[] cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = io.nextInt();
		}
		Arrays.sort(cows);
		int min = Integer.MAX_VALUE;
		//io.println(Arrays.toString(cows));
		if(cows[n - 2] - cows[0] == n - 2 && cows[n - 1] - cows[n - 2] > 2 || cows[n - 1] - cows[1] == n - 2 && cows[1] - cows[0] > 2) {
			min = 2;
		} else {
			int j = 0;
			for(int i = 0; i < n; i++) {
				while(j < n - 1 && cows[j + 1] - cows[i] < n) {
					j++;
				}
				min = Math.min(min, n - (j - i + 1));
			}
		}
 		io.println(min);
		io.println(Math.max(cows[n - 2] - cows[0], cows[n - 1] - cows[1]) - (n - 2));
        io.close();
	}

	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;
		public Kattio() { this(System.in, System.out); }
		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}
		public Kattio(String problemName) throws IOException {
			super(new FileWriter(problemName + ".out"));
			r = new BufferedReader(new FileReader(problemName + ".in"));
		}
		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) { }
			return null;
		}
		public int nextInt() { return Integer.parseInt(next()); }
		public double nextDouble() { return Double.parseDouble(next()); }
		public long nextLong() { return Long.parseLong(next()); }
	}
}