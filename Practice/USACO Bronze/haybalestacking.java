import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class haybalestacking {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int k = io.nextInt();
		int[] stacks = new int[n + 1];
		for(int i = 0; i < k; i++) {
			int start = io.nextInt() - 1;
			int end = io.nextInt() - 1;
			stacks[start]++;
			stacks[end + 1]--;
		}
		int[] answer = new int[n + 1];
		int prefix = 0;
		for(int i = 0; i < n; i++) {
			prefix += stacks[i];
			answer[i] = prefix;
		}
		Arrays.sort(answer);
		io.println(answer[answer.length / 2]);
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