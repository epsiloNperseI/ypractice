package ypractice.multithreading.atomic_variables;

import static java.lang.Thread.currentThread;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

/**
 * Запускает 5 потоков, каждый из которых вызывает {@code generate()} 10000 раз.
 * После завершения всех потоков проверяет что итоговое значение генератора
 * совпадает с ожидаемым — это доказывает потокобезопасность {@link EvenNumberGenerator}.
 *
 * <p>Источник: Java. Многопоточность. Урок 33. Классы атомарных переменных.
 * <a href="https://www.youtube.com/watch?v=NCaxM5V79PM&list=PLqnlz-HutZiRA06Y-LdunLtHN7XwBZgNG&index=33">YouTube</a>
 */
public final class Runner {

    static void main(final String... args) {
        final EvenNumberGenerator generator = new EvenNumberGenerator();

        final int taskGenerationCounts = 10000;
        final Runnable generatingTask = () -> range(0, taskGenerationCounts)
            .forEach(i -> generator.generate());

        final int amountOfGeneratingThreads = 5;
        final Thread[] generatingThreads = createThreads(generatingTask, amountOfGeneratingThreads);

        startThreads(generatingThreads);
        waitUntilFinish(generatingThreads);

        final int expectedGeneratorValue = amountOfGeneratingThreads * taskGenerationCounts * 2;
        final int actualGeneratorValue = generator.getValue();

        if (expectedGeneratorValue != actualGeneratorValue) {
            throw new RuntimeException(
                "Expected is %d but was %d".formatted(expectedGeneratorValue, actualGeneratorValue)
            );
        }

        System.out.printf("OK! Expected: %d, Actual: %d%n", expectedGeneratorValue, actualGeneratorValue);
    }

    private static Thread[] createThreads(final Runnable task, final int amountOfThreads) {
        return range(0, amountOfThreads)
            .mapToObj(i -> new Thread(task))
            .toArray(Thread[]::new);
    }

    private static void startThreads(final Thread[] threads) {
        stream(threads).forEach(Thread::start);
    }

    private static void waitUntilFinish(final Thread[] threads) {
        stream(threads).forEach(Runner::waitUntilFinish);
    }

    private static void waitUntilFinish(final Thread thread) {
        try {
            thread.join();
        } catch (final InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }
}
