import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class spacedout {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				grid[i][j] = io.nextInt();
			}
		}
		int rowsMax = 0;
		int columnMax = 0;
		for(int i = 0; i < n; i++) {
			int odd = 0;
			int even = 0;
			for(int j = 0; j < n; j++) {
				if(j % 2 == 0) {
					even += grid[i][j];
				} else {
					odd += grid[i][j];
				}
			}
			rowsMax += Math.max(odd, even);
		}
		for(int j = 0; j < n; j++) {
			int odd = 0;
			int even = 0;
			for(int i = 0; i < n; i++) {
				if(i % 2 == 0) {
					even += grid[i][j];
				} else {
					odd += grid[i][j];
				}
			}
			columnMax += Math.max(odd, even);
		}
		io.println(Math.max(rowsMax, columnMax));
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