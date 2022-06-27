import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class diamondcollector {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("diamond");
		int n = io.nextInt();
		int k = io.nextInt();
		int[] diamonds = new int[n];
		for(int i = 0; i < n; i++) {
			diamonds[i] = io.nextInt();
		}
		Arrays.sort(diamonds);
		//io.println(Arrays.toString(diamonds));
		int[] left = new int[n];
		int[] right = new int[n];
		int j = 0;
		for(int i = 0; i < n; i++) {
			while(j < diamonds.length && diamonds[i] - diamonds[j] > k) {
				j++;
			}
			left[i] = i - j + 1;
			//io.println("i" + " " + "j");
			if(i > 0) left[i] = Math.max(left[i], left[i - 1]);
		}
		j = n - 1;
		for(int i = n - 1; i >= 0; i--) {
			while(j >= 0 && diamonds[j] - diamonds[i] > k) {
				j--;
			}
			right[i] = j - i + 1;
			if(i < n - 1) right[i] = Math.max(right[i], right[i + 1]);
		}
		int answer = 0;
		for(int i = 0; i < n - 1; i++) {
			answer = Math.max(answer, left[i] + right[i + 1]);
		}
		io.println(answer);
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