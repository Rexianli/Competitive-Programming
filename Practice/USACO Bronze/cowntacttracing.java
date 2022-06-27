import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class cowntacttracing {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("tracing");
		int n = io.nextInt();
		int t = io.nextInt();
		String line = io.next();
		boolean[] infected = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(line.charAt(i) == '1') {
				infected[i] = true;
			} else {
				infected[i] = false;
			}
		}
		int[][] shakes = new int[t][3];
		for(int i = 0; i < t; i++) {
			shakes[i][0] = io.nextInt();
			int[] temp = new int[2];
			temp[0] = io.nextInt() - 1;
			temp[1] = io.nextInt() - 1;
			Arrays.sort(temp);
			shakes[i][1] = temp[0];
			shakes[i][2] = temp[1];
		}
		Arrays.sort(shakes, (a, b) -> Double.compare(a[0], b[0]));
		int solutionNum = 0;
		int Kmin = Integer.MAX_VALUE;
		int Kmax = 0;
		for(int i = 0; i < n; i++) {
			if(!infected[i]) continue;
			int min = Integer.MAX_VALUE;
			int max = 0;
			for(int k = 0; k <= 250; k++) {
				boolean[] current = new boolean[n];
				current[i] = true;
				int[] counts = new int[n];
				for(int j = 0; j < t; j++) {
					int current1 = shakes[j][1];
					int current2 = shakes[j][2];
					if(current[current1] && current[current2]) {
						counts[current1]++;
						counts[current2]++;
					} else if(current[current1] && !current[current2] && counts[current1] < k) {
						current[current2] = true;
						counts[current1]++;
					} else if(!current[current1] && current[current2] && counts[current2] < k) {
						current[current1] = true;
						counts[current2]++;
					}
				}
				boolean trueFalse = Arrays.equals(current, infected);
				if(trueFalse) {
					min = Math.min(min, k);
					max = Math.max(max, k);
				}
			}
			if(min != Integer.MAX_VALUE) {
				solutionNum++;
				Kmin = Math.min(min, Kmin);
				Kmax = Math.max(max, Kmax);
			}
		}
		if(Kmax == 250) {
			io.println(solutionNum + " " + Kmin + " " + "Infinity");
		} else {
			io.println(solutionNum + " " + Kmin + " " + Kmax);
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