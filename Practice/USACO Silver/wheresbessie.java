import java.io.*;
import java.util.*;

public class wheresbessie {
    static final int[] R_CHANGE = {0, 1, 0, -1};
    static final int[] C_CHANGE = {1, 0, -1, 0};
    static char[][] grid;
    static int n;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        grid = new char[n][n];
        for(int i = 0; i < n; i++) {
            String row = in.readLine();
            for(int j = 0; j < n; j++) {
                grid[i][j] = row.charAt(j);
            }
        }
        HashSet<Character> set = new HashSet<>();
        List<PCL> pclArray = new ArrayList<>();
        int num = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
//                top left
                for(int i2 = i; i2 < n; i2++) {
                    Loop:
                    for(int j2 = j; j2 < n; j2++) {
//                        bottom left
                        set.clear();
                        for(int row = i; row <= i2; row++) {
                            for(int col = j; col <= j2; col++) {
//                                iterate through rectangle
                                set.add(grid[row][col]);
                                if(set.size() > 2) continue Loop;
                            }
                        }
//                        out.println(i + " " + j + "   " + i2 + " " + j2);
                        if(floodfill(i, j, i2, j2)) {
                            pclArray.add(new PCL(i, j, i2, j2));
                        }
                    }
                }
            }
        }
        HashSet<PCL> toRemove = new HashSet<>();
        for(PCL pcl1 : pclArray) {
            for(PCL pcl2 : pclArray) {
                boolean remove1 = false;
                boolean remove2 = false;
                if(pcl1.i == pcl2.i && pcl1.j == pcl2.j && pcl1.i2 == pcl2.i2 && pcl1.j2 == pcl2.j2) {
                    continue;
                } else if(pcl2.i <= pcl1.i && pcl2.j <= pcl1.j && pcl2.i2 >= pcl1.i2 && pcl2.j2 >= pcl1.j2) {
                    remove1 = true;
                } else if(pcl1.i <= pcl2.i && pcl1.j <= pcl2.j && pcl1.i2 >= pcl2.i2 && pcl1.j2 >= pcl2.j2) {
                    remove2 = true;
                }
                if(remove1) toRemove.add(pcl1);
                if(remove2) toRemove.add(pcl2);
            }
        }
        pclArray.removeAll(toRemove);
//        for(PCL pcl : pclArray) out.println(pcl.i + " " + pcl.j + " " + pcl.i2 + " " + pcl.j2);
        out.println(pclArray.size());
        in.close();
        out.close();
    }

    static boolean floodfill(int row1, int col1, int row2, int col2) {
        boolean[][] visited = new boolean[n][n];
        int components = 0;
        While:
        while(true) {
            char curChar = 0;
            int row = 0;
            int col = 0;
            Loop:
            for(int i = row1; i <= row2; i++) {
                for(int j = col1; j <= col2; j++) {
                    if(!visited[i][j]) {
                        curChar = grid[i][j];
                        row = i;
                        col = j;
                        break Loop;
                    }
                    if(i == row2 && j == col2) break While;
                }
            }
            components++;
            Stack<Pos> stack = new Stack<>();
            stack.push(new Pos(row, col));
            while(!stack.isEmpty()) {
                Pos curr = stack.pop();
                row = curr.row;
                col = curr.col;
                if(row < row1 || row > row2 || col < col1 || col > col2 || visited[row][col] || grid[row][col] != curChar) {
                    continue;
                }
                visited[row][col] = true;
                for (int i = 0; i < 4; i++) {
                    int rowChange = row + R_CHANGE[i];
                    int colChange = col + C_CHANGE[i];
                    stack.add(new Pos(rowChange, colChange));
                }
            }
        }
        return components == 3;
    }

    static class Pos {
        int row, col;
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class PCL {
        int i, j, i2, j2;
        PCL(int i, int j, int i2, int j2) {
            this.i = i;
            this.j = j;
            this.i2 = i2;
            this.j2 = j2;
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