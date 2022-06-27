import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class whydidthecowcrosstheroad2 {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("circlecross");
		String cows = io.next();
		int answer = 0;
		for(int i = 0; i < cows.length(); i++) {
			for(int j = i + 1; j < cows.length(); j++) {
				for(int k = j + 1; k < cows.length(); k++) {
					for(int l = k + 1; l < cows.length(); l++) {
						if((cows.charAt(i) == cows.charAt(k)) && (cows.charAt(j) == cows.charAt(l))) {
							answer++;
						}
					}
				}
			}
		}
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