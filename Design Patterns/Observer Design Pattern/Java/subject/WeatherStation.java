package subject;
import java.util.List;
import java.util.ArrayList;
import observer.ObserverInterface;

// WeatherStation class implementing the Subject interface
public class WeatherStation implements SubjectInterface {

    // List to hold registered observers
    private List<ObserverInterface> observers;
    private float temperature;
    private float humidity;

    // Constructor to initialize the WeatherStation
    public WeatherStation() {
        this.observers = new ArrayList<>();
        this.temperature = 0.0f;
        this.humidity = 0.0f;
    }

    // Methods to register, remove, and notify observers
    @Override
    public void registerObserver(ObserverInterface o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverInterface o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    // Method to set measurements and notify observers
    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers(); // Notify when data changes
    }
}
