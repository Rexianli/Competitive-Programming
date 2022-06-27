import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class teamtictactoe {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("tttt");
		String[] board = new String[3];
		int ans1 = 0;
		int ans2 = 0;
		for(int i = 0; i < 3; i++) {
			board[i] = io.next();
		}
		String diagonal1 = board[0].charAt(0) + "" + board[1].charAt(1) + board[2].charAt(2);
		String diagonal2 = board[0].charAt(2) + "" + board[1].charAt(1) + board[2].charAt(0);
		String vertical1 = board[0].charAt(0) + "" + board[1].charAt(0) + board[2].charAt(0);
		String vertical2 = board[0].charAt(1) + "" + board[1].charAt(1) + board[2].charAt(1);
		String vertical3 = board[0].charAt(2) + "" + board[1].charAt(2) + board[2].charAt(2);
		String horizontal1 = board[0].charAt(0) + "" + board[0].charAt(1) + board[0].charAt(2);
		String horizontal2 = board[1].charAt(0) + "" + board[1].charAt(1) + board[1].charAt(2);
		String horizontal3 = board[2].charAt(0) + "" + board[2].charAt(1) + board[2].charAt(2);
		String[] directions = {diagonal1, diagonal2, vertical1, vertical2, vertical3, horizontal1, horizontal2, horizontal3};
		List<String> used = new ArrayList();
		used.add(null);
		for(int i = 0; i < directions.length; i++) {
			int unique = (int) directions[i].chars().distinct().count();
			String temp = directions[i].chars().distinct().mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
			char[] chars = temp.toCharArray();
        	Arrays.sort(chars);
			temp = String.valueOf(chars);
			if(unique == 1 || unique == 2) {
				int bool = 0;
				for(int j = 0; j < used.size(); j++) {
					if(temp.equals(used.get(j))) {
						bool = 1;
					} else {
						bool = 2;
					}
				}
				if(bool == 2) {
					if(unique == 1) ans1++;
					if(unique == 2) ans2++;
					used.add(temp);
				}
			}
		}
		io.println(ans1);
		io.println(ans2);
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