import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class breedcounting {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("bcount");
		int n = io.nextInt();
		int q = io.nextInt();
		int[] holsteins = new int[n + 1];
		int[] guernseys = new int[n + 1];
		int[] jerseys = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			holsteins[i] += holsteins[i - 1];
			guernseys[i] += guernseys[i - 1];
			jerseys[i] += jerseys[i - 1];
			int breed = io.nextInt();
			if(breed == 1) {
				holsteins[i]++;
			} else if(breed == 2) {
				guernseys[i]++;
			} else if(breed == 3) {
				jerseys[i]++;
			}
		}
		/*io.println(Arrays.toString(holsteins));
		io.println(Arrays.toString(guernseys));
		io.println(Arrays.toString(jerseys));
		
		
		*/
		for(int i = 0; i < q; i++) {
			int l = io.nextInt();
			int r = io.nextInt();
			io.print(holsteins[r] - holsteins[l - 1] + " ");
			io.print(guernseys[r] - guernseys[l - 1] + " ");
			io.print(jerseys[r] - jerseys[l - 1]);
			io.println();
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