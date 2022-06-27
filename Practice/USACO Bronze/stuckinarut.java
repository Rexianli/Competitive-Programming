import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class stuckinarut {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		Object[] answers = new Object[n];
		int[][] coords = new int[n][2];
		String[] direction = new String[n];
		for(int i = 0; i < n; i++) {
			direction[i] = io.next();
			coords[i][0] = io.nextInt();
			coords[i][1] = io.nextInt();
		}
		//io.println(Arrays.toString(direction)); io.println(Arrays.deepToString(coords));
		for(int i = 0; i < n; i++) {
			int conditionsFulfilled = 0;
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				if(direction[i] == "N") {
					if(direction[j] == "E") {
						if(coords[j][0] > coords[i][0] || coords[j][1] < coords[i][1] || coords[i][0] - coords[j][0] == coords[j][1] - coords[i][1] || coords[i][0] - coords[j][0] < coords[j][1] - coords[i][1])
						if(coords[j][0] > coords[i][0]) {
							conditionsFulfilled++;
							continue;
						} else if(coords[j][1] < coords[i][1]) {
							conditionsFulfilled++;
							continue;
						} else {
							if(coords[i][0] - coords[j][0] == coords[j][1] - coords[i][1]) {
								conditionsFulfilled++;
								continue;
							} else if(coords[i][0] - coords[j][0] < coords[j][1] - coords[i][1]) {
								conditionsFulfilled++;
								continue;
							} else {
								continue;
							}
						}
						
					} else {
						if(coords[i][0] == coords[j][0]) {
							continue;
						} else {
							conditionsFulfilled++;
							continue;
						}
					}
				} else {
					if(direction[j] == "E") {
						if(coords[i][1] == coords[j][1]) {
							continue;
						} else {
							conditionsFulfilled++;
							continue;
						}
					} else {
						if(coords[j][0] < coords[i][0]) {
							conditionsFulfilled++;
							continue;
						} else if(coords[j][1] > coords[i][1]) {
							conditionsFulfilled++;
							continue;
						} else {
							if(coords[i][0] - coords[j][0] == coords[j][1] - coords[i][1]) {
								conditionsFulfilled++;
								continue;
							} else if(coords[i][0] - coords[j][0] < coords[j][1] - coords[i][1]) {
								conditionsFulfilled++;
								continue;
							} else {
								continue;
							}
						}
					}
				}
			}
			if(conditionsFulfilled == 5) {
				continue;
			} else {
				answers[i] = "Infinity";
			}
		}
		io.println(Arrays.toString(answers));
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