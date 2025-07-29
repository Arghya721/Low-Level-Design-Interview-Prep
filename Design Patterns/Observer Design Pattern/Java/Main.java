import observer.ObserverInterface;
import observer.PhoneDisplay;
import subject.WeatherStation;

public class Main {
    public static void main(String[] args) {
        // Create a WeatherStation subject
        WeatherStation station = new WeatherStation();

        // Create phone displays (observers) and register them with the station
        ObserverInterface phone1 = new PhoneDisplay("Phone 1");
        ObserverInterface phone2 = new PhoneDisplay("Phone 2");

        // Register observers
        station.registerObserver(phone1);
        station.registerObserver(phone2);

        // Set measurements and notify observers
        station.setMeasurements(30.5f, 65f); // Both phones get notified

        // Remove one observer and set new measurements
        station.removeObserver(phone1);
        station.setMeasurements(32.0f, 70f); // Only Phone 2 gets notified
    }
}
