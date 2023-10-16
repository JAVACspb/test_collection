package interfaces;

import interfaces.type.of.collections.Car;

public interface CarSet extends CarCollection{
    public boolean add(Car car);
    public boolean remove(Car car);
    public int size();
    public void clear();


}
