import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class staticrangesum {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int q = io.nextInt();
		long[] a = new long[n];
		for(int i = 0; i < n; i++) {
			a[i] = io.nextInt();
		}
		long[] sum = new long[n + 1];
		for(int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + a[i];
		}
		for(int i = 0; i < q; i++) {
			int l = io.nextInt();
			int r = io.nextInt();
			io.println(sum[r] - sum[l]);
		}
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