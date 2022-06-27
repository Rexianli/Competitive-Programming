import java.io.*;
import java.util.*;

public class bucketlist {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("blist");
		int n = io.nextInt();
		ArrayList<Integer> items = new ArrayList();
		ArrayList<Integer> bucketItems = new ArrayList();
		for(int i = 0; i < n; i++) {
			int time1 = io.nextInt();
			int time2 = io.nextInt();
			int buckets = io.nextInt();
			items.add(time1);
			items.add(time2);
			bucketItems.add(buckets);
		}
		int max = Collections.max(items);
		int min = Collections.min(items);
		int[] bucketArray = new int[bucketItems.size() + 1];
		for(int i = 0; i < bucketItems.size(); i++) {
			bucketArray[i] = bucketItems.get(i);
		}
		int[] array = new int[max + 1];
		int halfSize = items.size() / 2;
		int k = 0;
		int y = 0;
		for(int i = 0; i < halfSize; i++) {
			int temp1 = items.get(k);
			int temp2 = items.get(k + 1);
			for(int j = temp1; j <= temp2; j++) {
				array[j] += bucketArray[y];
			}
			y++;
			k += 2;
		}
		int max2 = array[0];
		for (int i = 0; i < array.length; i++)
             if (array[i] > max2)
                 max2 = array[i];
		io.println(max2);
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