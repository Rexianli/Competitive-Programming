import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class herdle {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		String[] correct = new String[3];
		String[] guess = new String[3];	
		for(int i = 0; i < 3; i++) {
			correct[i] = io.next();
		}
		for(int i = 0; i < 3; i++) {
			guess[i] = io.next();
		}
		int green = 0;
		List<Character> chars = new ArrayList();
		List<Character> chars2 = new ArrayList();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(correct[i].charAt(j) == guess[i].charAt(j)) {
					green++;
					continue;
				}
				chars.add(correct[i].charAt(j));
				chars2.add(guess[i].charAt(j));
			}
		}
		io.println(chars);
		List<Character> charsClone = new ArrayList();
		charsClone.addAll(chars);
		chars.retainAll(chars2);
		io.println(chars);
		chars2.retainAll(charsClone);
		io.println(green);
		int max = Math.min(chars.size(), chars2.size());
		io.println(max);
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