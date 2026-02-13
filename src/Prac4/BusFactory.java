package Prac4;

public class BusFactory extends VehicleFactory {
    private int seats;
    private boolean cityBus;

    public BusFactory(int seats, boolean cityBus) {
        this.seats = seats;
        this.cityBus = cityBus;
    }

    @Override
    public IVehicle createVehicle() {
        return new Bus(seats, cityBus);
    }
}

