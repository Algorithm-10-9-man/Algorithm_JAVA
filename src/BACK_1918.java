package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    A+B*((C+D+E)*E) -> (A+(B*(((C+D+E)*E)))) // ���ϱ� �¿�� ��ȣ �� �־���

 */
public class BACK_1918 {
    static int findRightBracketIdx(StringBuilder target, int rightIdx) {
        int leftCnt = 1; // leftCnt
        int rightCnt = 0; // rightCnt;
        int idx = rightIdx + 1;
        while (idx < target.length()) {
            char elem = target.charAt(idx);
            if (elem == ')') {
                rightCnt++;
                if (leftCnt == rightCnt) {
                    return idx;
                }
            } else if (elem == '(') {
                leftCnt++;
            }
            idx++;
        }
        return -1;
    }

    static int findLeftBracketIdx(StringBuilder target, int leftIdx) {
        int rightCnt = 1;
        int leftCnt = 0;
        int idx = leftIdx - 1;
        while (idx >= 0) {
            char elem = target.charAt(idx);
            if (elem == '(') {
                leftCnt++;
                if (leftCnt == rightCnt) {
                    return idx;
                }
            } else if (elem == ')') {
                rightCnt++;
            }
            idx--;
        }
        return -1;
    }
    static void makeBracket(StringBuilder original) {
        // left�� findLeft�ؼ� ���ʿ� ������, idx �ϳ� �þ�Ƿ� �������� +1�ؼ� ����ؾ���.
        for (int i = 0; i < original.length(); i++) {
            char elem = original.charAt(i);
            if (elem == '*' || elem == '/') {
                int leftIdx = i - 1;
                int rightIdx = i + 1;
                char leftTarget = original.charAt(leftIdx);
                char rightTarget = original.charAt(rightIdx);
                if (leftTarget == ')') {
                    findLeftBracketIdx(original, leftIdx);
                }
                if (rightTarget == '(') {
                    findRightBracketIdx(original, rightIdx);
                }
                original.insert(leftIdx, '(');
                original.insert(rightIdx + 1, ')');
                i++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder original = new StringBuilder(br.readLine());
        makeBracket(original);
        System.out.println(original);
    }
}
