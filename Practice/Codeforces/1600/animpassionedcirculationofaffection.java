import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class animpassionedcirculationofaffection {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		String garland = io.next();
		char[] garArr = new char[n];
		for(int i = 0; i < n; i++) {
			garArr[i] = garland.charAt(i);
		}
		int q = io.nextInt();
		int[] m = new int[q];
		String[] c = new String[q];
		for(int i = 0; i < q; i++) {
			m[i] = io.nextInt();
			c[i] = io.next();
		}
		//io.println(Arrays.toString(m));
		//io.println(Arrays.toString(c));
		for(int i = 0; i < q; i++) {
			int num = m[i];
			char color = c[i].charAt(0);
			int left = 0, right = 0;
			int answer = 0;
			while(left < n && right < n) {
				while(right < n) {
					if(garArr[right] != color) {
						if(num == 0) break;
						num--;
					}
					right++;
				}
				answer = Math.max(answer, right - left);
				//io.println(left + " " + right);
				int temp = 1;
				if(garArr[left++] == color) {
					temp = 0;
				}
				num += temp;
			}
			io.println(answer);
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