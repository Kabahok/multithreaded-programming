package hm3.runnables;

import hm3.Main;

public class CheckOrder implements Runnable{
    private String text;

    public CheckOrder (String text) {
        this.text = text;
    }

    @Override
    public void run() {
        char[] charList = text.toCharArray();
        boolean flag = true;
        for (int i = 0; i < (charList.length - 1); i++) {
            if (!(charList[i] <= charList[i+1])) {
                flag = false;
            }
        }

        if (flag) {
            switch (text.length()) {
                case 3 -> Main.countOfLength3.incrementAndGet();
                case 4 -> Main.countOfLength4.incrementAndGet();
                case 5 -> Main.countOfLength5.incrementAndGet();
            }
        }
    }
}
