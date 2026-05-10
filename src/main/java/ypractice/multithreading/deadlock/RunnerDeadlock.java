package ypractice.multithreading.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public final class RunnerDeadlock {

    static void main(final String... args) {
        final Lock firstGivenLock = new ReentrantLock();
        final Lock secondGivenLock = new ReentrantLock();

        final Thread firstGivenThread = new Thread(
            new Task(
                firstGivenLock,
                "firstGivenLock",
                secondGivenLock,
                "secondGivenLock"
            )
        );
        final Thread secondGivenThread = new Thread(
            new Task(
                secondGivenLock,
                "secondGivenLock",
                firstGivenLock,
                "firstGivenLock"
            )
        );
        // для предотвращения дедлока потоки программы должны захватывать замки/мониторы
        //в одном и том же порядке. Для фикса нужно в 17 строке поменять местами secondGivenLock и firstGivenLock.

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
        private final Lock secondLock;
        private final String firstLockName;
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
            final String currentThreadName = currentThread().getName();

            out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThreadName, firstLockName);
            this.firstLock.lock();
            try {
                out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, firstLockName);
                TimeUnit.MILLISECONDS.sleep(200);

                out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThreadName, secondLockName);
                this.secondLock.lock();
                try {
                    out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, secondLockName);
                } finally {
                    this.secondLock.unlock();
                    out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, secondLockName);
                }
            } catch (final InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            } finally {
                this.firstLock.unlock();
                out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, firstLockName);
            }
        }
    }
}
