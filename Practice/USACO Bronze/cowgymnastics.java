import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class cowgymnastics {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("gymnastics");
		int k = io.nextInt();
		int n = io.nextInt();
		int[][] rankings = new int[k][n];
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < n; j++) {
				rankings[i][j] = io.nextInt();
			}
 		}
		List<Integer> nums = new ArrayList();
		for(int i = 0; i < n; i++) {
			nums.add(rankings[0][i]);
		}
		Collections.sort(nums);
		int[][] num2 = new int[(int)Math.pow(n, 2)][2];
		int e = 0;
		for(int i = 0; i < nums.size(); i++) {
			for(int j = 0; j < nums.size(); j++) {
				num2[e][0] = nums.get(i);
				num2[e][1] = nums.get(j);
				e++;
			}
		}
		int finalCount = 0;
		for(int i = 0; i < num2.length; i++) {
			int firstNum = num2[i][0];
			int secondNum = num2[i][1];
			int count = 0;
			int counterCount = 0;
			for(int j = 0; j < k; j++) {
				int[] currArray = rankings[j];
				int index1 = IntStream.range(0, currArray.length).filter(u -> firstNum == currArray[u]).findFirst().orElse(-1);
				int index2 = IntStream.range(0, currArray.length).filter(u -> secondNum == currArray[u]).findFirst().orElse(-1);
				if(index1 > index2) count++;
				if(index2 < index1) counterCount++;
			}
			if(count == k || counterCount == k) {
				finalCount++;
			}
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