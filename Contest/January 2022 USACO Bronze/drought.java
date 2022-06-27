import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class drought {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int t = io.nextInt();
		Loop:
		for(int i = 0; i < t; i++) {
			int n = io.nextInt();
			int[] arr = new int[n];
			for(int j = 0; j < n; j++) {
				arr[j] = io.nextInt();
			}
			if(Arrays.stream(arr).distinct().count() == 1) {
				io.println(0);
				continue;
			} 
			int total = 0;
			int curDif = 0;
			int dif = 0;
			while(true) {
				int curIndex = 0;
				int max = 0;
				for(int j = 0; j < n - 1; j++) {
					int curSum = arr[j] + arr[j + 1];
					if(curSum > max) {
						curIndex = j;
						max = curSum;
					}
				}
				curDif = Math.abs(arr[curIndex] - arr[curIndex + 1]);
				arr[curIndex] -= curDif;
				arr[curIndex + 1] -= curDif;
				total += curDif * 2;
				if(curDif == 0) {	
					curDif = getMax(arr) - getMin(arr);
					arr[curIndex] -= curDif;
					arr[curIndex + 1] -= curDif;
					total += curDif * 2;
				}
				if(Arrays.stream(arr).distinct().count() == 1) break;
				for(int j = 0; j < arr.length; j++) {
					if(arr[j] < 0) {
						io.println(-1);
						continue Loop;
					}
				}
			}
			io.println(total);
		}
        io.close();
	}

	public static int getMax(int[] inputArray){ 
    int maxValue = inputArray[0]; 
    for(int i=1;i < inputArray.length;i++){ 
      if(inputArray[i] > maxValue){ 
         maxValue = inputArray[i]; 
      } 
    } 
    return maxValue; 
  }

   public static int getMin(int[] inputArray){ 
    int minValue = inputArray[0]; 
    for(int i=1;i<inputArray.length;i++){ 
      if(inputArray[i] < minValue){ 
        minValue = inputArray[i]; 
      } 
    } 
    return minValue; 
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