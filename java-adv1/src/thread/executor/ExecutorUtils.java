package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            final int pool = poolExecutor.getPoolSize();
            final int active = poolExecutor.getActiveCount();
            final int queuedTasks = poolExecutor.getQueue().size();
            final long completedTask = poolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks +
                ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }

    // 추가
    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            final int pool = poolExecutor.getPoolSize();
            final int active = poolExecutor.getActiveCount();
            final int queuedTasks = poolExecutor.getQueue().size();
            final long completedTask = poolExecutor.getCompletedTaskCount();
            log(taskName + " -> [pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks +
                ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }
}
