import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;

public class bullinachinashop {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("bcs");
		int n = io.nextInt();
		int k = io.nextInt();
		String[] original = new String[n];
		String[][] pieces = new String[k][n];
		int count = 0;
		for(int i = 0; i < n; i++) {
			original[i] = io.next();
			for(int j = 0; j < n; j++) {
				if(original[i].charAt(j) == '#') count++;
			}
		}
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < n; j++) {
				pieces[i][j] = io.next();
			}
		}
		Map<Integer, List<String[]>> map = new HashMap();
		for(int i = 0; i < k; i++) {
			List<String[]> current = searchPos(pieces[i], original, n);
			if(current.size() == 0) continue;
			map.put(i + 1, current);
		}
		/*for(List<String[]> value : map.values()) {
			for(int i = 0; i < value.size(); i++) {
				printArr(value.get(i));
			}
		}*/
		//int[][] indexes = new int[map.size()][];
		List<Integer> sorter = new ArrayList();
		Outer:
		for(Map.Entry<Integer, List<String[]>> entry : map.entrySet()) {
			int key = entry.getKey();
			List<String[]> value = entry.getValue();
			for(String[] array : value) {
   				for(Map.Entry<Integer, List<String[]>> entry2 : map.entrySet()) {
				int key2 = entry2.getKey();
				if(key2 == key) continue;
				List<String[]> value2 = entry2.getValue();
					for(String[] array2 : value2) {
						String[] combined = combine(array, array2, n);
						if(Arrays.equals(original, combined)) {
							sorter.add(key);
							sorter.add(key2);
							break Outer;
						}
					}
				}
			}
		}
		/*String[] tester = {"..##", "....", "....", "...."};
		List<String[]> stringList = searchPos(tester, original, n);
		for(String[] currentArray : stringList) {  
    		printArr(currentArray);
		} 
		String[] anArray = combine(pieces[0], pieces[2], n);
		printArr(anArray);*/

		Collections.sort(sorter);
		io.println(sorter.get(0) + " " + sorter.get(1));
		//io.println(sorter);

		//String[] tester = {"..##", "...#", "...#", "...."};
		//boolean bool = searchPos(pieces[2], original, n);
		//io.println(bool);
        io.close();
	}

	public static List<String[]> searchPos(String[] arr, String[] arr2, int n) {
		String[] returnArr = new String[n];
		returnArr = arr.clone();
		boolean boo1 = false;
		List<String[]> stringList = new ArrayList<String[]>();
		//printArr(returnArr);
		while(true) {
			boolean boo2 = true;
			String[] currArr = new String[n];
			currArr = returnArr.clone();
			OuterLoop:
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(returnArr[i].charAt(j) == '#') {
						if(j - 1 < 0) {
							boo2 = false;
							break OuterLoop;
						}
						returnArr[i] = replace(returnArr[i], j - 1, '#');
						returnArr[i] = replace(returnArr[i], j, '.');
					}
				}
			}
			if(boo2 == false) {
				returnArr = currArr.clone();
				break;
			}
		}
		if(check(returnArr, arr2, n)) {
			boo1 = true;
			//System.out.println(Arrays.toString(returnArr));
			String[] tempArr = new String[n];
			tempArr = returnArr.clone();
			stringList.add(tempArr);
			/*for(String[] currentArray : stringList) {  
    		printArr(currentArray);
			}*/
		}	
		//printArr(returnArr);
		while(true) {
			boolean boo2 = true;
			String[] currArr = new String[n];
			currArr = returnArr.clone();
			OuterLoop:
			for(int i = 0; i < n; i++) {
				for(int j = n - 1; j >= 0; j--) {
					if(returnArr[j].charAt(i) == '#') {
						if(j + 1 > n - 1) {
							boo2 = false;
							break OuterLoop;
						}
						returnArr[j + 1] = replace(returnArr[j + 1], i, '#');
						returnArr[j] = replace(returnArr[j], i, '.');
					}
				}
			}
			if(boo2 == false) {
				returnArr = currArr.clone();
				break;
			}
		}
		if(check(returnArr, arr2, n)) {
			boo1 = true;
			//System.out.println(Arrays.toString(returnArr));
			String[] tempArr = new String[n];
			tempArr = returnArr.clone();
			stringList.add(tempArr);
			/*for(String[] currentArray : stringList) {  
				printArr(currentArray);
			}*/
		}	
		//printArr(returnArr);
		whileLoop:
		while(true) {
			while(true) {
				boolean boo2 = true;
				String[] currArr = new String[n];
				currArr = returnArr.clone();
				OuterLoop:
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(returnArr[j].charAt(i) == '#') {
							if(j - 1 < 0) {
								boo2 = false;
								break OuterLoop;
							}
							returnArr[j - 1] = replace(returnArr[j - 1], i, '#');
							returnArr[j] = replace(returnArr[j], i, '.');
						}
					}
				}
				if(boo2 == false) {
					returnArr = currArr.clone();
					break;
				}
				if(check(returnArr, arr2, n)) {
					boo1 = true;
					//System.out.println(Arrays.toString(returnArr));
					String[] tempArr = new String[n];
					tempArr = returnArr.clone();
					stringList.add(tempArr);
					/*for(String[] currentArray : stringList) {  
    					printArr(currentArray);
					}*/
				}
			}
				
			//printArr(returnArr);
			OuterLoop2:
			for(int i = 0; i < n; i++) {
				for(int j = n - 1; j >= 0; j--) {
					if(returnArr[i].charAt(j) == '#') {
						if(j + 1 > n - 1) {
							break whileLoop;
						}
						returnArr[i] = replace(returnArr[i], j + 1, '#');
						returnArr[i] = replace(returnArr[i], j, '.');
					}
				}
			}
			if(check(returnArr, arr2, n)) {
				boo1 = true;
				//System.out.println(Arrays.toString(returnArr));
				String[] tempArr = new String[n];
				tempArr = returnArr.clone();
				stringList.add(tempArr);
				/*for(String[] currentArray : stringList) {  
    				printArr(currentArray);
				}*/
			}	
			//printArr(returnArr);
			while(true) {
				boolean boo2 = true;
				String[] currArr = new String[n];
				currArr = returnArr.clone();
				OuterLoop:
				for(int i = 0; i < n; i++) {
					for(int j = n - 1; j >= 0; j--) {
						if(returnArr[j].charAt(i) == '#') {
							if(j + 1 > n - 1) {
								boo2 = false;
								break OuterLoop;
							}
							returnArr[j + 1] = replace(returnArr[j + 1], i, '#');
							returnArr[j] = replace(returnArr[j], i, '.');
						}
					}
				}
				if(boo2 == false) {
					returnArr = currArr.clone();
					break;
				}
				if(check(returnArr, arr2, n)) {
					boo1 = true;
					//System.out.println(Arrays.toString(returnArr));
					String[] tempArr = new String[n];
					tempArr = returnArr.clone();
					stringList.add(tempArr);
					/*for(String[] currentArray : stringList) {  
    					printArr(currentArray);
					}*/
					//printArr(returnArr);
				}	
			}
			
			//printArr(returnArr);
			OuterLoop3:
			for(int i = 0; i < n; i++) {
				for(int j = n - 1; j >= 0; j--) {
					if(returnArr[i].charAt(j) == '#') {
						if(j + 1 > n - 1) {
							break whileLoop;
						}
						returnArr[i] = replace(returnArr[i], j + 1, '#');
						returnArr[i] = replace(returnArr[i], j, '.');
					}
				}
			}
			if(check(returnArr, arr2, n)) {
				boo1 = true;
				//System.out.println(Arrays.toString(returnArr));
				String[] tempArr = new String[n];
				tempArr = returnArr.clone();
				stringList.add(tempArr);
				/*for(String[] currentArray : stringList) {  
    				printArr(currentArray);
				}*/ 
				//printArr(returnArr);
			}	
		}
		 
		return stringList;
	}

	public static void printArr(String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}

	public static String replace(String str, int index, char replace) {     
		if(str == null) {
			return str;
		} else if(index<0 || index>=str.length()) {
			return str;
		}
		char[] chars = str.toCharArray();
		chars[index] = replace;
		return String.valueOf(chars);       
	}

	public static boolean check(String[] arr, String[] arr2, int n) {
		int count1 = 0;
		int count2 = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i].charAt(j) == '#') {
					count1++;
					if(arr2[i].charAt(j) == '#') {
						count2++;
					}
				}
			}
		}
		if(count1 == count2) {
			return true;
		}
		return false;
	}

	public static String[] combine(String[] arr1, String[] arr2, int n) {
		String[] returnArray = new String[n];
		for(int i = 0; i < n; i++) {
			returnArray[i] = ".".repeat(n);
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if((arr1[i].charAt(j) == '#' || arr2[i].charAt(j) == '#') && returnArray[i].charAt(j) == '.') {
					returnArray[i] = replace(returnArray[i], j, '#');
				}
			}
		}
		return returnArray;
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