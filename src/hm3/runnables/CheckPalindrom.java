package hm3.runnables;

import hm3.Main;

public class CheckPalindrom implements Runnable {

    private String text;

    public CheckPalindrom(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        if (new StringBuilder(text).reverse().toString().equals(text)) {
            switch (text.length()) {
                case 3 -> Main.countOfLength3.incrementAndGet();
                case 4 -> Main.countOfLength4.incrementAndGet();
                case 5 -> Main.countOfLength5.incrementAndGet();
            }
        }
    }
}
