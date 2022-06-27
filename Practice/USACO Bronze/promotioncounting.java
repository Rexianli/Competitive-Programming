import java.io.*;
import java.util.*;

public class promotioncounting {
	public static void main(String[] args) throws IOException{
		Kattio io = new Kattio("promote");
		int BB = io.nextInt();
		int BA = io.nextInt();
		int SB = io.nextInt();
		int SA = io.nextInt();
		int GB = io.nextInt();
		int GA = io.nextInt();
		int PB = io.nextInt();
		int PA = io.nextInt();
		int goldPlat = PA - PB;
		int silvGold = PA - PB + GA - GB;
		int bronSilv = SA - SB + PA - PB + GA - GB;
		io.println(bronSilv);
		io.println(silvGold);
		io.println(goldPlat);
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
