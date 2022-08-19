import java.io.*;
import java.util.*;

public class thegreatrevegetation {
    static ArrayList<Integer>[] same;
    static ArrayList<Integer>[] different;
    static int[] type;
    static boolean impossible;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("revegetate.in");
        PrintWriter out = new PrintWriter("revegetate.out");
        int n = in.nextInt();
        int m = in.nextInt();
        same = new ArrayList[n + 1];
        different = new ArrayList[n + 1];
        type = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            same[i] = new ArrayList<>();
            different[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int type = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            if(type == 35) {
                same[a].add(b);
                same[b].add(a);
            }
            if(type == 20) {
                different[a].add(b);
                different[b].add(a);
            }
        }
//        out.println(Arrays.toString(same));
//        out.println(Arrays.toString(different));
        int components = 0;
        for(int i = 1; i <= n; i++) {
            if(type[i] == 0) {
                visit(i, 1);
                components++;
            }
        }
        if(impossible) {
            out.println(0);
        } else {
            out.print(1);
            for(int i = 0; i < components; i++) out.print(0);
        }
        in.close();
        out.close();
    }

    public static void visit(int node, int color) {
        type[node] = color;
        for(int next : same[node]) {
            if(type[next] == 3 - color) {
                impossible = true;
            }
            if(type[next] == 0) {
                visit(next, color);
            }
        }
        for(int next : different[node]) {
            if(type[next] == color) {
                impossible = true;
            }
            if(type[next] == 0) {
                visit(next, 3 - color);
            }
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