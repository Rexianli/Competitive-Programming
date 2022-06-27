import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class mowingthefield {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("mowing");
		int n = io.nextInt();
		int NS = 0;
		int EW = 0;
		List<int[]> traveled = new ArrayList<int[]>();
		traveled.add(new int[] {0, 0});
		for(int i = 0; i < n; i++) {
			String direction = io.next();
			int distance = io.nextInt();
			for(int j = 0; j < distance; j++) {
				if(direction.equals("N")) {
					NS++;
				} else if(direction.equals("S")) {
					NS--;
				} else if(direction.equals("E")) {
					EW++;
				} else {
					EW--;
				}
				traveled.add(new int[] {NS, EW});
			}
		}
		int minimum = Integer.MAX_VALUE;

		for(int i = 0; i < traveled.size(); i++) {
			for(int j = 0; j < traveled.size(); j++) {
				if(j == i) continue;
				String str1 = Arrays.toString(traveled.get(i));
				String str2 = Arrays.toString(traveled.get(j));
				if(str1.equals(str2)) {
					int mini = Math.abs(traveled.indexOf(traveled.get(j)) - traveled.indexOf(traveled.get(i)));
					if(mini < minimum) {
						minimum = mini;
					}
				}
			}
		}
		/*for(int[] row : traveled) {
        	io.println("Row = " + Arrays.toString(row));
    	}*/
		io.println(minimum);
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
