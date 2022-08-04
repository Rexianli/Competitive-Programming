import java.io.*;
import java.util.*;

public class magicship {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int startX = in.nextInt(), startY = in.nextInt();
        int endX = in.nextInt(), endY = in.nextInt();
        int n = in.nextInt();
        String forecastStr = in.readLine();
        long low = 0;
        long high = (long) 1e18;
        int x;
        int y;
        int[] XY = getXY(forecastStr);
        x = XY[0];
        y = XY[1];
        while(low < high) {
            long middle = (low + high) / 2;
            String curStr;
            long resX = startX;
            long resY = startY;
            if(middle <= n) {
                curStr = forecastStr.substring(0, (int) middle);
            } else {
                long repAmount = middle / n;
                long mod = middle % n;
                resX += repAmount * (long) x;
                resY += repAmount * (long) y;
                curStr = forecastStr.substring(0, (int) mod);
            }
            int[] curXY = getXY(curStr);
            resX += curXY[0];
            resY += curXY[1];
            long totalDif = Math.abs(resX - endX) + Math.abs(resY - endY);
            long movesLeft = middle - totalDif;
//            out.println(resX + " " + resY + " " + middle + " " + low + " " + high);
            if(movesLeft < 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        if(high == 1e18) {
            out.println(-1);
        } else {
            out.println(low);
        }
        in.close();
        out.close();
    }

    static int[] getXY(String forecastStr) {
        int x = 0;
        int y = 0;
        int[] ret = new int[2];
        for(int i = 0; i < forecastStr.length(); i++) {
            if(forecastStr.charAt(i) == 'U') y++;
            else if(forecastStr.charAt(i) == 'D') y--;
            else if(forecastStr.charAt(i) == 'L') x--;
            else if(forecastStr.charAt(i) == 'R') x++;
        }
        ret[0] = x;
        ret[1] = y;
        return ret;
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
            byte[] buf = new byte[100001];
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