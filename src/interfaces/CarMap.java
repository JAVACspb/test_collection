package interfaces;

import interfaces.type.of.collections.Car;
import interfaces.type.of.collections.CarOwner;

import java.util.List;
import java.util.Set;

public interface CarMap {
    void put(CarOwner key, Car value);
    Car get(CarOwner key);
    Set<CarOwner> keyset();
    List<Car> values();
    boolean remove(CarOwner key);
    int size();
    void clear();

}
