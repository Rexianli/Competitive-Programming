import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class paintingthebarn {
	static final int WIDTH = 1000;
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("paintbarn");
		int rectNum = io.nextInt();
		int paintReq = io.nextInt();
		int[][] barn = new int[WIDTH + 1][WIDTH + 1];
		for(int i = 0; i < rectNum; i++) {
			int start_x = io.nextInt();
			int start_y = io.nextInt();
			int end_x = io.nextInt();
			int end_y = io.nextInt();
			barn[start_x][start_y]++;
			barn[end_x][end_y]++;
			barn[start_x][end_y]--;
			barn[end_x][start_y]--;
		}
		printArr(barn);
		int valid_area = 0;
		for(int x = 0; x <= WIDTH; x++) {
			for(int y = 0; y <= WIDTH; y++) {
				if(x > 0) barn[x][y] += barn[x - 1][y];
				if(y > 0) barn[x][y] += barn[x][y - 1];
				if(x > 0 && y > 0) barn[x][y] -= barn[x - 1][y - 1];
				if(barn[x][y] == paintReq) {
					valid_area++;
				}
			}
		}
		System.out.println();
		printArr(barn);
		io.println(valid_area);
        io.close();
	}

	static void printArr(int[][] barn) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(barn[i][j]);
			}
			System.out.println();
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