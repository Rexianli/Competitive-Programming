import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class theyareeverywhere {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		char[] arr = new char[n];
		Set<Character> set = new HashSet();
		List<Character> list = new ArrayList();
		String str = io.next();
		for(int i = 0; i < n; i++) {
			arr[i] = str.charAt(i);
			set.add(arr[i]);
		}
		list.addAll(set);
		//io.println(list);
		Map<Character, Integer> caught = new HashMap();
		for(int i = 0; i < list.size(); i++) {
			caught.put(list.get(i), 0);
		}
		int left = 0, right = 0;
		while(caught.values().contains(0)) {
			caught.put(arr[right], caught.get(arr[right]) + 1);
			right++;
			//io.println(caught);
		}
		int min = right;
		//io.println(right);
		//right--;
		for(int i = right; i < arr.length; i++) {
			caught.put(arr[i], caught.get(arr[i]) + 1);
			while(true) {
				if(caught.get(arr[left]) > 1) {
					//io.println(left + " " + i);
					//io.println(caught);
					caught.put(arr[left], caught.get(arr[left]) - 1);
					left++;
				} else {
					break;
				}
			}
			//io.println(left + " " + i);
			//io.println(caught);
			min = Math.min(min, i - left);
		}
		if(right == arr.length) {
			right--;
			while(true) {
				if(caught.get(arr[left]) > 1) {
					//io.println(left + " " + i);
					//io.println(caught);
					caught.put(arr[left], caught.get(arr[left]) - 1);
					left++;
				} else {
					break;
				}
			}
			min = Math.min(min, right - left);
		}
		//io.println(caught);
		io.println(min + 1);
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