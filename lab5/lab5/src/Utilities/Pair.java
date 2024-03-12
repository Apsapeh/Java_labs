package Utilities;

/**
 * Класс для хранения пары значений
 * @param <T1> - тип первого значения
 * @param <T2> - тип второго значения
 */
public class Pair <T1, T2> {
    private T1 first;
    private T2 second;

    /**
     * Конструктор класса
     * @param first - первое значение
     * @param second - второе значение
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Получить первое значение
     * @return - первое значение
     */
    public T1 getFirst() {
        return this.first;
    }

    /**
     * Получить второе значение
     * @return - второе значение
     */
    public T2 getSecond() {
        return this.second;
    }
}
