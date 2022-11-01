import java.io.*;
import java.util.*;

public class problemwithrandomtests {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int num = in.nextInt();
        String str = String.valueOf(num);
        String answer = combine(str, str);
        int position1 = str.indexOf('1');
        int position2 = str.indexOf('0', position1);
        if(position1 == -1) {
            out.println(0);
            in.close();
            out.close();
            System.exit(0);
        }
        if(position2 == -1) {
            out.println(answer);
            in.close();
            out.close();
            System.exit(0);
        }
        int current = position1;
        int removed = 0;
        while(true) {
            if(current == n || str.charAt(current) == '1' && current > position2) break;
            String replace = combine(str, str.substring(position1, n - position1 - removed));
            if(evaluate(replace, answer)) answer = replace;
            current++;
            removed++;
        }
        out.println(answer);
        in.close();
        out.close();
    }

    static boolean evaluate(String a, String b) {
        if(a.length() != b.length()) return a.length() > b.length();
        return Integer.parseInt(a) > Integer.parseInt(b);
    }

    static String combine(String a, String b) {
        int size = Math.max(a.length(), b.length());
        String ret = String.valueOf(0).repeat(size);
        for(int i = 0; i < a.length(); i++) if(a.charAt(i) == '1') ret = replace(ret, i + size - a.length(), '1');
        for(int i = 0; i < b.length(); i++) if(b.charAt(i) == '1') ret = replace(ret, i + size - b.length(), '1');
        int count = 0;
        while(count < ret.length() && ret.charAt(count) == '0') count++;
        if(count == ret.length()) return "0";
        return ret.substring(count);
    }

    static String replace(String str, int index, char replace){
        if(str == null){
            return str;
        } else if(index < 0 || index >= str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        chars[index] = replace;
        return String.valueOf(chars);
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