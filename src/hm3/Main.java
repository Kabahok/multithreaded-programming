package hm3;

import hm3.runnables.CheckOrder;
import hm3.runnables.CheckPalindrom;
import hm3.runnables.CheckforRepeat;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static final AtomicInteger countOfLength3 = new AtomicInteger(0);
    public static final AtomicInteger countOfLength4 = new AtomicInteger(0);
    public static final AtomicInteger countOfLength5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

            List<Thread> threads = Arrays.asList(
              new Thread(new CheckforRepeat(texts)),
              new Thread(new CheckOrder(texts)),
              new Thread(new CheckPalindrom(texts))
            );

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }


        System.out.printf("Красивых слов с длиной 3: %d шт\n" +
                "Красивых слов с длиной 4: %d шт\n" +
                "Красивых слов с длиной 5: %d шт", countOfLength3.get(), countOfLength4.get(), countOfLength5.get());

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}
