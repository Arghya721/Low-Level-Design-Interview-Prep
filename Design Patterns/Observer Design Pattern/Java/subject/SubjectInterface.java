package subject;
import observer.ObserverInterface;

// Subject interface for the Observer Design Pattern
public interface SubjectInterface {
    void registerObserver(ObserverInterface o);
    void removeObserver(ObserverInterface o);
    void notifyObservers();
}