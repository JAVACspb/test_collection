package interfaces.type.of.collections;

import interfaces.CarMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap<T,K> implements CarMap<T,K> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] array = new Object[INITIAL_CAPACITY];
    private int size;

    @Override
    public void put(T key, K value) {
        if (size >= array.length * LOAD_FACTOR){
            increaseArray();
        }
        boolean puty = put(key, value, array);
        if (puty){
            size++;
        }
    }
    public boolean put(T key, K value, Object[] dst) {
        int position = getElementPosition(key, dst.length); // получаем номер позиции
        Entry existedElement = (Entry) dst[position]; // получаем элемент по этой позиции
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
    public K get(T key) {
        int position = getElementPosition(key, array.length); // получаем индекс
        Entry existedElement = (Entry) array[position]; // присваиваем
        while(existedElement != null){ // ищем в цепочке элемент с нужным ключом
            if (existedElement.key.equals(key)){
                return (K) existedElement.value;
            }
            existedElement = existedElement.next; // тут перезаписали что бы шагать вниз по цепочке
        }
        return null;
    }

    @Override
    public Set<T> keyset() {
        Set<T> result = new HashSet<>();
        for (Object entry : array){ // 1 этап
            Entry existedElement = (Entry) entry;
            while (existedElement != null){ // заходим в связный список
                result.add((T) existedElement.key); // пишем result
                existedElement = existedElement.next; // шлепаем дальше
            }
        }
        return result;
    }

    @Override
    public List<K> values() {
        List<K> result = new ArrayList<>();
        for (Object entry : array){ // 1 этап
            Entry existedElement = (Entry) entry;
            while (existedElement != null){ // заходим в связный список
                result.add((K) existedElement.value); // пишем result
                existedElement = existedElement.next; // шлепаем дальше
            }
        }
        return result;
    }

    @Override
    public boolean remove(T key) {
        int position = getElementPosition(key, array.length); // получаем индекс
        Entry existedElement = (Entry) array[position]; // присваиваем
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
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }
    private int getElementPosition(T carOwner, int arrayLength){
        return Math.abs(carOwner.hashCode() % arrayLength);
    }
    private void increaseArray(){
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array){ // 1 этап
            Entry existedElement = (Entry) entry;
            while (existedElement != null){ // заходим в связный список
                put((T) existedElement.key, (K) existedElement.value, newArray);
                existedElement = existedElement.next; // шлепаем дальше
            }
        }
        array = newArray;
    }

    private class Entry{
        private T key;
        private K value;
        private Entry next;

        public Entry(T key, K value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }
}
