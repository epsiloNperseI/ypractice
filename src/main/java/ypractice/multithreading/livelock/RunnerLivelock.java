package ypractice.multithreading.livelock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Демонстрация Livelock на примере двух потоков,
 * которые вежливо уступают друг другу и никак не могут продвинуться.
 *
 * <p>Источник: Java. Многопоточность. Урок 31. Livelock + пример.
 * <a href="https://www.youtube.com/watch?v=UX2R3BT9Sec&list=PLqnlz-HutZiRA06Y-LdunLtHN7XwBZgNG&index=31">YouTube</a>
 */
public final class RunnerLivelock {

    static void main(final String... args) {
        final Lock firstGivenLock = new ReentrantLock();
        final Lock secondGivenLock = new ReentrantLock();

        final Thread firstGivenThread = new Thread(
            new Task(firstGivenLock, "firstGivenLock", secondGivenLock, "secondGivenLock")
        );
        final Thread secondGivenThread = new Thread(
            new Task(secondGivenLock, "secondGivenLock", firstGivenLock, "firstGivenLock")
        );

        firstGivenThread.start();
        secondGivenThread.start();
    }

    private static final class Task implements Runnable {

        private static final String MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK =
            "Thread '%s' is trying to acquire lock '%s'%n";
        private static final String MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK =
            "Thread '%s' acquired lock '%s'%n";
        private static final String MESSAGE_TEMPLATE_RELEASE_LOCK =
            "Thread '%s' released lock '%s'%n";

        private final Lock firstLock;
        private final String firstLockName;
        private final Lock secondLock;
        private final String secondLockName;

        public Task(final Lock firstLock, final String firstLockName,
                    final Lock secondLock, final String secondLockName) {
            this.firstLock = firstLock;
            this.firstLockName = firstLockName;
            this.secondLock = secondLock;
            this.secondLockName = secondLockName;
        }

        @Override
        public void run() {
            final String currentThreadName =Thread.currentThread().getName();

            System.out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThreadName, firstLockName);
            this.firstLock.lock();
            try {
                System.out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, firstLockName);
                TimeUnit.MILLISECONDS.sleep(50);

                while (!this.tryAcquireSecondLock(currentThreadName)) {
                    TimeUnit.MILLISECONDS.sleep(50);
                    this.firstLock.unlock();
                    System.out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, firstLockName);
                    TimeUnit.MILLISECONDS.sleep(50);
                    System.out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThreadName, firstLockName);
                    this.firstLock.lock();
                    System.out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, firstLockName);
                    TimeUnit.MILLISECONDS.sleep(50);
                }

                try {
                    System.out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, secondLockName);
                } finally {
                    this.secondLock.unlock();
                    System.out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, secondLockName);
                }

            } catch (final InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            } finally {
                this.firstLock.unlock();
                System.out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, firstLockName);
            }
        }

        private boolean tryAcquireSecondLock(final String currentThreadName) {
            System.out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThreadName, secondLockName);
            return this.secondLock.tryLock();
        }
    }
}