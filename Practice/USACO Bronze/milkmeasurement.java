import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class milkmeasurement {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("measurement");
		int n = io.nextInt();
		TreeMap<Integer, List<Object>> treeMap = new TreeMap();
		for(int i = 0; i < n; i++) {
			int order = io.nextInt();
			List<Object> objects = new ArrayList();
			objects.add(io.next()); objects.add(io.nextInt());
			treeMap.put(order, objects);
		}	
		LinkedHashMap<String, Integer> map = new LinkedHashMap();
		for(Map.Entry<Integer, List<Object>> entry : treeMap.entrySet()) {
  			List<Object> value = entry.getValue();
			String name = (String) value.get(0);
			map.put(name, 7);
		}
		int changes = 0;
		List<String> leadingCow = new ArrayList();
		for(Map.Entry<Integer, List<Object>> entry : treeMap.entrySet()) {
  			List<Object> value = entry.getValue();
			String name = (String) value.get(0);
			int difference = (int) value.get(1);
			map.put(name, map.get(name) + difference);
			List<String> keys = returnMax(map);
			if(!keys.equals(leadingCow)) {
				changes++;
				leadingCow = keys;
			}
		}
		io.println(changes);
        io.close();
	}

	static List<String> returnMax(Map<String, Integer> map) {
		List<String> returns = new ArrayList();
		int maxValueInMap=(Collections.max(map.values()));
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            if(entry.getValue()==maxValueInMap) {
				returns.add(entry.getKey());
            }
        }
		return returns;
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