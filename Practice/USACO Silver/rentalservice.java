import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class rentalservice {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("rental");
		int n = io.nextInt();
		int m = io.nextInt();
		int r = io.nextInt();
		Integer[] cows = new Integer[n];
		for(int i = 0; i < n; i++) {
			cows[i] = io.nextInt();
		}
		Arrays.sort(cows, Collections.reverseOrder());
		Store[] store = new Store[m];
		for(int i = 0; i < m; i++) {
			store[i] = new Store(io.nextInt(), io.nextInt());
		}
		Arrays.sort(store);
		/*io.println(Arrays.toString(cows));
		for(Store s: store) {
			io.print(s.capacity);
			io.print(" " + s.price);
			io.println();
		}
		io.println(Arrays.toString(rental));*/
		long[] maxProfit = new long[n + 1];
		int k = 0;
		for(int i = 0; i < n; i++) {
			maxProfit[i + 1] = maxProfit[i];
			while(k < m && cows[i] > 0) {
				int used = Math.min(cows[i], store[k].capacity);
				maxProfit[i + 1] += used * (long) store[k].price;
				cows[i] -= used;
				store[k].capacity -= used;
				if(store[k].capacity == 0) {
					k++;
				}
			}
		}
		Integer[] rental = new Integer[r];
		for(int i = 0; i < r; i++) {
			rental[i] = io.nextInt();
		}
		Arrays.sort(rental, Collections.reverseOrder());
		int arrIndex = n - 1;
		int rentIndex = 0;
		long prefix = 0;
		while(arrIndex >= 0 && rentIndex < r) {
			prefix += rental[rentIndex];
			maxProfit[arrIndex] += prefix;
			rentIndex++;
			arrIndex--;
		}
		//io.println(Arrays.toString(maxProfit));
		long answer = 0;
		for(int i = 0; i < maxProfit.length; i++) {
			answer = Math.max(answer, maxProfit[i]);
		}
		io.println(answer);
        io.close();
	}

	static class Store implements Comparable<Store> {
		int capacity, price;
		public Store(int _capacity, int _price) {
			capacity = _capacity;
			price = _price;
		}
		public int compareTo(Store in) {
			return Integer.compare(in.price, price);
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