import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class bovinegenomics {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("cownomics");
		int n = io.nextInt();
		int m = io.nextInt();
		String[] arr = new String[n];
		String[] arr2 = new String[n];
		for(int i = 0; i < n; i++) {
			arr[i] = io.next();
		}
		for(int i = 0; i < n; i++) {
			arr2[i] = io.next();
		}
		int finalCount = 0;
		for(int i = 0; i < m; i++) {
			List<Character> character = new ArrayList();
			int count = 0;
			for(int j = 0; j < n; j++) {
				character.add(arr[j].charAt(i));
			}
			for(int j = 0; j < n; j++) {
				char current = arr2[j].charAt(i);
				for(int k = 0; k < character.size(); k++) {
					if(current == character.get(k)) count++;
				}
			}
			if(count == 0) finalCount++;
		}
		io.println(finalCount);
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