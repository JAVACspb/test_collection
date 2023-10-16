package interfaces;

import interfaces.type.of.collections.Car;

public interface CarList extends CarCollection{
    Car get(int index);
    boolean add(Car car);

    boolean add(Car car, int index);

    boolean remove(Car car);

    boolean removeAt(int index);

    int size();

    void clear();

    @Override
    default boolean contains(Car car) {
        return false;
    }
}