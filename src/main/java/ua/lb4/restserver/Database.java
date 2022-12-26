package ua.lb4.restserver;

import lombok.Getter;
import ua.lb4.restserver.bo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
public class Database {

    List<VehicleType> vehicles = new ArrayList<>();
    List<CourierType> couriers = new ArrayList<>();;
    List<ClientType> clients = new ArrayList<>();
    List<ItemType> itemTypes = new ArrayList<>();
    List<DeliveryType> deliveries = new ArrayList<>();

    public Database() {
     generateVehicles();
     generateCouriers();
    }

    private void generateVehicles() {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setVehicleId(UUID.randomUUID().toString());
        vehicleType.setVehicleName("Truck");

        VehicleType vehicleType2 = new VehicleType();
        vehicleType.setVehicleId(UUID.randomUUID().toString());
        vehicleType.setVehicleName("Car");

        VehicleType vehicleType3 = new VehicleType();
        vehicleType.setVehicleId(UUID.randomUUID().toString());
        vehicleType.setVehicleName("Bike");

        vehicles.addAll(Arrays.asList(vehicleType, vehicleType2, vehicleType3));
    }

    private void generateCouriers() {
        CourierType courierType = new CourierType();
        courierType.setCourierId(UUID.randomUUID().toString());
        courierType.setAge((byte)25);
        courierType.setFirstName("Іван");
        courierType.setLastName("Чорнозем");
        courierType.setVehicle(vehicles.get(2));
        courierType.setPhoneNumber("000000000");

        CourierType courierType2 = new CourierType();
        courierType.setCourierId(UUID.randomUUID().toString());
        courierType.setAge((byte)25);
        courierType.setFirstName("Біллі");
        courierType.setLastName("Херінгтон");
        courierType.setVehicle(vehicles.get(1));
        courierType.setPhoneNumber("000000000");

        CourierType courierType3 = new CourierType();
        courierType.setCourierId(UUID.randomUUID().toString());
        courierType.setAge((byte)25);
        courierType.setFirstName("Стів");
        courierType.setLastName("Рембо");
        courierType.setVehicle(vehicles.get(0));
        courierType.setPhoneNumber("000000000");

        couriers.addAll(Arrays.asList(courierType, courierType2, courierType3));
    }


}
