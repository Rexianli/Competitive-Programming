import java.io.*;
import java.util.*;

public class blockedbillboard2 {
	
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("billboard");
		int bl_a_x = io.nextInt(), bl_a_y = io.nextInt(), tr_a_x = io.nextInt(), tr_a_y = io.nextInt();
		int bl_b_x = io.nextInt(), bl_b_y = io.nextInt(), tr_b_x = io.nextInt(), tr_b_y = io.nextInt();
		int areaA = (tr_a_y - bl_a_y) * (tr_a_x - bl_a_x);
		int areaB = (tr_b_y - bl_b_y) * (tr_b_x - bl_b_x);
		if (bl_a_x >= tr_b_x || tr_a_x <= bl_b_x 
		|| bl_a_y >= tr_b_y || tr_a_y <= bl_b_y) {
			io.print(areaA);
		} else {
			io.print(areaA);
		}
		io.close();
	}

	//BeginCodeSnip{Kattio}
	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;
	
		// standard input
		public Kattio() { this(System.in, System.out); }
		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}
		// USACO-style file input
		public Kattio(String problemName) throws IOException {
			super(new FileWriter(problemName + ".out"));
			r = new BufferedReader(new FileReader(problemName + ".in"));
		}
	
		// returns null if no more input
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
	//EndCodeSnip
}