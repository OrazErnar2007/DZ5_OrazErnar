package Prac4;

public class ElectricScooterFactory extends VehicleFactory {
    private int batteryCapacity;

    public ElectricScooterFactory(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public IVehicle createVehicle() {
        return new ElectricScooter(batteryCapacity);
    }
}

