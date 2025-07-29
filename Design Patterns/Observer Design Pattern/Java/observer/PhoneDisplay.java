package observer;

// Phone display implementation of the Observer interface
public class PhoneDisplay implements ObserverInterface {

    private String name;

    // Constructor to initialize the phone display with a name
    public PhoneDisplay(String name) {
        this.name = name;
    }

    // Update method to receive temperature and humidity updates
    @Override
    public void update(float temperature, float humidity) {
        System.out.println(name + " received update: Temperature = " + temperature + "°C, Humidity = " + humidity + "%");
    }
}
