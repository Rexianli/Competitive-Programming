import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class themeetingplacecannotbechanged {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
//        traveling north will decrease coordinate value, vice versa applies
        int n = in.nextInt();
        Friend[] friendArr = new Friend[n];
        for(int i = 0; i < n; i++) {
            friendArr[i] = new Friend(in.nextInt(), 0);
        }
        for(int i = 0; i < n; i++) {
            friendArr[i].speed = in.nextInt();
        }
        BigDecimal low = new BigDecimal(0);
        BigDecimal high = new BigDecimal(Integer.MAX_VALUE);
//        add more zeros to 0.0000001 if more accuracy is needed
        while((high.subtract(low)).compareTo(BigDecimal.valueOf(0.0000001)) > 0) {
//            out.println(low + " " + high);
            BigDecimal middle = low.add(high).divide(BigDecimal.valueOf(2));
            BigDecimal left = new BigDecimal(0);
            BigDecimal right = new BigDecimal(Integer.MAX_VALUE);
            for(int i = 0; i < n; i++) {
//                limit the BigDecimal to 8 digits for memory conservation?
                BigDecimal lower = BigDecimal.valueOf(friendArr[i].location).subtract(BigDecimal.valueOf(friendArr[i].speed).multiply(middle));
                BigDecimal higher = BigDecimal.valueOf(friendArr[i].location).add(BigDecimal.valueOf(friendArr[i].speed).multiply(middle));
                if(lower.compareTo(left) > 0) left = lower;
                if(higher.compareTo(right) < 0) right = higher;
            }
            if(left.compareTo(right) < 1) {
                high = middle;
            } else {
                low = middle;
            }
        }
//        out.println(low + " " + high);
        out.println(low.add(high).divide(BigDecimal.valueOf(2)));
        in.close();
        out.close();
    }

    static class Friend {
        int location, speed;
        Friend(int location, int speed) {
            this.location = location;
            this.speed = speed;
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