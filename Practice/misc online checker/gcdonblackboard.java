import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class gcdonblackboard {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int[] original = new int[n + 1];
		int[] left = new int[n + 1];
		int[] right = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			original[i] = io.nextInt();
			left[i] = original[i];
			right[i] = original[i];
		}
		for(int i = 1; i <= n; i++) {
			left[i] = gcd(left[i], left[i - 1]);
		}
		for(int i = n - 1; i > 0; i--) {
			right[i] = gcd(right[i], right[i + 1]);
		}
		int max = 0;
		for(int i = 1; i < n; i++) {
			int m = gcd(left[i - 1], right[i + 1]);
			max = Math.max(max, m);
		}
		io.println(max);
        io.close();
	}

	static int gcd(int n1, int n2) {
    	if (n2 == 0) {
        	return n1;
   		}
    	return gcd(n2, n1 % n2);
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