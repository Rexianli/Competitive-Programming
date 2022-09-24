import java.io.*;
import java.util.*;

public class colorwithoccurrences {
    static String t = "";
    static ArrayList<indices> answers;
    static String[] s;
    static boolean works = true;
    static int answerNum = 0;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int q = in.nextInt();
        for(int z = 0; z < q; z++) {
            t = in.readLine();
            int n = in.nextInt();
            s = new String[n];
            works = true;
            answerNum = 0;
//            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                s[i] = in.readLine();
//                map.put(s[i], i);
            }
            answers = new ArrayList<>();
            solve(0, 0);
            out.println(answers.get(0).w + answers.get(0).p);
            if(false) {
                out.println(-1);
            } else {
                out.println(answerNum);
                for(indices answer : answers) {
                    out.println((answer.w + 1) + " " + (answer.p + 1));
                }
            }
        }
        in.close();
        out.close();
    }

    static void solve(int a, int b) {
        int max = 0, id = -1, pos = -1;
        for(int i = a; i <= b; i++) {
            for(int j = 0; j < s.length; j++) {
                String cur = s[j];
                if(i + cur.length() > t.length() || i + cur.length() <= b) continue;
                if(t.substring(i, i + cur.length()).equals(cur)) {
                    if(i + cur.length() > max) {
                        max = i + cur.length();
                        id = j;
                        pos = i;
                    }
                }
            }
        }
        if(id == -1) {
            works = false;
            return;
        } else {
            answers.add(new indices(id, pos));
            answerNum++;
            if(max == t.length()) {
                return;
            } else {
                solve(Math.max(pos, b + 1), max);
            }
        }
    }

    static class indices {
        int w, p;
        indices(int w, int p) {
            this.w = w;
            this.p = p;
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
            byte[] buf = new byte[102];
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