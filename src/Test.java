package src;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            String inputFileName = "C:\\Users\\Jeyoung\\Desktop\\testcases\\zagrade.in." + i;
            String outputFileName = "C:\\Users\\Jeyoung\\Desktop\\testcases\\zagrade.out." + i;

            System.out.println("Running test case: " + i);

            // 표준입력 리디렉션
            try {
                System.setIn(new FileInputStream(inputFileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            // 표준출력 리디렉션
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // 여기서 실제로 프로그램을 실행하게 됩니다.
            BACK_2800.main(new String[]{});

            // 표준출력을 문자열로 변환하여 저장
            String actualOutput = outputStream.toString().trim();

            // 표준출력 리디렉션 원상복구
            System.setOut(System.out);

// 복구 전에 표준 출력의 내용을 확인해보기
            System.out.println("Restored standard output:");

// 예외가 발생한 경우를 대비하여 try-catch 블록 사용
            try {
                BufferedReader restoredOutputReader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = restoredOutputReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

// 예상 출력 읽기
            String expectedOutput = readFromFile(outputFileName);

// 결과 비교
            if (actualOutput.equals(expectedOutput)) {
                System.out.println("Test case " + i + " passed!");
            } else {
                System.out.println("Test case " + i + " failed!");
                System.out.println("Expected output: " + expectedOutput);
                System.out.println("Actual output: " + actualOutput);
            }
        }
    }

    private static String readFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            reader.close();
            return result.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}