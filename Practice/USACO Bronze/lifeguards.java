import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class lifeguards {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("lifeguards");
		int n = io.nextInt();
		int[][] time = new int[n][2];
		for(int i = 0; i < n; i++) {
			time[i][0] = io.nextInt();
			time[i][1] = io.nextInt();
		}
		ArrayList<Integer> nums = new ArrayList();
		for(int i = 0; i < n; i++) {
			nums.add(time[i][0]);
			nums.add(time[i][1]);
		}
		int answer = 0;
		int min = Collections.min(nums); int max = Collections.max(nums);
		for(int i = 0; i < n; i++) {
			int count = 0;
			boolean[] tf = new boolean[max];
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				int start = time[j][0]; int end = time[j][1];
				for(int k = start; k < end; k++) {
					if(!tf[k]) {
						tf[k] = true;
						count++;
					}
				}
			}
			answer = Math.max(answer, count);
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