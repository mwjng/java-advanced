package thread.control.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class TestMain {

    static Thread t1,t2;

    public static void main(String[] args) throws InterruptedException {
        final TestRunnable runnable1 = new TestRunnable();
        final TestRunnable2 runnable2 = new TestRunnable2();
        t1 = new Thread(runnable1, "t1");
        t2 = new Thread(runnable2, "t2");
        t1.start();
        t2.start();
        t1.interrupt();
        log("종료");
    }

    static class TestRunnable implements Runnable {

        @Override
        public void run() {
            log("t1 test");
            try {
                log(t1.isInterrupted());
                t2.join();
            } catch (InterruptedException e) {
                log(e);
                log(t1.isInterrupted());
            }
            log("t1 end");
        }
    }

    static class TestRunnable2 implements Runnable {

        @Override
        public void run() {
            log("t2 test");
            sleep(5000);
            log("t2 end");
        }
    }
}
