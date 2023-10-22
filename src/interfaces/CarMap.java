package interfaces;

import interfaces.type.of.collections.Car;
import interfaces.type.of.collections.CarOwner;

import java.util.List;
import java.util.Set;

public interface CarMap<T,V> {
    void put(T key, V value);
    V get(T key);
    Set<T> keyset();
    List<V> values();
    boolean remove(T key);
    int size();
    void clear();

}
