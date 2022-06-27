import java.io.*;
import java.util.*;

public class goodsubarrays {
	static long solve (int arrayLength, String stringArray) {
		int[] array = new int[arrayLength + 1];
		for(int x = 1; x <= arrayLength; x++) {  
			array[x] = stringArray.charAt(x - 1) - '0';
		}
		for(int x = 1; x <= arrayLength; x++) { 
			array[x] += array[x - 1];
		}
		Map<Integer, Long> sumDist = new HashMap<>();
		for(int x = 0; x <= arrayLength; x++) {  
			if (sumDist.containsKey(array[x] - x)) {  
				sumDist.put(array[x] - x, sumDist.get(array[x] - x) + 1);
			}
			else {  
				sumDist.put(array[x] - x, (long) 1);
			}
		}
		long goodArrays = 0;
		for(int key : sumDist.keySet()) {  
			long f = sumDist.get(key);
			goodArrays += f * (f - 1) / 2;
		}
		return goodArrays;
	}

	public static void main(String[] args) {
		Kattio io = new Kattio();
		int t = io.nextInt();
		for (int x = 0; x < t; x++) { 
			int arrayLength = io.nextInt();
			String array = io.next();
			io.println(solve(arrayLength, array));
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
			super(problemName + ".out");
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