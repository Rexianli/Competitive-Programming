import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;
 
public class sumofthreevalues {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int x = io.nextInt();
		nums[] arr = new nums[n];
		for(int i = 0; i < n; i++) {
			arr[i] = new nums(io.nextInt(), i + 1);
		}
		Arrays.sort(arr);
		for(int i = 0; i < n; i++) {
			int left = 0, right = n - 1;
			while(left != right) {
				int target = x - arr[i].value;
				if(left != i && right != i && arr[left].value + arr[right].value == target) {
					io.println(arr[i].index + " " + arr[left].index + " " + arr[right].index);
					io.close();
					System.exit(0);
				}
				if(arr[left].value + arr[right].value >= target) {
					right--;
				} else {
					left++;
				}
			}
		}
		io.println("IMPOSSIBLE");
        io.close();
	}
 
	static class nums implements Comparable<nums> {
		int value, index;
		public nums(int value, int index) {
			this.value = value;
			this.index = index;
		}
		public int compareTo(nums y) {
			return Integer.compare(this.value, y.value);
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