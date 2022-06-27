import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class whydidthecowcrosstheroad2 {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("maxcross");
		int n = io.nextInt();
		int k = io.nextInt();
		int b = io.nextInt();
		Map<Integer, Integer> broken = new HashMap();
		for(int i = 0; i < b; i++) {
			broken.put(io.nextInt(), 0);
		}
		int min = Integer.MAX_VALUE;
		int count = 0;
		for(int i = 1; i <= k; i++) {
			if(broken.containsKey(i)) count++;
		}
		min = Math.min(min, count);
		for(int i = 2; i <= n; i++) {
			if(i > n - k + 1) break;
			if(broken.containsKey(i - 1)) {
				count--;
			}
			if(broken.containsKey(i + k - 1)) {
				count++;
			}
			min = Math.min(min, count);
		}
		io.println(min);
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