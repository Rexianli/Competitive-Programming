import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class contaminatedmilk {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("badmilk");
		int friendAmount = io.nextInt(); int typeAmount = io.nextInt(); int drinkLine = io.nextInt(); int sickAmount = io.nextInt();
		int[][] drinkLog = new int[drinkLine][3];
		int[][] sickLog = new int[sickAmount][2];
		for(int i = 0; i < drinkLine; i++) {
			drinkLog[i][0] = io.nextInt();
			drinkLog[i][1] = io.nextInt();
			drinkLog[i][2] = io.nextInt();
		}
		int[] mapKeys = new int[sickAmount];
		for(int i = 0; i < sickAmount; i++) {
			sickLog[i][0] = io.nextInt();
			sickLog[i][1] = io.nextInt();
			mapKeys[i] = sickLog[i][0];
		}
		Map<Integer, Integer> sickLogMap = new HashMap();
		for(int i = 0; i < sickLog.length; i++) {
			sickLogMap.put(sickLog[i][0], sickLog[i][1]);
		}
		Map<Integer, List<Integer>> drankBefore = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < sickLog.length; i++) {
			List<Integer> temp = new ArrayList();
			drankBefore.put(sickLog[i][0], new ArrayList<Integer>());
		}
		for(int i = 0; i < mapKeys.length; i++) {
			int currentMapKey = mapKeys[i];
			int currentMapValue = sickLogMap.get(currentMapKey);
			for(int j = 0; j < drinkLog.length; j++) {
				if(drinkLog[j][0] == currentMapKey) {
					if(drinkLog[j][2] < currentMapValue) {
						drankBefore.get(currentMapKey).add(drinkLog[j][1]);
					}
				}
			}
		}
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		for(int i = 0; i < drankBefore.size(); i++) {
			int currentMapKey = mapKeys[i];
			lists.add(drankBefore.get(currentMapKey));
		}
		List<Integer> commons = new ArrayList<Integer>();
		commons.addAll(lists.get(1));
    	for(ListIterator<List<Integer>> iter = lists.listIterator(1); iter.hasNext();) {
        	commons.retainAll(iter.next());
    	}
		int answer = 0;
		for(int i = 0; i < commons.size(); i++) {
			int current = commons.get(i);
			LinkedHashSet<Integer> linkedset = new LinkedHashSet<Integer>();
			for(int j = 0; j < drinkLog.length; j++) {
				if(drinkLog[j][1] == current) {
					linkedset.add(drinkLog[j][0]);
				}
			}
			answer = Math.max(answer, linkedset.size());
		}
		io.println(answer);
		/*io.println(Arrays.deepToString(drinkLog));
		io.println(Arrays.deepToString(sickLog));
		io.println(Arrays.toString(mapKeys));
		io.println(drankBefore);
		io.println(lists);
		io.println(commons);*/
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