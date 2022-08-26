import java.io.*;
import java.util.*;

public class whydidthecowcrosstheroad3 {
    static final int[] R_CHANGE = {0, 1, 0, -1};
    static final int[] C_CHANGE = {1, 0, -1, 0};
    static Coordinate[] position;
    static Set[][] bridge;
    static boolean[][] visited;
    static int n;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("countcross.in");
        PrintWriter out = new PrintWriter("countcross.out");
        n = in.nextInt();
        int k = in.nextInt();
        int r = in.nextInt();
        position = new Coordinate[n];
        bridge = new Set[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                bridge[i][j] = new HashSet<Coordinate>();
            }
        }
        for(int i = 1; i <= r; i++) {
            int r1 = in.nextInt() - 1;
            int c1 = in.nextInt() - 1;
            int r2 = in.nextInt() - 1;
            int c2 = in.nextInt() - 1;
            bridge[r1][c1].add(new Coordinate(r2, c2));
            bridge[r2][c2].add(new Coordinate(r1, c1));
        }
        for(int i = 0; i < k; i++) {
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            position[i] = new Coordinate(row, col);
        }
//        can be further optimized
        int answer = 0;
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < k; j++) {
                if(i < j) {
//                    out.println(i + " " + j);
                    for(boolean[] arr : visited) {
                        Arrays.fill(arr, false);
                    }
//                    out.println(position[i].r + " " + position[i].c);
                    floodfill(position[i].r, position[i].c);
//                    out.println(Arrays.deepToString(visited));
                    if(!visited[position[j].r][position[j].c]) answer++;
                }
            }
        }
        out.println(answer);
        in.close();
        out.close();
    }

    static void floodfill(int row, int col) {
        Stack<Pos> stack = new Stack<>();
        stack.push(new Pos(row, col));
        while(!stack.isEmpty()) {
            Pos curr = stack.pop();
            row = curr.row;
            col = curr.col;
            if(row < 0 || row >= n || col < 0 || col >= n || visited[row][col]) {
                continue;
            }
            visited[row][col] = true;
            for(int i = 0; i < 4; i++) {
                int rowChange = row + R_CHANGE[i];
                int colChange = col + C_CHANGE[i];
                if(bridge[row][col].contains(new Coordinate(rowChange, colChange))) continue;
                stack.add(new Pos(rowChange, colChange));
            }
        }
    }

    static class Pos {
        int row, col;
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Coordinate {
        int r, c;
        Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + r;
            result = prime * result + c;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            Coordinate other = (Coordinate) obj;
            if(r != other.r)
                return false;
            if(c != other.c)
                return false;
            return true;
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