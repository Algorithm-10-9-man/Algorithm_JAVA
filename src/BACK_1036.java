package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class BACK_1036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, BigInteger> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            for (int j = 0; j < arr[i].length(); j++) {
                char elem = arr[i].charAt(j);
                BigInteger num = new BigInteger("Z", 36);
                BigInteger now = new BigInteger(String.valueOf(elem), 36);
                BigInteger position = new BigInteger("36").pow(arr[i].length() - j - 1);
                BigInteger res = num.multiply(position).subtract(now.multiply(position));
                if (!map.containsKey(elem)) {
                    map.put(elem, res);
                } else {
                    map.put(elem, map.get(elem).add(res));
                }
            }
        }
        List<Map.Entry<Character, BigInteger>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        int K = Integer.parseInt(br.readLine());
        Set<Character> set;
        if (list.size() <= K) {
            set = new HashSet<>(map.keySet());
        } else {
            set = new HashSet<>();
            for (int i = 0; i < K; i++) {
                set.add(list.get(i).getKey());
            }
        }
        BigInteger subSum = new BigInteger("0");
        for (int j = 0; j < arr.length; j++) {
            StringBuilder target = new StringBuilder(arr[j]);
            for (int k = 0; k < target.length(); k++) {
                if (set.contains(target.charAt(k))) {
                    target.setCharAt(k, 'Z');
                }
            }
            subSum = subSum.add(new BigInteger(target.toString(), 36));
        }
        StringBuilder output = new StringBuilder(subSum.toString(36));
        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) >= 'a' && output.charAt(i) <= 'z') output.setCharAt(i, (char)(output.charAt(i) + ('A' - 'a')));
        }
        System.out.println(output);
    }
}
