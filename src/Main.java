public class Main {
    public static void main(String[] args) {
        int numberOfFiles = 13;
        int minValue = -10;
        int maxValue = 10;
        int numberOfLines = 1000;
        int numberOfThreads = 2;

        RandomNumberGenerator generator = new RandomNumberGenerator();
        for (int i = 1; i <= numberOfFiles; i++) {
            String fileName = "file" + i + ".txt";
            generator.generateRandomNumbersToFile(fileName, minValue, maxValue, numberOfLines);
        }

        long startSingleThread = System.currentTimeMillis();
        int totalSumSingleThread = 0;
        SumNumbersFromLine sumCalculator = new SumNumbersFromLine();
        for (int i = 1; i <= numberOfFiles; i++) {
            sumCalculator.sumNumbersAndAdd("file" + i + ".txt");
        }
        totalSumSingleThread = sumCalculator.getTotalSum();
        long endSingleThread = System.currentTimeMillis();

        System.out.println("Сумма при однопоточном выполнении: " + totalSumSingleThread);
        System.out.println("Время однопоточного выполнения: " + (endSingleThread - startSingleThread) + " мс");

        long startMultiThread = System.currentTimeMillis();
        int totalSumMultiThread = 0;

        SumNumbersFromLine[] calculators = new SumNumbersFromLine[numberOfFiles];
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfFiles; i++) {
            calculators[i] = new SumNumbersFromLine();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                for (int j = threadIndex; j < numberOfFiles; j += numberOfThreads) {
                    calculators[j].sumNumbersAndAdd("file" + (j + 1) + ".txt");
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < numberOfFiles; i++) {
            totalSumMultiThread += calculators[i].getTotalSum();
        }

        long endMultiThread = System.currentTimeMillis();

        System.out.println("Сумма при многопоточном выполнении: " + totalSumMultiThread);
        System.out.println("Время многопоточного выполнения: " + (endMultiThread - startMultiThread) + " мс");
    }
}