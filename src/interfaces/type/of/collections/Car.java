package interfaces.type.of.collections;

public class Car{
    private String brand;
    private int number;

    public Car(String brand, int number) {
        this.brand = brand;
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

//    @Override
//    public int compareTo(Car o) {
//        return brand.compareTo(o.brand);
//    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", number=" + number +
                '}';
    }
}
