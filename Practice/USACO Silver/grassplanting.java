import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class grassplanting {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("planting");
		int n = io.nextInt();
		int[] nodes = new int[n + 1];
		for(int i = 0; i < n - 1; i++) {
			int a = io.nextInt();
			int b = io.nextInt();
			nodes[a]++;
			nodes[b]++;
		}
		List<Integer> list = IntStream.of(nodes).boxed().collect(Collectors.toList());
		io.println(Collections.max(list) + 1);
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