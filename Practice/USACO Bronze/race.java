import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class race {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("race");
		int k = io.nextInt();
		int n = io.nextInt();
		int[] x = new int[n];
		for(int i = 0; i < n; i++) {
			x[i] = io.nextInt();
		}
		For:
		for(int i = 0; i < n; i++) {
			int curX = x[i];
			int distTraveledIncreased = 0;
			int distTraveledDecreased = 0;
			int answer = 0;
			int currSpeed = 1;
			while(true) {
				distTraveledIncreased += currSpeed;
				answer++;
				if(distTraveledDecreased + distTraveledIncreased >= k) {
					io.println(answer);
					continue For;
				}
				if(currSpeed >= curX) {
					distTraveledDecreased += currSpeed;
					answer++;
					if(distTraveledDecreased + distTraveledIncreased >= k) {
						io.println(answer);
						continue For;
					}
				}
				currSpeed++;
			}
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