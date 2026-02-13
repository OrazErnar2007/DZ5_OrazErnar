package Prac4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleFactory factory = null;

        System.out.println("Выберите транспорт:");
        System.out.println("1 - Car");
        System.out.println("2 - Motorcycle");
        System.out.println("3 - Truck");
        System.out.println("4 - Bus");
        System.out.println("5 - Electric Scooter");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Марка: ");
                String brand = scanner.nextLine();
                System.out.print("Модель: ");
                String model = scanner.nextLine();
                System.out.print("Тип топлива: ");
                String fuel = scanner.nextLine();
                factory = new CarFactory(brand, model, fuel);
            }
            case 2 -> {
                System.out.print("Тип мотоцикла: ");
                String type = scanner.nextLine();
                System.out.print("Объем двигателя: ");
                int volume = scanner.nextInt();
                factory = new MotorcycleFactory(type, volume);
            }
            case 3 -> {
                System.out.print("Грузоподъемность (кг): ");
                int capacity = scanner.nextInt();
                System.out.print("Количество осей: ");
                int axles = scanner.nextInt();
                factory = new TruckFactory(capacity, axles);
            }
            case 4 -> {
                System.out.print("Количество мест: ");
                int seats = scanner.nextInt();
                System.out.print("Городской? (true/false): ");
                boolean city = scanner.nextBoolean();
                factory = new BusFactory(seats, city);
            }
            case 5 -> {
                System.out.print("Заряд батареи (%): ");
                int battery = scanner.nextInt();
                factory = new ElectricScooterFactory(battery);
            }
            default -> System.out.println("Неверный выбор!");
        }

        if (factory != null) {
            IVehicle vehicle = factory.createVehicle();
            vehicle.drive();
            vehicle.refuel();
        }
    }
}

