import java.io.*;
import java.util.*;

public class makenonzerosum {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int[] arr = new int[n];
            int sum = 0;
            boolean allZero = true;
            for(int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                if(arr[i] != 0) allZero = false;
                sum += arr[i];
            }
            if(sum % 2 != 0) {
                out.println(-1);
                continue;
            }
            ArrayList<int[]> segments = new ArrayList<>();
            sum = 0;
            int sum2 = 0;
            int counter = 0;
            boolean firstZero = false;
            for(int i = 0; i < n; i++) {
                counter++;
                if(arr[i] != 0) {
                    sum++;
                }
                if(counter % 2 == 0) {
                    sum2 += arr[i];
                } else {
                    sum2 -= arr[i];
                }
                if(counter == 1 && arr[i] == 0) firstZero = true;
                if(sum == 2) {
                    if(sum2 == 0) {
                        segments.add(new int[]{i - counter + 1, i});
                    } else if(counter % 2 == 0) {
                        segments.add(new int[]{i - counter + 1, i - 1});
                        segments.add(new int[]{i, i});
                    } else if(firstZero) {
                        segments.add(new int[]{i - counter + 1, i - counter + 1});
                        segments.add(new int[]{i - counter + 2, i - 1});
                        segments.add(new int[]{i, i});
                    } else {
                        segments.add(new int[]{i - counter + 1, i - counter + 1});
                        segments.add(new int[]{i - counter + 2, i});
                    }
                    counter = 0;
                    firstZero = false;
                    sum = 0;
                    sum2 = 0;
                }
            }
            if(allZero) {
                out.println(n);
                for(int i = 0; i < n; i++) {
                    out.println((i + 1) + " " + (i + 1));
                }
                continue;
            }
            out.println(segments.size());
            segments.set(segments.size() - 1, new int[]{segments.get(segments.size() - 1)[0], n - 1});
            for(int[] a : segments) {
                out.println((a[0] + 1) + " " + (a[1] + 1));
            }
        }
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