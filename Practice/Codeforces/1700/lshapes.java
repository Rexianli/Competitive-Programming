import java.io.*;
import java.util.*;

public class lshapes {
    static char[][] arr;
    static boolean[][] visited;
    static int n;
    static int m;
    static boolean valid;
    static final int[] R_CHANGE = {0, 1, 0, -1};
    static final int[] C_CHANGE = {1, 0, -1, 0};
    static final int[] R_CHANGE2 = {-1, 1, -1, 1};
    static final int[] C_CHANGE2 = {-1, -1, 1, 1};
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        For:
        for(int z = 0; z < t; z++) {
            n = in.nextInt();
            m = in.nextInt();
            arr = new char[n][m];
            visited = new boolean[n][m];
            valid = true;
            for(boolean[] row : visited) {
                Arrays.fill(row, false);
            }
            for(int i = 0; i < n; i++) {
                String line = in.readLine();
                for(int j = 0; j < m; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }
//            out.println(Arrays.deepToString(arr));
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(arr[i][j] == '*' && !visited[i][j]) {
                        floodfill(i, j);
                        if(!valid) {
                            out.println("NO");
                            continue For;
                        }
                    }
                }
            }
//            floodfill(1, 2);
            out.println("YES");
        }
        in.close();
        out.close();
    }

    static void floodfill(int row, int col) {
        int num = 0;
        HashSet<Pos> positionSet = new HashSet<>();
        Stack<Pos> stack = new Stack<>();
        stack.push(new Pos(row, col));
        while(!stack.isEmpty()) {
            Pos curr = stack.pop();
            row = curr.row;
            col = curr.col;
            if(row < 0 || row >= n || col < 0 || col >= m || visited[row][col] || arr[row][col] == '.') {
                continue;
            }
            visited[row][col] = true;
            if(num < 3) {
                positionSet.add(new Pos(row, col));
            }
            num++;
            for(int i = 0; i < 4; i++) {
                int rowChange = row + R_CHANGE[i];
                int colChange = col + C_CHANGE[i];
                stack.add(new Pos(rowChange, colChange));
            }
        }
        if(num != 3) {
            valid = false;
            return;
        }
//        for(Pos position : positionSet) {
//            System.out.println(position.row + " " + position.col);
//        }
        int minX = Integer.MAX_VALUE, maxX = 0, minY = Integer.MAX_VALUE, maxY = 0;
        for(Pos position : positionSet) {
            for(int i = 0; i < 4; i++) {
                int rowChange = position.row + R_CHANGE2[i];
                int colChange = position.col + C_CHANGE2[i];
                if(rowChange < 0 || rowChange >= n || colChange < 0 || colChange >= m || positionSet.contains(new Pos(rowChange, colChange))) {
                    continue;
                }
                if(arr[rowChange][colChange] == '*') {
                    valid = false;
                    return;
                }
            }
            minX = Math.min(minX, position.row);
            maxX = Math.max(maxX, position.row);
            minY = Math.min(minY, position.col);
            maxY = Math.max(maxY, position.col);
        }
        if(maxX - minX != 1 || maxY - minY != 1) {
            valid = false;
            return;
        }
//        System.out.println(num);
    }

    static class Pos {
        int row, col;
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            Pos other = (Pos) obj;
            if(row != other.row)
                return false;
            if(col != other.col)
                return false;
            return true;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
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