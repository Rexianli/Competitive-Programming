import java.io.*;
import java.util.*;

public class fieldreduction {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader("reduce.in");
        PrintWriter out = new PrintWriter("reduce.out");
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] x2 = new int[n];
        int[] y2 = new int[n];
        for(int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            x2[i] = x[i];
            y2[i] = y[i];
        }
        Arrays.sort(x);
        Arrays.sort(y);
        int answer = Integer.MAX_VALUE;
        for(int a = 0; a < 4; a++) {
            int xs = x[a];
            for(int b = n - 4; b < n; b++) {
                int xl = x[b];
                for(int c = 0; c < 4; c++) {
                    int ys = y[c];
                    for(int d = n - 4; d < n; d++) {
                        int yl = y[d];
                        int counter = 0;
                        for(int i = 0; i < n; i++) {
                            if(x2[i] < xs || x2[i] > xl || y2[i] < ys || y2[i] > yl) {
                                counter++;
                            }
                        }
                        if(counter <= 3) {
                            int area = Math.abs(xl - xs) * Math.abs(yl - ys);
                            answer = Math.min(answer, area);
                        }
                    }
                }
            }
        }
        out.println(answer);
        in.close();
        out.close();
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