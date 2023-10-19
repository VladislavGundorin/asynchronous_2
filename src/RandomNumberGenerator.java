import java.io.*;
import java.util.Random;

class RandomNumberGenerator {
    public void generateRandomNumbersToFile(String fileName, int minValue, int maxValue, int numberOfLines) {
        File file = new File(fileName);

        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                Random random = new Random();
                for (int i = 0; i < numberOfLines; i++) {
                    int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
                    writer.write(Integer.toString(randomNumber));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
