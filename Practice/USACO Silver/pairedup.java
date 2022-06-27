import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class pairedup {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("pairup");
		int n = io.nextInt();
		Cow[] cowArr = new Cow[n];
		for(int i = 0; i < n; i++) {
			cowArr[i] = new Cow(io.nextInt(), io.nextInt());
		}
		Arrays.sort(cowArr);
		int left = 0, right = n - 1;
		int ans = 0;
		while(left != right) {
			ans = Math.max(ans, cowArr[left].output + cowArr[right].output);
			if(cowArr[left].quantity > cowArr[right].quantity) {
				cowArr[left].quantity -= cowArr[right].quantity;
				right--;
			} else if(cowArr[left].quantity < cowArr[right].quantity) {
				cowArr[right].quantity -= cowArr[left].quantity;
				left++;
			} else {
				right--;
				left++;
			}
		}
		io.println(ans);
        io.close();
	}

	static class Cow implements Comparable<Cow> {
		int quantity, output;
		public Cow(int quantity, int output) {
			this.quantity = quantity;
			this.output = output;
		}
		public int compareTo(Cow x) {
			return Integer.compare(this.output, x.output);
		}
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