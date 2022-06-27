import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class thelazycow {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("lazy");
		int n = io.nextInt();
		int k = io.nextInt();
		int[][] rotated = new int[n * 2 + 1][n * 2 + 1];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				rotated[i + j + 1][i - j + n] = io.nextInt();
			}
		}
		int n1 = n;
		n = n * 2 + 1;
		int answer = 0;
		int[][] pfx = new int[n][n];
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < n; j++) {
				pfx[i][j] = rotated[i][j] + pfx[i - 1][j] + pfx[i][j - 1] - pfx[i - 1][j - 1];
			}	
		}
		k = Math.min(2 * k + 1, 2 * n);
		//printArr(pfx);
		/*int i = 5, j = 5;
		int fromRow = j, toRow = j + k - 1, fromCol = i, toCol = i + k - 1;
		int current = pfx[toRow][toCol] - pfx[fromRow - 1][toCol] - pfx[toRow][fromCol - 1]	+ pfx[fromRow - 1][fromCol - 1];
		io.println(pfx[toRow][toCol] + " " + pfx[fromRow - 1][toCol] + " " + pfx[toRow][fromCol - 1] + " " + pfx[fromRow - 1][fromCol - 1]);
		io.println(current);*/
		int maximum = 2 * n1;
		for(int i = 1; i + k <= maximum; i++) {
			for(int j = 1; j + k <= maximum; j++) {
				int fromRow = j, toRow = j + k - 1, fromCol = i, toCol = i + k - 1;
				int cur = pfx[toRow][toCol] - pfx[fromRow - 1][toCol] - pfx[toRow][fromCol - 1]	+ pfx[fromRow - 1][fromCol - 1];
				answer = Math.max(answer, cur);
			}
		}
		io.println(answer);
        io.close();
	}

	/*
	0 0 0 0 0 0 0 0 0 0 0 
	0 0 0 0 0 50 0 0 0 0 0 
	0 0 0 0 5 0 14 0 0 0 0 
	0 0 0 25 0 3 0 99 0 0 0 
	0 0 6 0 2 0 10 0 8 0 0 
	0 17 0 7 0 1 0 7 0 10 0 
	0 0 21 0 2 0 5 0 0 0 0 
	0 0 0 80 0 23 0 78 0 0 0 
	0 0 0 0 11 0 1 0 0 0 0 
	0 0 0 0 0 9 0 0 0 0 0 
	0 0 0 0 0 0 0 0 0 0 0 
	*/

	static void printArr(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
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