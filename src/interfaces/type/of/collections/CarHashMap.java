package interfaces.type.of.collections;

import interfaces.CarMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    private int size;

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= array.length * LOAD_FACTOR){
            increaseArray();
        }
        boolean puty = put(key, value, array);
        if (puty){
            size++;
        }
    }
    public boolean put(CarOwner key, Car value, Entry[] dst) {
        int position = getElementPosition(key, dst.length); // получаем номер позиции
        Entry existedElement = dst[position]; // получаем элемент по этой позиции
        if (existedElement == null){ // проверяем пустая ли позиция
            Entry entry = new Entry(key, value, null); // если пустая, то создаем новый объект Entry
            dst[position] = entry; // кладем новый объект в массив
            return true;
        }else { // в противном случае надо проверять все элементы по цепочке
            while (true){
                if (existedElement.key.equals(key)){ // надо проверить, что у существующего элемента ключ не равен ключу, передаваемого объекта, а если равен, то надо переписать значения
                    existedElement.value = value;
                    return false;
                }
                if (existedElement.next == null){ // проверяем следующий элемент(который в связном списке)
                    existedElement.next = new Entry(key, value, null);
                    return true;
                }
                existedElement = existedElement.next; // переписали ссылки, что бы дальше чекать связный список
            }
        }
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length); // получаем индекс
        Entry existedElement = array[position]; // присваиваем
        while(existedElement != null){ // ищем в цепочке элемент с нужным ключом
            if (existedElement.key.equals(key)){
                return existedElement.value;
            }
            existedElement = existedElement.next; // тут перезаписали что бы шагать вниз по цепочке
        }
        return null;
    }

    @Override
    public Set<CarOwner> keyset() {
        Set<CarOwner> result = new HashSet<>();
        for (Entry entry : array){ // 1 этап
            Entry existedElement = entry;
            while (existedElement != null){ // заходим в связный список
                result.add(existedElement.key); // пишем result
                existedElement = existedElement.next; // шлепаем дальше
            }
        }
        return result;
    }

    @Override
    public List<Car> values() {
        List<Car> result = new ArrayList<>();
        for (Entry entry : array){ // 1 этап
            Entry existedElement = entry;
            while (existedElement != null){ // заходим в связный список
                result.add(existedElement.value); // пишем result
                existedElement = existedElement.next; // шлепаем дальше
            }
        }
        return result;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length); // получаем индекс
        Entry existedElement = array[position]; // присваиваем
        if (existedElement != null && existedElement.key.equals(key)){ // проверка на пустоту и на совпадение ключей
            array[position] = existedElement.next; // перезаписали ссылки, что бы не потерять цепочку после удаления элемента
            size--;
            return true;
        }else {
            while (existedElement != null){ // идем дальше по цепочке, мб и из-за не совпадения ключей(не ток null)
                Entry nextElement = existedElement.next; // ссылка на следующий элемент
                if (nextElement == null){
                    return false;
                }
                if (nextElement.key.equals(key)){ // если не пустой, то чекаем ключи
                    existedElement.next = nextElement.next; // если ключи совпали, то удаляем следующий элемент(имеется ввиду nextElement(типо через 1 получается для тупиксов))
                    size--;
                    return true;
                }
                existedElement = existedElement.next; // база, чтобы шлепать дальше
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }
    private int getElementPosition(CarOwner carOwner, int arrayLength){
        return Math.abs(carOwner.hashCode() % arrayLength);
    }
    private void increaseArray(){
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array){ // 1 этап
            Entry existedElement = entry;
            while (existedElement != null){ // заходим в связный список
                put(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next; // шлепаем дальше
            }
        }
        array = newArray;
    }

    private static class Entry{
        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
