import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class restaurantcustomers {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int[] coords = new int[n * 2];
		for(int i = 0; i < n * 2; i++) {
			coords[i] = io.nextInt();
		}
		//io.println(Arrays.toString(coords));
		int[] compress = coords.clone();
		Arrays.sort(compress);
		Map<Integer, Integer> map = new HashMap();
		for(int i = 0; i < n * 2; i++) {
			map.put(compress[i], i);
		}
		for(int i = 0; i < n * 2; i++) {
			coords[i] = map.get(coords[i]);
		}
		//io.println(Arrays.toString(coords));
		int[] sim = new int[n * 2 + 1];
		for(int i = 0; i < n * 2; i += 2) {
			sim[coords[i]]++;
			sim[coords[i + 1] + 1]--;
		}
		int prefix = 0;
		int answer = 0;
		for(int i = 0; i < sim.length; i++) {
			prefix += sim[i];
			answer = Math.max(answer, prefix);
		}
		//io.println(Arrays.toString(sim));
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