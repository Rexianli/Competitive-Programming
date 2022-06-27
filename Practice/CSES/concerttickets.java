import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.IntStream;
 
public class concerttickets {
	static TreeMap<Integer, Integer> multiset = new TreeMap<Integer, Integer>();
	public static void main(String[] args) throws IOException {
		Reader io = new Reader();
		PrintWriter pw = new PrintWriter(System.out);
		int n = io.nextInt();
		int m = io.nextInt();
		for(int i = 0; i < n; i++) {
			add(io.nextInt());
		}
		for(int i = 0; i < m; i++) {
			int customer = io.nextInt();
			if(multiset.floorKey(customer) == null) {
				pw.println(-1);
				continue;
			}
			int ticket = multiset.floorKey(customer);
			pw.println(ticket);
			remove(ticket);
		}
		//io.println(multiset.floorKey(4));
        io.close();
		pw.close();
	}
 
	static void add(int x) {
		if(multiset.containsKey(x)) {
			multiset.put(x, multiset.get(x) + 1);
		} else {
			multiset.put(x, 1);
		}
	}
 
	static void remove(int x) {
		if(multiset.get(x) == 1) {
			multiset.remove(x);
		} else {
			multiset.put(x, multiset.get(x) - 1);
		}
	}
 
	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;
		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if(bytesRead == -1)
				buffer[0] = -1;
		}
		private byte read() throws IOException {
			if(bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
		public void close() throws IOException {
			if(din == null)
				return;
			din.close();
		}
		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if(neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			}  while ((c = read()) >= '0' && c <= '9');
			if(neg)
				return -ret;
			return ret;
		}
	}
}