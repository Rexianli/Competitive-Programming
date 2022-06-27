import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class loadbalancing {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("balancing");
		int n = io.nextInt();
		int b = io.nextInt();
		int[][] coords = new int[n][2];
		for(int i = 0; i < n; i++) {
			coords[i][0] = io.nextInt();
			coords[i][1] = io.nextInt();
		}
		int minimum = Integer.MAX_VALUE;
		int maximum = 0;
		for(int i = 0; i < b; i++) {
			if(i % 2 != 0) continue;
			for(int j = 0; j < b; j++) {
				if(i % 2 != 0) continue;
				int topleft = 0; int topright = 0; int bottomleft = 0; int bottomright = 0;
				for(int k = 0; k < coords.length; k++) {
					int currentX = coords[k][0];
					int currentY = coords[k][1];
					if(currentX > i && currentY > j) {
						topright++;
					} else if(currentX > i && currentY < j) {
						bottomright++;
					} else if(currentX < i && currentY < j) {
						bottomleft++;
					} else {
						topleft++;
					}
				}
				maximum = max(topright, topleft, bottomright, bottomleft);
				minimum = Math.min(maximum, minimum);
			}
		}
		io.println(minimum);
        io.close();
	}

	public static int max(int a, int b, int c, int d) {
		int max = a;
		if(b > max)
			max = b;
		if(c > max)
			max = c;
		if(d > max)
			max = d;
		return max;
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