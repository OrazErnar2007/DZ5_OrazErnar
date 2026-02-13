package Prac4;

public class Motorcycle implements IVehicle {
    private String type;
    private int engineVolume;

    public Motorcycle(String type, int engineVolume) {
        this.type = type;
        this.engineVolume = engineVolume;
    }

    @Override
    public void drive() {
        System.out.println("Мотоцикл (" + type + ") едет. Объем двигателя: " + engineVolume + " см³");
    }

    @Override
    public void refuel() {
        System.out.println("Заправка мотоцикла бензином.");
    }
}

