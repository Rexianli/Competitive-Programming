import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

class Graph { 
    int V;
	LinkedList<Integer>[] adj;
	Graph(int V) { 
		this.V = V; 
		adj = new LinkedList[V]; 
		for(int i = 0; i < adj.length; i++) 
			adj[i] = new LinkedList<Integer>(); 
	} 
		
	void addEdge(int v, int w) { 
		adj[v].add(w);
	} 
		
	boolean DFS(int n, int target) { 
		boolean nodes[] = new boolean[V]; 
		Stack<Integer> stack = new Stack<>(); 	
		stack.push(n); 
		int a = 0;	
		while(!stack.empty()) { 
			n = stack.peek();
			stack.pop();
			if(nodes[n] == false) { 
				//System.out.print(n + " "); 
				if(n == target) {
					return true;
				}
				nodes[n] = true; 
			} 	
			for(int i = 0; i < adj[n].size(); i++) {
				a = adj[n].get(i);
				if(!nodes[a]) {
					stack.push(a);   
				}
			}  
				
		} 
		return false;
	} 
} 

public class milkfactory {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("factory");
		int n = io.nextInt();
		Graph g = new Graph(n + 1);
		int[][] pairs = new int[n - 1][2];
		Set<Integer> list = new HashSet();
		for(int i = 0; i < n - 1; i++) {
			pairs[i][0] = io.nextInt();
			pairs[i][1] = io.nextInt();
			g.addEdge(pairs[i][0], pairs[i][1]);
			list.add(pairs[i][0]);
			list.add(pairs[i][1]);
		}
		List<Integer> set = new ArrayList();
		set.addAll(list);
        int answer = Integer.MAX_VALUE;
		for(int i = 0; i < set.size(); i++) {
			List<Boolean> answers = new ArrayList();
			int target = set.get(i);
			for(int j = 0; j < set.size(); j++) {
				if(i == j) continue;
				int current = set.get(j);
				answers.add(g.DFS(current, target));
			}
			if(!answers.contains(false)) {
				answer = Math.min(answer, target);
			}
		}
		if(answer == Integer.MAX_VALUE) {
			io.println(-1);
		} else {
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