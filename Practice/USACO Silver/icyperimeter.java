import java.io.*;
import java.util.*;

public class icyperimeter {
    static final int R_CHANGE[] = {0, 1, 0, -1};
    static final int C_CHANGE[] = {1, 0, -1, 0};
    static boolean[][] visited;
    static char[][] iceCream;
    static int n;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("perimeter.in");
        PrintWriter out = new PrintWriter("perimeter.out");
        n = in.nextInt();
        iceCream = new char[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            String row = in.readLine();
            for(int j = 0; j < n; j++) {
                iceCream[i][j] = row.charAt(j);
            }
        }
        int maxArea = 0;
        int perimeter = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(iceCream[i][j] == '#' && !visited[i][j]) {
                    int[] arr = floodfill(i, j);
                    if(arr[0] > maxArea) {
                        maxArea = arr[0];
                        perimeter = arr[1];
                    } else if(arr[0] == maxArea) {
                        if(arr[1] < perimeter) {
                            perimeter = arr[1];
                        }
                    }
                }
            }
        }
        out.println(maxArea + " " + perimeter);
        in.close();
        out.close();
    }

    static int[] floodfill(int row, int col) {
        int area = 0;
        int perimeter = 0;
        Stack<Pos> frontier = new Stack<>();
        frontier.push(new Pos(row, col));
        while(!frontier.isEmpty()){
            Pos curr = frontier.pop();
            row = curr.row;
            col = curr.col;
            if(row < 0 || row >= n || col < 0 || col >= n || iceCream[row][col] == '.') {
                perimeter++;
                continue;
            } else if(visited[row][col]) continue;
            visited[row][col] = true;
            area++;
            for(int i = 0; i < 4; i++) {
                frontier.add(new Pos(row + R_CHANGE[i], col + C_CHANGE[i]));
            }
        }
        int[] answer = new int[2];
        answer[0] = area;
        answer[1] = perimeter;
        return answer;
    }

    static class Pos {
        int row;
        int col;
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
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
            byte[] buf = new byte[1001];
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