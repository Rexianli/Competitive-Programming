import java.io.*;
import java.util.*;

public class convention2 {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        Cow[] cowArr = new Cow[n];
        for(int i = 0; i < n; i++) {
            int a = in.nextInt(); int t = in.nextInt(); int s = i;
            cowArr[i] = new Cow(a, t, s);
//            out.println(cowArr[i].a + " " + cowArr[i].t + " " + cowArr[i].s);
        }
        Arrays.sort(cowArr, Comparator.comparingInt(x -> x.a));
        TreeMap<Integer, Cow> waitLine = new TreeMap<>();
        int currentTime = 0;
        int maxTime = 0;
        int cowIndex = 0;
        while(cowIndex < n || waitLine.size() > 0) {
            if(cowIndex < n && cowArr[cowIndex].a <= currentTime) {
                waitLine.put(cowArr[cowIndex].s, cowArr[cowIndex]);
                cowIndex++;
            } else if(waitLine.size() == 0) {
//                out.println(cowArr[cowIndex].s);
                currentTime = cowArr[cowIndex].a + cowArr[cowIndex].t;
                cowIndex++;
            } else {
                Cow cow = waitLine.pollFirstEntry().getValue();
                maxTime = Math.max(maxTime, currentTime - cow.a);
                currentTime += cow.t;
            }
        }
        out.println(maxTime);
        in.close();
        out.close();
    }

    static class Cow {
        int a, t, s;
        public Cow(int a, int t, int s) {
            this.a = a;
            this.t = t;
            this.s = s;
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