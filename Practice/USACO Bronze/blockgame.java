import java.io.*;
import java.util.*;

public class blockgame {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("blocks");
		int n = io.nextInt();
		String[][] arr = new String[n][2];
		int power = (int) Math.pow(2, n);
		String[][] arr2 = new String[power][n];
		int[] letters = new int[26];
		for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                arr[i][j] = io.next();
            }
        }
		String[][] jaggedArray = new String[n][2];
		jaggedArray = arr;
        int[] rowLengths = new int[jaggedArray.length];
        int totalJaggedCombos = 1;
        int jaggedArrayLength = jaggedArray.length;
        for (int i = jaggedArrayLength - 1; i >= 0; i--) {
            rowLengths[i] = totalJaggedCombos;
            totalJaggedCombos *= jaggedArray[i].length;
            if (totalJaggedCombos <= 0)
                throw new IllegalStateException("Empty object in in multi-dimensional array");
        }
        for (int countJaggedElements = 0; countJaggedElements < totalJaggedCombos;
             countJaggedElements++) {
            for (int count = 0; count < jaggedArrayLength; count++) {
                int jaggedDivRow = countJaggedElements / rowLengths[count];
                jaggedDivRow = jaggedDivRow % jaggedArray[count].length;
				arr2[countJaggedElements][count] = jaggedArray[count][jaggedDivRow];
            }
        }
		int[] alphabetFinal = new int[26];
		List<Character> alpha = new ArrayList(
			Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
		);
		for(int i = 0; i < arr2.length; i++) {
			Object[][] alphabet2 = new Object[26][2];
			for(int j = 0; j < 26; j++) {
				alphabet2[j][0] = (char)(97 + j);
				alphabet2[j][1] = 0;
			}
			String joined = "";
			for(int j = 0; j < n; j++) {
				joined += arr2[i][j];
			}
			joined = joined.replaceAll("\\s+","");
			for(int j = 0; j < joined.length(); j++) {
				int index = alpha.indexOf(joined.charAt(j));
				int current = (Integer) alphabet2[index][1];
				current++;
				alphabet2[index][1] = current;
			}
			for(int j = 0; j < 26; j++) {
				int current = (Integer) alphabet2[j][1];
				if(current > alphabetFinal[j]) {
					alphabetFinal[j] = current;
				}
			}
		}
		for(int i = 0; i < alphabetFinal.length; i++) {
			io.println(alphabetFinal[i]);
		}
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