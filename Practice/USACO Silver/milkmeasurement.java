import java.io.*;
import java.util.*;

public class milkmeasurement {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int g = in.nextInt();
        Record[] log = new Record[n];
        TreeMap<Integer, Integer> tm = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> cow = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int a = in.nextInt(); int b = in.nextInt(); int c = Integer.parseInt(in.readLine());
            log[i] = new Record(a, b, c);
            cow.put(b, 0);
//            out.println(log[i].day + " " + log[i].id + " " + log[i].change);
        }
        int answer = 0;
        Arrays.sort(log, Comparator.comparingInt(x -> x.day));
        tm.put(0, n + 1);
        for(int i = 0; i < n; i++) {
            int outChange = cow.get(log[i].id);
//            out.println(outChange);
            boolean prevTop = outChange == tm.firstKey();
            int prevCount = tm.get(outChange);
            tm.put(outChange, tm.get(outChange) - 1);
            if(tm.get(outChange) == 0) {
                tm.remove(outChange);
            }
//            out.println(tm);
            outChange += log[i].change;
            cow.put(log[i].id, outChange);
            tm.put(outChange, tm.getOrDefault(outChange, 0) + 1);
            boolean curTop = outChange == tm.firstKey();
            int curCount = tm.get(outChange);
            if(prevTop) {
                if(curTop && curCount == prevCount && curCount == 1) {
                    continue;
                }
                answer++;
            } else if(curTop) {
                answer++;
            }
        }
        out.println(answer);
        in.close();
        out.close();
    }

    static class Record {
        int day, id, change;
        public Record(int day, int id, int change) {
            this.day = day;
            this.id = id;
            this.change = change;
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