import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class madscientist {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("breedflip");
		int n = io.nextInt();
		String a = io.next();
		String b = io.next();
		char[] charArr1 = new char[n];
		for(int i = 0; i < n; i++) {
			charArr1[i] = a.charAt(i);
		}
		char[] charArr2 = new char[n];
		for(int i = 0; i < n; i++) {
			charArr2[i] = b.charAt(i);
		}
		int answer = 0;
		While:
		while(!Arrays.equals(charArr1, charArr2)) {
			answer++;
			int index = -1;
			for(int i = 0; i < n; i++) {
				if(charArr1[i] != charArr2[i]) {
					if(index != -1) {
						if(i != index) continue While;
					}
					if(index == -1) {
						index = i;
					}
					if(charArr2[i] == 'H') {
						charArr2[i] = 'G';
					} else {
						charArr2[i] = 'H';
					}
					index++;
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