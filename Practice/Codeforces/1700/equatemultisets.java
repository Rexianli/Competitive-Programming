import java.io.*;
import java.util.*;

public class equatemultisets {
    static TreeMap<Integer, Integer> multiset1 = new TreeMap<Integer, Integer>();
    static TreeMap<Integer, Integer> multiset2 = new TreeMap<Integer, Integer>();
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            for(int i = 0; i < n; i++) {
                int x = in.nextInt();
                while(x % 2 == 0) x /= 2;
                add1(x);
            }
            for(int i = 0; i < n; i++) {
                add2(in.nextInt());
            }
            n = multiset1.size();
            while(!multiset2.isEmpty()) {
                int x = multiset2.lastKey();
                if(!multiset1.containsKey(x)) {
                    if(x == 1) break;
                    remove2(x);
                    add2(x / 2);
                } else {
                    remove1(x);
                    remove2(x);
                }
            }
            out.println(multiset2.isEmpty() ? "YES" : "NO");
            multiset1 = new TreeMap<>();
            multiset2 = new TreeMap<>();
        }
        in.close();
        out.close();
    }

    static void add1(int x){
        if(multiset1.containsKey(x)){
            multiset1.put(x, multiset1.get(x) + 1);
        } else {
            multiset1.put(x, 1);
        }
    }

    static void remove1(int x){
        multiset1.put(x, multiset1.get(x) - 1);
        if(multiset1.get(x) == 0){
            multiset1.remove(x);
        }
    }

    static void add2(int x){
        if(multiset2.containsKey(x)){
            multiset2.put(x, multiset2.get(x) + 1);
        } else {
            multiset2.put(x, 1);
        }
    }

    static void remove2(int x){
        multiset2.put(x, multiset2.get(x) - 1);
        if(multiset2.get(x) == 0){
            multiset2.remove(x);
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

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}