import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class cowevolution {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("evolution");
		int n = io.nextInt();
		String[][] species = new String[n][];
		Set<String> traits = new HashSet();
		for(int i = 0; i < n; i++) {
			int curN = io.nextInt();
			String[] curArr = new String[curN];
			for(int j = 0; j < curN; j++) {
				curArr[j] = io.next();
				traits.add(curArr[j]);
			}
			species[i] = curArr;
		}
		List<String> traitList = new ArrayList();
		traitList.addAll(traits);
		String answer = "yes";
		Outer:
		for(int i = 0; i < traitList.size(); i++) {
			for(int j = i + 1; j < traitList.size(); j++) {
				if(checkCross(traitList.get(i), traitList.get(j), species)) {
					answer = "no";
					break Outer;
				}
			}
		}
		io.println(answer);
        io.close();
	}

	static boolean checkCross(String trait1, String trait2, String[][] species) {
		int A = 0;
		int B = 0;
		int AB = 0;
		for(int i = 0; i < species.length; i++) {
			boolean hasA = false;
			boolean hasB = false;
			for(int j = 0; j < species[i].length; j++) {
				if(species[i][j].equals(trait1)) {
					hasA = true;
				}
				if(species[i][j].equals(trait2)) {
					hasB = true;
				}
			}
			if(hasA && hasB) {
				AB++;
			} else if(hasA) {
				A++;
			} else if(hasB) {
				B++;
			}
		}
		if(A > 0 && B > 0 && AB > 0) {
			//System.out.println(trait1 + " " + trait2);
			return true;
		}
		return false;
		
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