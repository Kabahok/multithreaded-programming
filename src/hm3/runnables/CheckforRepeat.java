package hm3.runnables;

import hm3.Main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckforRepeat implements Runnable{

    private String[] texts;
    public CheckforRepeat(String[] texts) {
        this.texts = texts;
    }

    @Override
    public void run() {

        for (String text : texts) {
            Set<Character> set = text.chars().mapToObj(x -> (char) x).collect(Collectors.toSet());
            if (set.size() == 1) {
                switch (text.length()) {
                    case 3 -> Main.countOfLength3.incrementAndGet();
                    case 4 -> Main.countOfLength4.incrementAndGet();
                    case 5 -> Main.countOfLength5.incrementAndGet();
                }
            }
        }

    }
}
