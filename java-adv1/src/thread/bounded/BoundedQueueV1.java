package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(final int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(final String data) {
        if(queue.size() == max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if(queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    // 예제에서는 toString() 메서드에 synchronized을 안붙였지만 원래는 붙여야됨
    @Override
    public String toString() {
        return queue.toString();
    }
}
