import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class nontransitivedice {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		for(int i = 0; i < n; i++) {
			int[] arr1 = {io.nextInt(), io.nextInt(), io.nextInt(), io.nextInt()};
			int[] arr2 = {io.nextInt(), io.nextInt(), io.nextInt(), io.nextInt()};
			String str = "no";
			int count = 0;
			for(int j = 1; j <= 10; j++) {
				for(int l = 1; l <= 10; l++) {
					for(int k = 1; k <= 10; k++) {
						for(int m = 1; m <= 10; m++) {
							int[] arr3 = {j, l, k, m};
							if(beats(arr1, arr2) && beats(arr2, arr3) && beats(arr3, arr1)) {
								str = "yes";
							} else if(beats(arr2, arr1) && beats(arr1, arr3) && beats(arr3, arr2)) {
								str = "yes";
							}
						}
					}
				}
			}
			io.println(str);
		}
		io.close();	
	}	
	static boolean beats(int[] arr1, int[] arr2) {
		int chance1 = 0;
		int chance2 = 0;
		for(int j = 0; j < arr1.length; j++) {
			for(int l = 0; l < arr2.length; l++) {
				if(arr1[j] > arr2[l]) {
					chance1++;
				} else if(arr1[j] < arr2[l]) {
					chance2++;
				}
			}
		}
		if(chance1 > chance2) {
			return true;
		} else if(chance2 > chance1) {
			return false;
		} else {
			return false;
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