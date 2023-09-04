package hm3.runnables;

import hm3.Main;

public class CheckPalindrom implements Runnable {

    private String[] texts;

    public CheckPalindrom(String[] texts) {
        this.texts = texts;
    }

    @Override
    public void run() {

        for (String text : texts) {
            if (new StringBuilder(text).reverse().toString().equals(text)) {
                switch (text.length()) {
                    case 3 -> Main.countOfLength3.incrementAndGet();
                    case 4 -> Main.countOfLength4.incrementAndGet();
                    case 5 -> Main.countOfLength5.incrementAndGet();
                }
            }
        }

    }
}
