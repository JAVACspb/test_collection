import interfaces.type.of.collections.Car;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Car> cars = new TreeSet<>(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
        for (int i = 0; i < 100; i++){
            cars.add(new Car("Brand" + i, i));
        }
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}