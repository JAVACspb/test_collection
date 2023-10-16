package interfaces;

import interfaces.type.of.collections.Car;

public interface CarCollection {
    boolean add(Car car);
    boolean remove(Car car);
    int size();
    void clear();

}
