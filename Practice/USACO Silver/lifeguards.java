import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class lifeguards {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("lifeguards");
		int n = io.nextInt();
		TreeSet<Integer> set = new TreeSet();
		lifeguard[] arr = new lifeguard[n * 2];
		for(int i = 0; i < n; i++) {
			arr[2 * i] = new lifeguard(io.nextInt(), i);
			arr[2 * i + 1] = new lifeguard(io.nextInt(), i);
		}
		Arrays.sort(arr);
		/*for(int i = 0; i < n * 2; i++) {
			io.println(arr[i].time + " " + arr[i].index);
		}*/
		int last = 0;
		int timeCovered = 0;
		int[] alone = new int[n];
		for(lifeguard guard: arr) {
			if(set.size() == 1) {
				alone[set.first()] += guard.time - last;
			}
			if(!set.isEmpty()) {
				timeCovered += guard.time - last;
			}
			if(set.contains(guard.index)) {
				set.remove(guard.index);
			} else {
				set.add(guard.index);
			}
			last = guard.time;
		}
		int answer = 0;
		for(int i = 0; i < n; i++) {
			int current = alone[i];
			answer = Math.max(answer, timeCovered - current);
		}
		io.println(answer);
        io.close();
	}

	static class lifeguard implements Comparable<lifeguard> {
		public int time, index;
		public lifeguard(int _time, int _index) {
			time = _time;
			index = _index;
		}
 		public int compareTo(lifeguard l) {
			return time - l.time;
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