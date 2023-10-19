import interfaces.type.of.collections.Car;
import interfaces.type.of.collections.CarOwner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<CarOwner, Car> map = new HashMap<>();
        CarOwner key = new CarOwner(1,"Name", "LastName");
        map.put(key, new Car("Brand1", 1));
        key.setId(8);
        Car car = map.get(key);
        System.out.println(car.getBrand());

    }
}