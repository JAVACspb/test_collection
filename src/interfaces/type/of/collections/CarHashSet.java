package interfaces.type.of.collections;

import interfaces.CarSet;

public class CarHashSet implements CarSet {
    private int size;
    private final static int INITIAL_CAPACITY = 16;
    private final static double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    @Override
    public boolean add(Car car) { // метод добавления в хэш-таблицу
        if (size >= array.length * LOAD_FACTOR){
            increaseArray();
        }
        boolean added = add(car, array);
        if (added){ // вернул true увеличили size на 1, убрал из метода add, что бы переиспользовать его и не напакастить при увелечении капасити
            size++;
        }
        return added;
    }
    private boolean add(Car car, Entry[] dst){
        int position = getElementPosition(car, dst.length); // вычисляем куда будем класть эелемент(Его индекс)
        if (dst[position] == null){
            Entry entry = new Entry(car,null); // Если на полученой позиции пусто кладем новый элемент, не оставляя ссылок на следующий
            dst[position] = entry;
            return true;
        }else{
            Entry existedElement = dst[position]; // если на полученной позиции уже есть объект мы получаем и проверяем его
            while (true) {
                if (existedElement.value.equals(car)) {
                    return false; // если значение передоваемого элемента совпаладает, с элементом который уже лежит, то возвращаем false
                } else if (existedElement.next == null) { // если передаваемый элемент не совпадает, то нам нужно положить его в переменную next
                    existedElement.next = new Entry(car, null); // если он пустой, то кладем наш новый элемент
                    return true;
                } else { // если и next занят, то надо проверить цепочку дальше, поэтому мы перезаписываем элемент
                    existedElement = existedElement.next;
                }
            }
        }
    }
    private void increaseArray(){
        Entry[] newArray = new Entry[array.length * 2]; // создаем новый массив
        for (Entry entry : array ) { // начинаем проходится по старому массиву
            Entry existedElement = entry; // получаем ссылку на нулевой элемент
            while (existedElement != null){ // если не пустой, то
                add(existedElement.value, newArray); //добавляем его в новый массив
                existedElement = existedElement.next; // строчка для того, что бы от нулевого элемента зайти в связный список
            }
        }
        array = newArray;
    }


    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length); // вычисляем номер позиции в массиве
        if (array[position] == null){return false;} // проверяем есть ли что-то на полученной позиции
        Entry secondLast = array[position];// запоминаем предпоследний элемент самая верхушка тот, который под индексом
        Entry last = secondLast.next; // запоминаем последний элемент который под элементом который под индексом
        if (secondLast.value.equals(car)){ // проверка и удаление
            array[position] = last;
            size--;
            return true;
        }
        while (last != null) { // идем вниз пор списку с проверкой на null
            if (last.value.equals(car)) {
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Car car) {
        int position = getElementPosition(car, array.length); // вычисляем номер позиции в массиве
        if (array[position] == null){return false;} // проверяем есть ли что-то на полученной позиции
        Entry secondLast = array[position];// запоминаем предпоследний элемент самая верхушка тот, который под индексом
        Entry last = secondLast.next; // запоминаем последний элемент который под элементом который под индексом
        if (secondLast.value.equals(car)){ // проверка и удаление
            return true;
        }
        while (last != null) { // идем вниз пор списку с проверкой на null
            if (last.value.equals(car)) {
                return true;
            } else {
                last = last.next;
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
    private int getElementPosition(Car car, int arrayLength){
        return Math.abs(car.hashCode() % arrayLength);
    }
    private static class Entry{
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }

}
