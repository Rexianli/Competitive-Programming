import java.io.*;
import java.util.*;

public class jobscheduling {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int d = in.nextInt();
        int m = in.nextInt();
        Pair[] arr = new Pair[m];
        for(int i = 0; i < m; i++) {
            arr[i] = new Pair(i + 1, in.nextInt());
        }
        Arrays.sort(arr, Comparator.comparingInt(x -> x.day));
//        for(Pair pairs : arr) {
//            out.println(pairs.request + " " + pairs.day);
//        }
        int low = 0;
        int high = m + 1;
        int answer = 0;
        List<List<Integer>> ansList = null;
        while (low <= high) {
//            out.println(low + " " + high);
            int middle = (low + high) / 2;
            List<List<Integer>> list = new ArrayList<>();
            if (m / middle > n) {
                low = middle + 1;
                continue;
            }
            boolean works = true;
            for (int i = 0; i < m; i += middle) {
                list.add(new ArrayList<>());
                for (int j = i; j < i + middle; j++) {
                    list.get(i / middle).add(arr[j].request);
                    if (arr[j].day >= i / middle + d + 1) { //if day surpasses delay, lower range and continue
                        works = false;
                    }
                }
            }
            if(works) {
                answer = middle;
                ansList = list;
                break;
            } else {
                high = middle - 1;
            }
        }
        out.println(answer);
        for(List<Integer> list : ansList) {
            for(Integer integer : list) {
                out.print(integer + " ");
            }
            out.print(0);
            out.println();
        }
        if(ansList.size() < n) {
            for(int i = 0; i < n - ansList.size(); i++) {
                out.println(0);
            }
        }
//        out.println(Arrays.toString(arr));
        in.close();
        out.close();
    }

    static class Pair {
        int request, day;
        Pair(int request, int day) {
            this.request = request;
            this.day = day;
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