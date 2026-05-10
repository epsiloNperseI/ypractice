package ypractice.multithreading.atomic_variables;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Потокобезопасный генератор чётных чисел на основе {@link AtomicInteger}.
 * Вместо явной блокировки через {@code Lock} использует атомарную операцию
 * {@code getAndAdd} — быстрее и проще.
 *
 * <p>Источник: Java. Многопоточность. Урок 33. Классы атомарных переменных.
 * <a href="https://www.youtube.com/watch?v=Mamy54xktyo&list=PLqnlz-HutZiRA06Y-LdunLtHN7XwBZgNG&index=33">YouTube</a>
 */
public final class EvenNumberGenerator {

    private static final int GENERATION_DELTA = 2;

    private final AtomicInteger value = new AtomicInteger();

    /**
     * Генерирует следующее чётное число.
     * Атомарно читает текущее значение и прибавляет {@code GENERATION_DELTA}.
     *
     * @return текущее значение до прибавления
     */
    public int generate() {
        return this.value.getAndAdd(GENERATION_DELTA);
    }

    /**
     * Возвращает текущее накопленное значение генератора.
     *
     * @return текущее значение
     */
    public int getValue() {
        return this.value.intValue();
    }
}
