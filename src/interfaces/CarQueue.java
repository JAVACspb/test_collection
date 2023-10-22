package interfaces;

import interfaces.type.of.collections.Car;

public interface CarQueue<T> {
    boolean add(T car);
    T peek();
    T poll();
}
