package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_25401 {
    public static int cntChange(int[] nums, int startValue, int diff) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != startValue) count++;
            startValue += diff;
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N + 1];
        int minAnswer = 500;
        for (int i = 1; i < nums.length; i++) nums[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                float diff = (float)(nums[j] - nums[i]) / (j - i);
                if (diff != (int)diff) continue;
                minAnswer = Math.min(minAnswer, cntChange(nums, nums[i] - ((i - 1) * (int)diff), (int)diff));
            }
        }
        System.out.println(minAnswer);
    }
}
