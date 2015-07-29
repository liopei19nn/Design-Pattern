// Observer Pattern
// defines a one-to-many dependency between 
// objects so that when one object changes state,
// all of its dependents are notified and 
// updated automatically.




// Design a weather station app, with data collect and display system

// 1. you have get method for temperature, humidity and pressure
// 2. measurement could be changed
// 3. must implement 3 display elements use the weather data
	// 3.1 Current condition, 3.2 statistics 3.3 forecast

// open API for developer


// initial design

public class WeatherData{

	public float getTemperature(){
		// get temperature from sensor
	}

	public float getHumidity(){
		// get humidity from sensor
	}
	public float getPressure(){
		// get pressure from sensor
	}



	public void measurementsChanged(){
		float temp = getTemperature();
		float humidity = getHumidity();
		float pressure = getPressure();

		currentConditionDisplay.update(temp,humidity,pressure);
		statisticsDisplay.update(temp,humidity,pressure);
		forecastDisplay.update(temp,humidity,pressure);
	}
}




// Observer Pattern 
// = publisher + subscriber
// publisher in business will keep publish
// subscriber will keep receiving new version, but stop when you unsubscribe

// publisher --> SUBJECT
// subscriver --> OBSERVER
// SUBJECT manage data bits, when data changes, OBSERVER will be noticed
// NOT OBSERVER object will not be noticed

// code for a SUBJECT OBSERVER model

public interface Subject{
	// have many observers
	registerObserver();
	removeObserver();
	notifyObservers();
}

public interface ConcreteSubject implements Subject{
	registerObserver(){
		// detail
	}
	removeObserver(){
		// detail
	}

	notifyObservers(){
		// detail
	}

	getState(){

	}

	setState(){

	}
}

public interface Observer{
	update();
}

public class ConcreteObserver implements Observer{
	update();
	// other methods
}

/* loosely couple
	When two objects are loosely coupled, they can interact, but have very little knowledge of each other.
The Observer Pattern provides an object design where subjects and observers are loosely coupled.
*/



// weather station app with OBSERVER PATTERN

public interface Subject{
	// have many observers
	public void registerObserver();
	public void removeObserver();
	public void notifyObservers();
}


public interface Observer{
	public void update(float temp, float humidity, float pressure);
}

public interface DisplayElement{
	public void display();
}



// main class, WeatherData play as SUBJECT
publci class WeatherData implements Subject{
	private ArrayList observers;
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData(){
		observers = new ArrayList();

	}

	public void registerObserver(Observer o){
		observers.add(o);
	}

	public void removeObserver(Observer o){
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(o);
		}
	}

	public void notifyObservers(){
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(temperature,humidity,pressure);
		}
	}


	public void measurementsChanged(){
		notifyObservers();
	}


	public void setMeasurement(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

}

// one display observer example

public class CurrentConditionDisplay implements Observer,DisplayElement{
	private float temperature;
	private float humidity;
	private Subject weatherData;// save the reference of SUBJECT for register and remove this OBSERVER

	public CurrentConditionDisplay(Subject weatherData){
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	public void update(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}

	public void display(){
		System.out.println("Current conditions: " + temperature + " F degrees and" + "humidity + %" + humidity);
	}

}
// TEST
public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay currentDisplay =
		new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData); ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		weatherData.setMeasurements(80, 65, 30.4f); weatherData.setMeasurements(82, 70, 29.2f); weatherData.setMeasurements(78, 90, 29.2f);
	} 
}


// Java built-in Observer Pattern 
// implements Observer interface and Observable class


// model for Weather Station app using built-in Observer and Observable

public class WeatherDate extends Observable{
	getTemperature();
	getHumidity();
	getPressure();
}

public class GeneralDisplay implements Observer,DisplayElement{
	update();
	display();
}


// For object act as OBSERVER, just implements Observer interface

// for SUBJECT, extends Observable 
// Sent notification with 2 steps
	// 1. call setChanged() method first 

		// setChanged() method is used to control when to send the notification
		// if not called, no notification will be sent


	// 2. call notifyObservers() or notifyObservers(Object args)
			// you could choose to push data by puting object, 
			// if null, OBSERVER will choose to pull the data from SUBJECT

// for OBSERVER receive notification 
// update(Observable o, Object args)



// Weather Station use Java built-in Observer and Observable


import java.util.Observable;
import java.util.Observer;

public class WeatherData extends Observable{
	private float temperature;
	private float humidity;
	private float pressure;


	public WeatherData(){

	}

	public void measurementsChanged(){
		setChanged(); // call it first to prepare for notice all
		notifyObservers(); // empty means OBSERVER should pull the update data from SUBJECT 
	}

	public void setMeasurement(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}



	// the getter method is used for
	// Observer to pull what they want 
	// when update
	public float getTemperature(){
		return this.temperature;
	}
	public float getHumidity(){
		return this.humidity;
	}
	public float getPressure(){
		return this.pressure;
	}

}

import java.util.Observable;
import java.util.Observer;


public class CurrentConditionsDisplay implements Observer{
	Observable observable;
	private float temperature;
	private float humidity;

	public CurrentConditionsDisplay(Observable observable){
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void update(Observable obs, Object args){
		if (obs instanceof WeatherData) {

			// this reference is used to 
			// access the getter method for WeatherData class
			// because Observable do not know these method
			WeatherData weatherData = (WeatherData)obs;

			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}


	public void display(){
		System.out.println("Current conditions: " + temperature + " F degrees and" + "humidity + %" + humidity);
	}



}


// rerun the WeatherStation and you will notice that
// the display does not follow the order of instance register 
// so

// !!!! DO NOT depend on order of evaluation of the Observer notification!
















































