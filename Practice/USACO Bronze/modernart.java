import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class modernart {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("art");
		int n = io.nextInt();
		int[][] grid = new int[n][n];
		Set<Integer> nums = new HashSet();
		for(int i = 0; i < n; i++) {
			String currentNum = io.next();
			for(int j = 0; j < n; j++) {
				grid[i][j] = Character.getNumericValue(currentNum.charAt(j));
				nums.add(grid[i][j]);
			}
		}
		if(nums.contains(0)) nums.remove(0);
		//io.println(Arrays.deepToString(grid));
		//io.println(nums);
		ArrayList<Integer> numsList = new ArrayList<>(nums);
		int answer = 0;
		for(int i = 0; i < numsList.size(); i++) {
			int overlapNum = 0;
			int currentNum1 = numsList.get(i);
			int[][] filledArr1 = fill(grid, currentNum1, n);
			for(int j = 0; j < numsList.size(); j++) {
				if(i == j) continue;
				int currentNum2 = numsList.get(j);
				int[][] filledArr2 = fill(grid, currentNum2, n);
				List<int[]> overlaps = findOverlap(filledArr1, filledArr2, n);
				if(overlaps.size() == 0) continue;
				int firstI = overlaps.get(0)[0];
				int firstJ = overlaps.get(0)[1];
				if(grid[firstI][firstJ] == filledArr1[firstI][firstJ]) overlapNum++;
			}
			if(overlapNum == 0) answer++;
		}
		/*int[][] filledArr1 = fill(grid, 2, n);
		int[][] filledArr2 = fill(grid, 7, n);
		List<int[]> overlaps = findOverlap(filledArr1, filledArr2, n);
		overlaps.forEach(array -> io.println(Arrays.toString(array)));*/
		//int[][] arr = fill(grid, 2, n);
		//io.println(Arrays.deepToString(arr));
		io.println(answer);
        io.close();
	}

	static int[][] fill(int[][] grid, int num, int n) {
		int[][] returnGrid = new int[n][n];
		int top = Integer.MAX_VALUE;
		int bottom = 0;
		int left = Integer.MAX_VALUE;
		int right = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == num) {
					top = Math.min(top, i);
					bottom = Math.max(bottom, i);
					left = Math.min(left, j);
					right = Math.max(right, j);
				}
			}
		}
		//if(top == Integer.MAX_VALUE) top = bottom;
		//if(left == Integer.MAX_VALUE) left = right;
		//System.out.println(top + " " + bottom + " " + left + " " + right);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i >= top && i <= bottom && j >= left && j <= right) {
					returnGrid[i][j] = num;
				} else {
					returnGrid[i][j] = 0;
				}
			}
		}
		return returnGrid;
	}

	static List<int[]> findOverlap(int[][] grid, int[][] grid2, int n) {
		List<int[]> indexes = new ArrayList();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] != 0 && grid2[i][j] != 0) {
					int[] index = new int[2];
					index[0] = i;
					index[1] = j;
					indexes.add(index);
				}
			}
		}
		return indexes;
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