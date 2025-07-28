import factory.LuxuryVehicle;
import factory.OrdinaryVehicle;
import factory.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        // Create instances of LuxuryVehicle and OrdinaryVehicle factories
        VehicleFactory luxuryFactory = new LuxuryVehicle();
        // Get vehicles from the luxury factory and call their averageMileage method
        luxuryFactory.getVehicle("BMW").averageMileage();
        // Get vehicles from the luxury factory and call their averageMileage method
        luxuryFactory.getVehicle("Mercedes").averageMileage();

        // Create instances of OrdinaryVehicle factory
        VehicleFactory ordinaryFactory = new OrdinaryVehicle();
        // Get vehicles from the ordinary factory and call their averageMileage method
        ordinaryFactory.getVehicle("Suzuki").averageMileage();
        // Get vehicles from the ordinary factory and call their averageMileage method
        ordinaryFactory.getVehicle("Hyundai").averageMileage();
    }
}