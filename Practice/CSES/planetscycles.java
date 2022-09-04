import java.io.*;
import java.util.*;

public class planetscycles {
    static int n, steps;
    static int[] destinations, pathlength;
    static boolean[] visited;
    static LinkedList<Integer> path = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        destinations = new int[n];
        visited = new boolean[n];
        pathlength = new int[n];
        for(int i= 0; i < n; i++) {
            destinations[i] = in.nextInt() - 1;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                steps = 0;
                dfs(i);
                int decrement = 1;
                Integer last = path.peekLast();
                while(!path.isEmpty()) {
                    if(path.peekFirst().equals(last)) decrement = 0;
                    pathlength[path.poll()] = steps;
                    steps -= decrement;
                }
            }
        }
        for(int i = 0; i < n; i++) {
            out.print(pathlength[i] + " ");
        }
        in.close();
        out.close();
    }

    static void dfs(int n) {
        visited[n] = true;
        path.add(n);
        steps++;
        if(!visited[destinations[n]]) {
            dfs(destinations[n]);
        } else {
            path.add(destinations[n]);
            steps += pathlength[destinations[n]];
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