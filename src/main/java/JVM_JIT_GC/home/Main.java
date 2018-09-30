package JVM_JIT_GC.home;

public class Main {
    public static void main(String[] args) {
        Runnable thread = () -> {
            String x = "";
            while (true) {
                x = x + "1";
            }
        };


        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(thread).start();

        }


    }


}

