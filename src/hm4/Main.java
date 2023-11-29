package hm4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final String letters = "abc";
    private static final int textLength = 100_000;
    private static final int numOfTexts = 10_000;

    private static final BlockingQueue<String> queueA = new ArrayBlockingQueue<>(100);
    private static final BlockingQueue<String> queueB = new ArrayBlockingQueue<>(100);
    private static final BlockingQueue<String> queueC = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
        Thread textGeneratorThread = new Thread(() ->{
            for (int i = 0; i < numOfTexts; i++) {
                String text = generateText(letters, textLength);
                try {
                    char maxChar = getMaxCharacter(text);
                    switch (maxChar) {
                        case 'a':
                            queueA.put(text);
                            break;
                        case 'b':
                            queueB.put(text);
                            break;
                        case 'c':
                            queueC.add(text);
                            break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        textGeneratorThread.start();

        Thread counterAThread = new Thread(() -> countMaxChar('a', queueA));
        Thread counterBThread = new Thread(() -> countMaxChar('b', queueB));
        Thread counterCThread = new Thread(() -> countMaxChar('c', queueC));

        counterAThread.start();
        counterBThread.start();
        counterCThread.start();


    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    private static void countMaxChar(char character, BlockingQueue<String> queue) {
        int maxCount = 0;
        String maxText = "";

        try {
            while (true) {
                String text = queue.take();
//                if (text.isEmpty()) {
//                    queue.put("");
//                    break;
//                }
                int count = countCharacter(text, character);
                if (count > maxCount) {
                    maxCount = count;
                    maxText = text;
                    System.out.println("Символ " + character + ": Максимальное количество = " + maxCount);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static int countCharacter(String text, char character) {
        int count = 0;
        for (char i : text.toCharArray()) {
            if (i == character) {
                count++;
            }
        }
        return count;
    }

    private static char getMaxCharacter(String text) {
        int countA = 0, countB = 0, countC = 0;

        for (char i : text.toCharArray()) {
            if (i == 'a') {
                countA++;
            } else if (i == 'b') {
                countB++;
            } else if (i == 'c') {
                countC++;
            }
        }

        if (countA >= countB && countA >= countC) {
            return 'a';
        } else if (countB >= countA && countB >= countC) {
            return 'b';
        } else {
            return 'c';
        }
    }
}


