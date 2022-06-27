import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class loadbalancing {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int[][] coords = new int[n][2];
		for(int i = 0; i < n; i++) {
			coords[i][0] = io.nextInt();
			coords[i][1] = io.nextInt();
		}
		Arrays.sort(coords, Comparator.comparingDouble(o -> o[0]));
		//io.println(Arrays.deepToString(coords));
		int ret = coords.length;
		for(int i = 0; i < n; i++) {
			List<int[]> above = new ArrayList();
			List<int[]> below = new ArrayList();
			for(int j = 0; j < n; j++) {
				if(coords[j][1] > coords[i][1]) {
					above.add(coords[j]);
				} else {
					below.add(coords[j]);
				}
			}
			above.forEach(array -> io.println(Arrays.toString(array)));
			io.println();
			below.forEach(array -> io.println(Arrays.toString(array)));
			
			int belowIndex = 0;
			int aboveIndex = 0;
			while(belowIndex < below.size() || aboveIndex < above.size()) { //while loop runs until sweeping is done
				int xBorder = Integer.MAX_VALUE; //border shrinks
				if(belowIndex < below.size()) { //runs as long as below sweeping has not finished
					io.println(xBorder + " " + below.get(belowIndex)[0]); 
					xBorder = Math.min(xBorder, below.get(belowIndex)[0]); //sets xBorder to 
					io.println(xBorder);
				}
				if(aboveIndex < above.size()) { //runs as long as above sweeping has not finished
					io.println(xBorder + " " + above.get(aboveIndex)[0]); 
					xBorder = Math.min(xBorder, above.get(aboveIndex)[0]);
					io.println(xBorder);
				}
				if(belowIndex < below.size() && below.get(belowIndex)[0] == xBorder) {
					belowIndex++;
				}
				if(aboveIndex < above.size() && above.get(aboveIndex)[0] == xBorder) {
					aboveIndex++;
				}
				ret = Math.min(ret, Math.max(Math.max(belowIndex, below.size() - belowIndex), Math.max(aboveIndex, above.size() - aboveIndex)));
			}
			io.println("-------------");
			/*The block of code above splits the graph into 
			two seperate parts, a top half as well as a bottom half.
			After parsing the coordinates into two seperate blocks,
			the code sweeps both the top and the bottom,
			while finding the maximum amount of coordinates in each
			quadrant as the for loop runs. 

			The for loop splits the graph into top and bottom,
			and it uses each y coordinate in the coordinates given.

			Sweeping Function - 
			The sweeping function parses the two blocks of coordinates in a linear time complexity
			with a o(n) fashion.
			
			The sweeping function works with 2 integer variables
			aboveIndex and belowIndex. Both increment as long as the index
			is below the total size of the list.

			As the index increments, it moves a coordinate from one quadrant
			to another. 
			
			This method of sweeping the entire graph is effective in 
			sweeping the entire graph in an o(n) time complexity, bringing
			the total complexity of the file quadratic o(n^2).
			
			 */
		}
		io.println(ret);
        io.close();
	}

	public static int max(int a, int b, int c, int d) {
		int max = a;
		if(b > max)
			max = b;
		if(c > max)
			max = c;
		if(d > max)
			max = d;
		return max;
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