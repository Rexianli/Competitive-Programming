import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class stuckinarut {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();
		List<Integer> up = new ArrayList();
		List<Integer> right = new ArrayList();
		int[] xCoords = new int[n];
		int[] yCoords = new int[n];
		int[] stoppedBy = new int[n];
		Arrays.fill(stoppedBy, -1);
		int[] stoppedCount = new int[n];
		for(int i = 0; i < n; i++) {
			if(io.next().equals("N")) {
				up.add(i);
			} else {
				right.add(i);
			}
			xCoords[i] = io.nextInt();
			yCoords[i] = io.nextInt();
			//io.println(xCoords[i] + " " + yCoords[i]);
		}
		up.sort(Comparator.comparingInt(j -> xCoords[j]));
		right.sort(Comparator.comparingInt(j -> yCoords[j]));
		//io.println(up);
		//io.println(right);
		Outer:
		for(int i = 0; i < up.size(); i++) {
			int north = up.get(i);
			if(stoppedBy[north] != -1) continue;
			for(int j = 0; j < right.size(); j++) {
				int east = right.get(j);
				if(stoppedBy[east] != -1) continue;
				if(stoppedBy[north] != -1) continue Outer;
				//io.println(stoppedBy[north] + " " + stoppedBy[east]);
				int upDist = yCoords[east] - yCoords[north];
				int leftDist = xCoords[north] - xCoords[east];
				if(upDist < 0 || leftDist < 0) continue;
				//io.println(north + " " + east + " " + upDist + " " + leftDist);
				if(upDist == leftDist) {

				} else if(upDist > leftDist) {
					//vertical entity (red - east) blocks
					if(stoppedBy[east] == -1) {
						stoppedBy[north] = east;
					} else {
						stoppedBy[north] = stoppedBy[east];
					}
					stoppedCount[east]++;
					//if(stoppedBy[north] == 3) io.println(north + " " + east);
				} else {
					//horizontal entity (blue - north) blocks
					if(stoppedBy[north] == -1) {
						stoppedBy[east] = north;
					} else {
						stoppedBy[east] = stoppedBy[north];
					}
					stoppedCount[north]++;
					//if(stoppedBy[east] == 3) io.println(north + " " + east);
				}
			}
		}
		for(int num: stoppedBy) {
			if(num == -1) continue;
			int cur = stoppedBy[num];
			while(cur != -1) {
				stoppedCount[cur]++;
				cur = stoppedBy[cur];
			}
		}
		//io.println(Arrays.toString(stoppedBy));
		for(int answer: stoppedCount) {
			io.println(answer);
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