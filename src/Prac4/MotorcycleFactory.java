package Prac4;

public class MotorcycleFactory extends VehicleFactory {
    private String type;
    private int engineVolume;

    public MotorcycleFactory(String type, int engineVolume) {
        this.type = type;
        this.engineVolume = engineVolume;
    }

    @Override
    public IVehicle createVehicle() {
        return new Motorcycle(type, engineVolume);
    }
}

