import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class hoofball {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("hoofball");
		int n = io.nextInt();
		int[] nums = new int[n];
		for(int i = 0; i < n; i++) {
			nums[i] = io.nextInt();
		}
		Arrays.sort(nums);
		int answer = 0;
		boolean[] direction = new boolean[n];
		//false = left, true = right
		for(int i = 1; i < n - 1; i++) {
			if(nums[i] - nums[i - 1] > nums[i + 1] - nums[i]) {
				direction[i] = true;
			}
		}
		int i = 0;
		while(i < n) {
			int rt = 0;
			while(direction[i]) {
				i++;
				rt++;
			}
			int lt = 0;
			while(i < n && !direction[i]) {
				i++;
				lt++;
			}
			if(rt > 1 && lt > 1) {
				answer += 2;
			} else {
				answer++;
			}
		}
		if(n <= 2) {
			io.println(1);
		} else {
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