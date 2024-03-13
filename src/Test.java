package src;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            String inputFileName = "C:\\Users\\Jeyoung\\Desktop\\testcases\\zagrade.in." + i;
            String outputFileName = "C:\\Users\\Jeyoung\\Desktop\\testcases\\zagrade.out." + i;

            System.out.println("Running test case: " + i);

            // ǥ���Է� ���𷺼�
            try {
                System.setIn(new FileInputStream(inputFileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            // ǥ����� ���𷺼�
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // ���⼭ ������ ���α׷��� �����ϰ� �˴ϴ�.
            BACK_2800.main(new String[]{});

            // ǥ������� ���ڿ��� ��ȯ�Ͽ� ����
            String actualOutput = outputStream.toString().trim();

            // ǥ����� ���𷺼� ���󺹱�
            System.setOut(System.out);

// ���� ���� ǥ�� ����� ������ Ȯ���غ���
            System.out.println("Restored standard output:");

// ���ܰ� �߻��� ��츦 ����Ͽ� try-catch ��� ���
            try {
                BufferedReader restoredOutputReader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = restoredOutputReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

// ���� ��� �б�
            String expectedOutput = readFromFile(outputFileName);

// ��� ��
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