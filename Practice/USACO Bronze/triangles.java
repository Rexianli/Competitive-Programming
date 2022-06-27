import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class triangles {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("triangles");
		int n = io.nextInt();
		int[][] coords = new int[n][2];
		for(int i = 0; i < n; i++) {
			coords[i][0] = io.nextInt();
			coords[i][1] = io.nextInt();
		}
		int answer = 0;
		for(int i = 0; i < n; i++) {
			int a_x = coords[i][0]; int a_y = coords[i][1];
			for(int j = 0; j < n; j++) {
				int b_x = coords[j][0]; int b_y = coords[j][1];
				for(int k = 0; k < n; k++) {
					int c_x = coords[k][0]; int c_y = coords[k][1];
					int area = Math.abs((a_x  * (b_y - c_y)) + (b_x * (c_y - a_y)) + (c_x * (a_y - b_y )));
					if((a_x == b_x || a_x == c_x || c_x == b_x) && (a_y == b_y || a_y == c_y || c_y == b_y)) {
						answer = Math.max(answer, area);
					}
				}
			}
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