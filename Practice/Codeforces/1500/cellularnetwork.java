import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class cellularnetwork {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int m = io.nextInt();
		int[] cityArray = new int[n];
		int[] cellArray = new int[m];
		for(int i = 0; i < n; i++) {
			cityArray[i] = io.nextInt();
		}
		for(int i = 0; i < m; i++) {
			cellArray[i] = io.nextInt();
		}
		int left = 0, right = 0;
		int[] city = removeDuplicates(cityArray);
		int[] cell = removeDuplicates(cellArray);
		n = city.length;
		m = cell.length;
		//io.println(Arrays.toString(city));
		//io.println(Arrays.toString(cell));
		//io.println(findClosest(cell, city[0]));
		int max = 0;
		int cellIndex = findClosest(cell, city[0]);
		//io.println(cellIndex);
		int cellNum = cell[cellIndex];
		Outer:
		for(int i = 0; i < city.length; i++) {
			int cityNum = city[i];
			if(i == 0) {
				max = Math.abs(cellNum - cityNum);
				continue;
			}
			while(cellIndex < cell.length) {
				//io.println(cityNum + " " + cellNum);
				if(Math.abs(cityNum - cellNum) <= max) {
					continue Outer;
				}
				if(cellNum - cityNum > max) {
					int fmax = Math.max(max, cityNum - cell[cellIndex - 1]);
					max = Math.min(fmax, cellNum - cityNum);
					continue Outer;
				}
				if(cellIndex == cell.length - 1 && cityNum - cellNum > max) {
					max = cityNum - cellNum;
				}
				//cellIndex++;
				if(cellIndex == cell.length - 1) {
					break;
				}
				cellIndex++;
				cellNum = cell[cellIndex];
			}
		}
		io.println(max);
        io.close();
	}

	public static int[] removeDuplicates(int[] a) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        for(int i = 0; i < a.length; i++)
            set.add(a[i]);
		int[] ret = new int[set.size()];
		int j = 0;
		for(int element: set) {
			ret[j++] = element;
		}
        return ret;
    }

	public static int findClosest(int arr[], int target) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			int abs = Math.abs(arr[i] - target);
			if(abs <= min) {
				min = abs;
				index = i;
			}
		}
		return index;
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