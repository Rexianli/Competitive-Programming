import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class familytree {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("family");
		int n = io.nextInt();
		String cow1 = io.next();
		String cow2 = io.next();
		String[][] pairs = new String[n][2];
		Set<String> set = new HashSet();
		for(int i = 0; i < n; i++) {
			pairs[i][0] = io.next();
			pairs[i][1] = io.next();
			set.add(pairs[i][0]);
			set.add(pairs[i][1]);
		}
		List<String> list = new ArrayList();
		list.addAll(set);
		list.remove(cow1);
		list.remove(cow2);
		String currentAncestor = cow1;
		int xDist = 0;
		int yDist = 0;
		while(!currentAncestor.equals("none")) {
			if(findA(cow2, currentAncestor, pairs) != -1) {
				yDist = findA(cow2, currentAncestor, pairs);
				break;
			}
			currentAncestor = findM(currentAncestor, pairs);
			xDist++;
		}
		if(findA(cow1, cow2, pairs) != -1) {
			int num = findA(cow1, cow2, pairs);
			String name = "mother";
			if(num == 1) {

			} else if(num == 2) {
				name = "grand-mother";
			} else {
				name = "great-".repeat(num - 2) + "grand-mother";
			}
			io.println(cow2 + " is the " + name + " of " + cow1);
		} else if(xDist > 1 && yDist > 1) {
			io.println("COUSINS");
		} else if(currentAncestor.equals("none")) {
			io.println("NOT RELATED");
		} else if(xDist == 1 && yDist == 1) {
			io.println("SIBLINGS");
		} else {
			String name = "aunt";
			if(xDist == 2) {
				
			} else {
				name = "great-".repeat(xDist - 2) + "aunt";
			}
			io.println(cow2 + " is the " + name + " of " + cow1);
		}
        io.close();
	}

	static String findM(String name, String[][] pairs) {
		for(int i = 0; i < pairs.length; i++) {
			if(pairs[i][1].equals(name)) {
				return pairs[i][0];
			}
		}
		return "none";
	}

	static int findA(String cow1, String cow2, String[][] pairs) {
		int counter = 0;
		String curCow = cow1;
		while(true) {
			curCow = findM(curCow, pairs);
			//System.out.println(curCow);
			if(!curCow.equals("none")) counter++;
			if(curCow.equals("none")) return -1;
			if(curCow.equals(cow2)) break;
		}
		return counter;
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