package Prac4;

public class Bus implements IVehicle {
    private int seats;
    private boolean cityBus;

    public Bus(int seats, boolean cityBus) {
        this.seats = seats;
        this.cityBus = cityBus;
    }

    @Override
    public void drive() {
        System.out.println("Автобус едет. Мест: " + seats + ", городской: " + cityBus);
    }

    @Override
    public void refuel() {
        System.out.println("Заправка автобуса.");
    }
}

