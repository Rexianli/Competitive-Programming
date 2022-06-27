import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class guesstheanimal {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("guess");
		int n = io.nextInt();
		Map<String, String[]> data = new LinkedHashMap();
		for(int i = 0; i < n; i++) {
			String animal = io.next();
			int traitsAmount = io.nextInt();
			String[] traits = new String[traitsAmount];
			for(int j = 0; j < traitsAmount; j++) {
				String trait = io.next();
				traits[j] = trait;
			}
			data.put(animal, traits);
		}
		int maxCommon = 0;
		for(String key : data.keySet()) {
			for(String key2 : data.keySet()) {
				if(key.equals(key2)) continue;
				String[] array1 = data.get(key);
				String[] array2 = data.get(key2);
				int common = FindCommonElements(array1, array2);
				maxCommon = Math.max(maxCommon, common);
			}
		}
		io.println(maxCommon + 1);	
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