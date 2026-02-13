package Prac4;

public class TruckFactory extends VehicleFactory {
    private int loadCapacity;
    private int axles;

    public TruckFactory(int loadCapacity, int axles) {
        this.loadCapacity = loadCapacity;
        this.axles = axles;
    }

    @Override
    public IVehicle createVehicle() {
        return new Truck(loadCapacity, axles);
    }
}

