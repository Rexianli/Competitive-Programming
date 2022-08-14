import java.io.*;
import java.util.*;

public class fenceplanning {
    static ArrayList<Integer>[] adj;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("fenceplan.in");
        PrintWriter out = new PrintWriter("fenceplan.out");
        int n = in.nextInt();
        int m = in.nextInt();
        adj = new ArrayList[n];
        visited = new int[n];
        Arrays.fill(visited, -1);
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        Cow[] cowArr = new Cow[n];
        Connection[] connectionArr = new Connection[m];
        for(int i = 0; i < n; i++) {
            cowArr[i] = new Cow(in.nextInt(), in.nextInt(), i);
        }
        for(int i = 0; i < m; i++) {
            connectionArr[i] = new Connection(in.nextInt() - 1, in.nextInt() - 1);
            adj[connectionArr[i].a].add(connectionArr[i].b);
            adj[connectionArr[i].b].add(connectionArr[i].a);
        }
//        for(ArrayList<Integer> arr : adj) {
//            out.println(arr);
//        }
        List<List<Integer>> list = new ArrayList<>();
        while(!Arrays.stream(visited).allMatch(x -> x == 0)) {
            List<Integer> newList = new ArrayList<>();
            int i = 0;
            while(i < n - 1 && visited[i] != -1) {
                i++;
            }
            dfs(i);
            for(int j = 0; j < n; j++) {
                if(visited[j] == 1) {
                    newList.add(j);
                    visited[j] = 0;
                }
            }
            list.add(newList);
        }
//        out.println(list);
        int perimeter = Integer.MAX_VALUE;
        for(List<Integer> sList : list) {
            int minX = Integer.MAX_VALUE, maxX = 0, minY = Integer.MAX_VALUE, maxY = 0;
            for(int ele : sList) {
                int x = cowArr[ele].x;
                int y = cowArr[ele].y;
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
            int curPerimeter = ((maxX - minX) * 2) + ((maxY - minY) * 2);
            perimeter = Math.min(perimeter, curPerimeter);
        }
        out.println(perimeter);
        in.close();
        out.close();
    }

    static void dfs(int node) {
        visited[node] = 1;
        for(int ele : adj[node]) {
            if(visited[ele] == -1) {
                dfs(ele);
            }
        }
    }

    static class Cow {
        int x, y, index;
        Cow(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class Connection {
        int a, b;
        Connection(int a, int b) {
            this.a = a;
            this.b = b;
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