package src;

import java.io.*;

public class BACK_20437 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int minLength = Integer.MAX_VALUE;
            int maxLength = Integer.MIN_VALUE;
            String line = br.readLine();
            int[] cnts = new int[26];
            for (int j = 0; j < line.length(); j++) {
                cnts[line.charAt(j) - 'a']++;
            }
            int K = Integer.parseInt(br.readLine());
            if (K == 1) {
                bw.write("1 1\n");
            } else {
                for (int j = 0; j < line.length(); j++) {
                    if (cnts[line.charAt(j) - 'a'] < K) continue;
                    int cnt = 0;
                    for (int k = j; k < line.length(); k++) {
                        if (line.charAt(j) == line.charAt(k)) cnt++;
                        if (cnt == K) {
                            minLength = Math.min(minLength, k - j + 1);
                            maxLength = Math.max(maxLength, k - j + 1);
                            break;
                        }
                    }
                }
                if (minLength == Integer.MAX_VALUE || maxLength == Integer.MIN_VALUE) bw.write("-1\n");
                else {
                    bw.write(minLength + " " + maxLength);
                    bw.newLine();
                }
            }
        }
        bw.flush();
    }
}
