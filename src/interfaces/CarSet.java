package interfaces;

import interfaces.type.of.collections.Car;

public interface CarSet<T> extends CarCollection<T>{
    public boolean add(T car);
    public boolean remove(T car);
    public int size();
    public void clear();


}
