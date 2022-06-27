import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class subsequencessummingtosevens {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("div7");
		int n = io.nextInt();
		int[] ids = new int[n];
		for(int i = 0; i < n; i++) {
			ids[i] = io.nextInt();
		}

		long[] prefix = new long[n];
		prefix[0] = ids[0];
		for(int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + ids[i];
		}
		long[] mod7 = new long[n];
		Set<Long> set = new HashSet();
		for(int i = 0; i < n; i++) {
			mod7[i] = prefix[i] % 7;
			set.add(mod7[i]);
		}
		List<Long> setList = new ArrayList();
		setList.addAll(set);
		//io.println(Arrays.toString(mod7));
		int max = 0;
		for(int i = 0; i < setList.size(); i++) {
			long current = setList.get(i);
			int firstIndex = -1;
			for(int j = 0; j < n; j++) {
				if(firstIndex == -1 && mod7[j] == current) {
					firstIndex = j;
				} else {
					if(mod7[j] == current) {
						max = Math.max(max, j - firstIndex);
					}
				}
			}
		}
		io.println(max);
        io.close();
	}

	/*
	[0, 3, 5, 1, 6, 2, 14, 10]
	[0, 3, 8, 9, 15, 17, 31, 41]
	*/
	

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