import java.util.Scanner;

interface IVehicle{
    void Drive();
    void Refuel();
}

class Car implements IVehicle{
    private String brand;
    private String model;
    private String fuelType;

    public Car(String brand, String model, String fuelType){
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }
    @Override
    public void Drive() {
        System.out.println("Driving a car: " + brand + " " + model);
    }
    @Override
    public void Refuel() {
        System.out.println("Refueling car with " + fuelType);
    }
}


class Motorcycle implements IVehicle{
    private String type;
    private int obyemdvigitel;

    public Motorcycle(String type,int obyemdvigitel){
        this.type = type;
        this.obyemdvigitel = obyemdvigitel;
    }

    @Override
    public void Drive() {
        System.out.println("Riding a " + type + " motorcycle with engine capacity: " + obyemdvigitel + "cc");
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling motorcycle.");
    }
}

class Truck implements IVehicle{
    private int loadCapacity;
    private int colOsey;

    public Truck(int loadCapacity, int colOsey){
        this.loadCapacity = loadCapacity;
        this.colOsey = colOsey;
    }

    @Override
    public void Drive() {
        System.out.println("Driving a truck with load capacity: " + loadCapacity + "kg and " + colOsey + " axles.");
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling truck.");    }
}

class Bus implements IVehicle{
    private int seatingCapacity;

    public Bus(int seatingCapacity){
        this.seatingCapacity = seatingCapacity;
    }
    @Override
    public void Drive() {
        System.out.println("Driving a bus with seating capacity: " + seatingCapacity);
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling bus.");
    }
}

abstract class VehicleFactory {
    public abstract IVehicle createVehicle();
}
class CarFactory extends VehicleFactory{
    private String brand;
    private String model;
    private String fuelType;

    public CarFactory(String brand, String model, String fuelType){
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }
    public IVehicle createVehicle() {
        return new Car(brand, model, fuelType);
    }
}

class MotorcycleFactory extends VehicleFactory{
    private String type;
    private int obyemdvigitel;

    public MotorcycleFactory(String type,int obyemdvigitel){
        this.type = type;
        this.obyemdvigitel = obyemdvigitel;
    }

    @Override
    public IVehicle createVehicle() {
        return new Motorcycle(type,obyemdvigitel);
    }
}

class TruckFactory extends VehicleFactory{
    private int loadCapacity;
    private int colOsey;

    public TruckFactory(int loadCapacity, int colOsey){
        this.loadCapacity = loadCapacity;
        this.colOsey = colOsey;
    }

    @Override
    public IVehicle createVehicle() {
        return new Truck(loadCapacity,colOsey);
    }
}

class BusFactory extends VehicleFactory{
    private int seatingCapacity;

    public BusFactory(int seatingCapacity){
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public IVehicle createVehicle() {
        return new Bus(seatingCapacity);
    }
}

enum AvtoType{
    Car, Motorcycle,Truck,Bus
}

class Program{
    public static IVehicle getVehicle(AvtoType type){
        VehicleFactory factory = null;
        switch (type) {
            case Car:
                factory = new CarFactory("Toyota", "Camry", "Petrol");
                break;
            case Motorcycle:
                factory = new MotorcycleFactory("Sport", 600);
                break;
            case Truck:
                factory = new TruckFactory(5000, 4);
                break;
            case Bus:
                factory = new BusFactory(50);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type");
        }
        return factory.createVehicle();
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип транспорта:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Мотоцикл");
        System.out.println("3. Грузовик");
        System.out.println("4. Автобус");

        int choice = scanner.nextInt();
        IVehicle vehicle = null;

        switch (choice) {
            case 1:
                System.out.print("Введите марку автомобиля: ");
                String brand = scanner.next();
                System.out.print("Введите модель автомобиля: ");
                String model = scanner.next();
                System.out.print("Введите тип топлива: ");
                String fuelType = scanner.next();

                vehicle = new CarFactory(brand, model, fuelType).createVehicle();
                break;

            case 2:
                System.out.print("Введите тип мотоцикла: ");
                String motorcycleType = scanner.next();
                System.out.print("Введите объем двигателя (cc): ");
                int engineCapacity = scanner.nextInt();

                vehicle = new MotorcycleFactory(motorcycleType, engineCapacity).createVehicle();
                break;

            case 3:
                System.out.print("Введите грузоподъемность (кг): ");
                int loadCapacity = scanner.nextInt();
                System.out.print("Введите количество осей: ");
                int axles = scanner.nextInt();

                vehicle = new TruckFactory(loadCapacity, axles).createVehicle();
                break;

            case 4:
                System.out.print("Введите количество мест: ");
                int seatingCapacity = scanner.nextInt();

                vehicle = new BusFactory(seatingCapacity).createVehicle();
                break;

            default:
                System.out.println("Неверный выбор.");
                return;
        }

        vehicle.Drive();
        vehicle.Refuel();

        scanner.close();
    }
}
