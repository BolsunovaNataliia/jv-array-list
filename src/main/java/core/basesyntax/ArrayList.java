package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private T value;
    private Object[] elements;
    private int index;
    private static final int INITIAL_CAPACITY = 10;
    private int capacity;
    private int numberOfElements;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }

    // У методі add(E element) перед додаванням кожного нового елемента
    // перевіряється capacity (місткість) масиву.
    // Якщо поточна кількість елементів (разом з новим елементом) перевищує довжину elementData,
    // то створюється новий масив, місткість якого більша в 1,5 рази порівняно зі старим масивом,
    // а потім всі елементи зі старого масиву копіюються в новий.
    // Перевіряємо, що індекс позиції менше розміру масиву. Вставляємо.
    @Override
    public void add(T value) {
        checkCapacity(capacity);

        elements[numberOfElements] = value;
        numberOfElements++;
    }

    private void checkCapacity(int capacity) {
        if (numberOfElements > capacity) {
            int newCapacity = elements.length + elements.length >> 1;
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, numberOfElements);
            elements = newElements;
        }
    }

    private void rangeIndexCheck(int index) {
        if (index >= numberOfElements || index < 0)
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " outside array");
    }

    // Коли новий елемент вставляється на вказану позицію i,
    // усі елементи в масиві elementData, починаючи з позиції i, зміщуються праворуч.
    // System.arrayCopy()
    // використовується метод System.arrayCopy(),
    // який копіює старі значення на нові позиції.
    @Override
    public void add(T value, int index) {
        rangeIndexCheck(index);
        checkCapacity(capacity + 1);
        for (int i = numberOfElements; i > index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = value;
        numberOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        checkCapacity(numberOfElements + list.size());
        System.arraycopy(list, 0, elements, numberOfElements, list.size());
    }

    @Override
    public T get(int index) {
        rangeIndexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeIndexCheck(index);
        elements[index] = value;
    }

    // коли елемент видаляється, всі елементи зсуваються ліворуч.
    // використовується метод System.arrayCopy(),
    // який копіює старі значення на нові позиції.
    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }
}
