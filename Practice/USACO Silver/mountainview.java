import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class mountainview {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("mountains");
		int n = io.nextInt();
		Mountain[] mountArr = new Mountain[n];
		for(int i = 0; i < n; i++) {
			int x = io.nextInt();
			int y = io.nextInt();
			mountArr[i] = new Mountain(x - y, x + y);
		}
		Arrays.sort(mountArr);
		int obscured = 0;
		int max = -1;
		for(Mountain mtn: mountArr) {
			io.println(mtn.x + " " + mtn.y);
			if(mtn.y > max) {
				obscured++;
				max = mtn.y;
			}
		}
		io.println(obscured);
        io.close();
	}

	static class Mountain implements Comparable<Mountain> {
		int x, y;
		public Mountain(int _x, int _y) {
			x = _x;
			y = _y;
		}
		public int compareTo(Mountain in) {
			if(x != y) return Integer.compare(x, in.x);
			return Integer.compare(in.y, y);
		}
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