import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class bovinegenomics {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("cownomics");
		int n = io.nextInt();
		int k = io.nextInt();
		String[] arr = new String[n];
		String[] arr2 = new String[n];
		for(int i = 0; i < n; i++) {
			arr[i] = io.next();
		}
		for(int i = 0; i < n; i++) {
			arr2[i] = io.next();
		}
		int answer = 0;
		for(int i = 0; i < k; i++) {
			for(int j = i + 1; j < k; j++) {
				for(int l = j + 1; l < k; l++) {
					String[] temp1 = new String[n];
					String[] temp2 = new String[n];
					for(int m = 0; m < n; m++) {
						String curStr = "";
						curStr += arr[m].charAt(i); 
						curStr += arr[m].charAt(j);
						curStr += arr[m].charAt(l);
						temp1[m] = curStr;
						String curStr2 = "";
						curStr2 += arr2[m].charAt(i);
						curStr2 += arr2[m].charAt(j);
						curStr2 += arr2[m].charAt(l);
						temp2[m] = curStr2;
					}
					int common = FindCommonElements(temp1, temp2);
					if(common == 0) answer++;
				}
			}
		}
		io.println(answer);
        io.close();
	}

	public static int FindCommonElements(String[] arr1, String[] arr2) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for(String i : arr1) {
            set1.add(i);
        }
        for(String i : arr2) {
            set2.add(i);
        }
        set1.retainAll(set2);
		int com = set1.size();
		return com;
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