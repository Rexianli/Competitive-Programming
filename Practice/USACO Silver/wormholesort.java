import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class wormholesort {
    static ArrayList<Integer>[] adj;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("wormsort.in");
        PrintWriter out = new PrintWriter("wormsort.out");
        int n = in.nextInt();
        int m = in.nextInt();
        adj = new ArrayList[n];
        visited = new int[n];
        int[] locations = new int[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            locations[i] = in.nextInt() - 1;
        }
        wormhole[] wormholeArr = new wormhole[m];
        for(int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            wormholeArr[i] = new wormhole(a, b, in.nextInt(), i);
        }
        Arrays.sort(wormholeArr, Comparator.comparingInt(x -> x.w));
        if(IntStream.range(0, locations.length - 1).noneMatch(i -> locations[i] > locations[i + 1])) {
            out.println(-1);
            in.close();
            out.close();
        }
        int low = 0;
        int high = n - 1;
        while(low < high) {
//            out.println(low + " " + high);
            int middle = (low + high + 1) / 2;
            for(int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }
            Arrays.fill(visited, -1);
            for(int i = middle; i <= high; i++) {
                adj[wormholeArr[i].a].add(wormholeArr[i].b);
                adj[wormholeArr[i].b].add(wormholeArr[i].a);
            }
            int count = 0;
            for(int i = 0; i < n; i++) {
                if(visited[i] < 0) {
                    dfs(i, count);
                    count++;
                }
            }
            boolean works = true;
            for(int i = 0; i < n; i++) {
                if(visited[i] != visited[locations[i]]) {
                    works = false;
                    break;
                }
            }
//            out.println(Arrays.toString(visited));
            if(works) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        out.println(wormholeArr[low].w);
        in.close();
        out.close();
    }

    static class wormhole {
        int a, b, w, id;
        wormhole(int a, int b, int w, int id) {
            this.a = a;
            this.b = b;
            this.w = w;
            this.id = id;
        }
    }

    static void dfs(int node, int count) {
        visited[node] = count;
        for(int ele : adj[node]) {
            if(visited[ele] == -1) {
                dfs(ele, count);
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