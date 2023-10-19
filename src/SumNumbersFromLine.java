import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SumNumbersFromLine {
    private int totalSum = 0;

    public  void sumNumbersAndAdd(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                totalSum += number;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getTotalSum() {
        return totalSum;
    }
}