import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class hoofpaperscissors {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("hps");
		int n = io.nextInt();
		int[] hoof = new int[n + 1];
		int[] paper = new int[n + 1];
		int[] scissors = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			hoof[i] += hoof[i - 1];
			paper[i] += paper[i - 1];
			scissors[i] += scissors[i - 1];
			String gesture = io.next();
			if(gesture.equals("H")) {
				hoof[i]++;
			} else if(gesture.equals("P")) {
				paper[i]++;
			} else if(gesture.equals("S")) {
				scissors[i]++;
			}
		}
		
		int maximum = 0;
		for(int i = 0; i <= n; i++) {
			int HSum = hoof[i] - hoof[0];
			int PSum = paper[i] - paper[0];
			int SSum = scissors[i] - scissors[0];
			int HSum2 = hoof[n] - hoof[i];
			int PSum2 = paper[n] - paper[i];
			int SSum2 = scissors[n] - scissors[i];
			int[] sum1 = {HSum, PSum, SSum};
			int[] sum2 = {HSum2, PSum2, SSum2};
			int curMax = Math.max(HSum, Math.max(PSum, SSum)) + Math.max(HSum2, Math.max(PSum2, SSum2));
			maximum = Math.max(maximum, curMax);
		}
		io.println(maximum);
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