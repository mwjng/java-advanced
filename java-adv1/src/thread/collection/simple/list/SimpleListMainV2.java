package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
//        test(new SyncList());
        test(new SyncProxyList(new BasicList()));
    }

    private static void test(final SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        // A를 리스트에 저장하는 코드
        final Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        // B를 리스트에 저장하는 코드
        final Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        final Thread thread1 = new Thread(addA, "Thead-1");
        final Thread thread2 = new Thread(addB, "Thead-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log(list);
    }
}
