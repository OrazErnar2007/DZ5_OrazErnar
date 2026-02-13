package Prac4;

public class Truck implements IVehicle {
    private int loadCapacity;
    private int axles;

    public Truck(int loadCapacity, int axles) {
        this.loadCapacity = loadCapacity;
        this.axles = axles;
    }

    @Override
    public void drive() {
        System.out.println("Грузовик едет. Грузоподъемность: " + loadCapacity + " кг, осей: " + axles);
    }

    @Override
    public void refuel() {
        System.out.println("Заправка грузовика дизелем.");
    }
}

