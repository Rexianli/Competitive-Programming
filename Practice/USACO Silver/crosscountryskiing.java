import java.io.*;
import java.util.*;

public class crosscountryskiing {
    static final int R_CHANGE[] = {0, 1, 0, -1};
    static final int C_CHANGE[] = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] grid;
    static int n, m;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("ccski.in");
        PrintWriter out = new PrintWriter("ccski.out");
        n = in.nextInt();
        m = in.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                grid[i][j] = in.nextInt();
            }
        }
        ArrayList<Indices> indexArr = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(in.nextInt() == 1) {
                    indexArr.add(new Indices(i, j));
                }
            }
        }
        int low = 0;
        int high = (int) 1e9;
        while(low < high) {
            for(boolean[] row : visited) {
                Arrays.fill(row, false);
            }
            int middle = (low + high) / 2;
            floodfill(indexArr.get(0).x, indexArr.get(0).y, middle);
            boolean works = true;
            for(Indices index : indexArr) {
                if (!visited[index.x][index.y]) {
                    works = false;
                    break;
                }
            }
            if(works) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        out.println(low);
        in.close();
        out.close();
    }

    static void floodfill(int row, int col, int d) {
        Stack<Pos> stack = new Stack<>();
        stack.push(new Pos(row, col));
        while(!stack.isEmpty()) {
            Pos curr = stack.pop();
            row = curr.row;
            col = curr.col;
            if(row < 0 || row >= n || col < 0 || col >= m || visited[row][col]) {
                continue;
            }
            visited[row][col] = true;
            for(int i = 0; i < 4; i++) {
                int rowChange = row + R_CHANGE[i];
                int colChange = col + C_CHANGE[i];
                try {
                    if(Math.abs(grid[rowChange][colChange] - grid[row][col]) > d) continue;
                } catch(Exception ArrayIndexOutOfBoundsException) {
                    continue;
                }
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

    static class Indices {
        int x, y;
        Indices(int x, int y) {
            this.x = x;
            this.y = y;
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
            byte[] buf = new byte[501];
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