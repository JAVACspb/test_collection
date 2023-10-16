package interfaces.type.of.collections;

import interfaces.CarList;

import java.util.Arrays;

public class CarArrayList implements CarList {
    private Car[] array = new Car[10];
    private  int size;
    @Override
    public Car get(int index) {
        checkindex(index);
        return array[index];
    }

    @Override
    public boolean add(Car car) {
        increseArray();
        array[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        increseArray();
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = car;
        size++;
        return true;
    }


    @Override
    public boolean remove(Car car) {
        for(int i = 0; i < size; i ++){
            if (array[i].equals(car)){
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean contains(Car car) {
        for(int i = 0; i < size; i ++){
            if (array[i].equals(car)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkindex(index);
        for(int i = index; i <  size - 1; i ++){
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Car[10];
        size = 10;
    }
    private void checkindex(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }
    private void increseArray(){
        if (size >= array.length){
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
}
