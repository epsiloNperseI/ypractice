package ypractice.multithreading.old;

import java.util.concurrent.Callable;

public class ThreadTimer {
    public static <T> T measure(Callable<T> task) throws Exception {
        long startTime = System.nanoTime();
        try {
            return task.call();
        } finally {
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println("Время выполнения: " + duration + " мс");
        }
    }
}
