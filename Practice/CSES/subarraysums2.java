import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;
 
public class subarraysums2 {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int sum = io.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = io.nextInt();
		}
		long prefix = 0;
		long answer = 0;
		Map<Long, Integer> map = new HashMap();
		map.put((long) 0, 1);
		for(int i = 0; i < n; i++) {
			int num = arr[i];
			prefix += num;
			if(map.containsKey(prefix - sum)) {
				answer += map.get(prefix - sum);
			}
			if(!map.containsKey(prefix)) {
				map.put(prefix, 1);
			} else {
				map.put(prefix, map.get(prefix) + 1);
			}
		}
		io.println(map);
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