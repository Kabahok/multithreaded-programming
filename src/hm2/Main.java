package hm2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(() -> {
                int len = (int) Arrays.stream(generateRoute("RLRFR", 100).split(""))
                        .filter(x -> x.equals("R"))
                        .count();

                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(len)) {
                        sizeToFreq.put(len, sizeToFreq.get(len) + 1);
                    } else {
                        sizeToFreq.put(len, 1);
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        AtomicInteger keyWithMaxValue = new AtomicInteger(-1);
        AtomicInteger maxValue = new AtomicInteger(Integer.MIN_VALUE);

        sizeToFreq.forEach((key, value) -> {
            if (value > maxValue.get()) {
                maxValue.set(value);
                keyWithMaxValue.set(key);
            }
        });

        sizeToFreq.remove(keyWithMaxValue.get());
        System.out.printf("Самое частое количество повторений %d (встретилось %d раз)\n", keyWithMaxValue.get(), maxValue.get());

        System.out.println("Другие размеры:");
        sizeToFreq.forEach((key, value) -> System.out.printf("- %d (%d раз)\n", key, value));

    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
