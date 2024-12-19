package thread.control.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinTest1Main {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(new MyTask(), "t1");
        final Thread t2 = new Thread(new MyTask(), "t2");
        final Thread t3 = new Thread(new MyTask(), "t3");

        t1.start(); // 3초
        t1.join(); // 대기

        t2.start(); // 3초
        t2.join(); // 대기

        t3.start(); // 3초
        t3.join(); // 대기
        System.out.println("모든 스레드 실행 완료");
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}
