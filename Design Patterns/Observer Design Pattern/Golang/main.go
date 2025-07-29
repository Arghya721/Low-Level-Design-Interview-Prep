package main

import "fmt"

// ObserverInterface receives updates with new temperature and humidity values from the subject
type ObserverInterface interface {
	update(temperature float64, humidity float64)
}

// SubjectInterface manages observer subscriptions and triggers notifications when data changes
type SubjectInterface interface {
	registerObserver(observer ObserverInterface)
	removeObserver(observer ObserverInterface)
	notifyObservers()
}

// WeatherStation tracks weather data and notifies registered observers of any changes
type WeatherStation struct {
	observers   []ObserverInterface
	temperature float64
	humidity    float64
}

// NewWeatherStationConstructor initializes a weather station with empty observer list and default values
func NewWeatherStationConstructor() *WeatherStation {
	return &WeatherStation{
		observers:   make([]ObserverInterface, 0),
		temperature: 0.0,
		humidity:    0.0,
	}
}

// registerObserver adds a new observer to receive weather updates
func (ws *WeatherStation) registerObserver(observer ObserverInterface) {
	ws.observers = append(ws.observers, observer)
}

// removeObserver unsubscribes an observer from weather updates
func (ws *WeatherStation) removeObserver(observer ObserverInterface) {
	for i, obs := range ws.observers {
		if obs == observer {
			ws.observers = append(ws.observers[:i], ws.observers[i+1:]...)
			break
		}
	}
}

// notifyObservers sends current weather data to all registered observers
func (ws *WeatherStation) notifyObservers() {
	for _, observer := range ws.observers {
		observer.update(ws.temperature, ws.humidity)
	}
}

// setMeasurements updates weather data and notifies all observers of the changes
func (ws *WeatherStation) setMeasurements(temperature float64, humidity float64) {
	ws.temperature = temperature
	ws.humidity = humidity
	ws.notifyObservers()
}

// PhoneDisplay observes and displays weather updates with a unique identifier
type PhoneDisplay struct {
	name string
}

// NewPhoneDisplayConstructor creates a new phone display with the given name
func NewPhoneDisplayConstructor(name string) *PhoneDisplay {
	return &PhoneDisplay{name: name}
}

// update displays the received weather data along with the phone's identifier
func (pd *PhoneDisplay) update(temperature float64, humidity float64) {
	fmt.Println(pd.name, "received update: Temperature:", temperature, "Humidity:", humidity)
}

// main demonstrates the Observer pattern with a weather station updating multiple phone displays
func main() {
	station := NewWeatherStationConstructor()

	phone1 := NewPhoneDisplayConstructor("Phone 1")
	phone2 := NewPhoneDisplayConstructor("Phone 2")

	station.registerObserver(phone1)
	station.registerObserver(phone2)

	station.setMeasurements(30.5, 65.0)

	station.removeObserver(phone1)
	station.setMeasurements(28.0, 70.0)
}
