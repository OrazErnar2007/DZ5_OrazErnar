package Prac4;

public class ElectricScooter implements IVehicle {
    private int batteryCapacity;

    public ElectricScooter(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void drive() {
        System.out.println("Электросамокат едет. Батарея: " + batteryCapacity + "%");
    }

    @Override
    public void refuel() {
        System.out.println("Электросамокат заряжается от розетки.");
    }
}

