import java.io.*;
import java.util.*;

public class moocast {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("moocast.in");
        PrintWriter out = new PrintWriter("moocast.out");
        int n = in.nextInt();
        adj = new ArrayList[n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
        Cow[] cowArr = new Cow[n];
        for(int i = 0; i < n; i++) {
            cowArr[i] = new Cow(in.nextInt(), in.nextInt(), in.nextInt(), i);
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                double dist = Math.sqrt(Math.pow(cowArr[i].x - cowArr[j].x, 2) + Math.pow(cowArr[i].y - cowArr[j].y, 2));
                if(dist <= cowArr[i].p) adj[cowArr[i].id].add(cowArr[j].id);
            }
        }
//        for(ArrayList<Integer> arr : adj) {
//            out.println(arr);
//        }
        int maxCount = 0;
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            int count = 0;
            dfs(i);
            for(boolean value : visited) {
                if(value) count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        out.println(maxCount);
        in.close();
        out.close();
    }

    static void dfs(int node) {
        visited[node] = true;
        for(int ele : adj[node]) {
            if(!visited[ele]) {
                dfs(ele);
            }
        }
    }

    static class Cow {
        int x, y, p, id;
        Cow(int x, int y, int p, int id) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.id = id;
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