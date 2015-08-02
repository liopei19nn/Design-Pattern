// Adaptive Pattern and Facade Pattern
//￼The Adapter Pattern 
// converts the interface of a class into another interface the clients expect. 
// Adapter lets classes work together that couldn’t otherwise because of incompatible interfaces.

// The Facade Pattern
// provides a unified interface to a set of interfaces in a subsytem. 
// Facade defines a higher-level interface that makes the subsystem easier to use.



// Example for Duck 

public interface Duck{
	public void quack();
	public void fly();
}

// one kind of duck
public class MallardDuck implements Duck{
	public void quack(){
		// quack
	}

	public void fly(){
		// fly
	}
}


public interface Turkey{
	public void  gobble();
	public void fly();
}

public class WildTurkey implements Turkey{
	public void gobble(){
		// gobble
	}

	public void fly(){
		// fly
	}
}



public class TurkeyAdapter implements Duck{
	Turkey turkey;

	public TurkeyAdapter(Turkey turkey){
		this.turkey = turkey;
	}

	public void quack(){
		turkey.gobble();
	}

	public void fly(){
		turkey.fly();
	}
}



// Model : Client -- (request()) -->  Adapter -- (translatedRequest()) --> Adaptee 
/*

	1. The client makes a request to the adapter by calling a method on it using the target interface
	2. The adapter translates the request into one or more calls on the adaptee using the adaptee interface
	3. The client receives the results of the call and never knows there is an adapter doing the translation.

*/

// Adapter Pattern used for one interface adapted to one the other interface, not two or more interfaces 
	// to the other interface


// Object adapter

public class Client{
	Target adapter;
	public class Client(Target adapter){
		this.adapter = adapter;
	}
}

public interface Target{
	abstract request();
}

public class Adapter implements Target{
	Adaptee adaptee;

	public Adapter(Adaptee adaptee){
		this.adaptee = adaptee;
	}

	request(){
		adaptee.specificRequest();
	}
}

public class Adaptee{
	specificRequest(){

	}
}


// Class Adapter : need multiple inheritance, which is not applicable in Java

public class Client{
	Target adapter;
	public class Client(Target adapter){
		this.adapter = adapter;
	}
}

public class Target{
	request(){

	}
}

public class Adaptee{
	specificRequest(){

	}
}


public class Adapter extends Target,Adaptee{
	
	request(){
		adaptee.specificRequest();
	}
}



// real world example

// Old iterator interface : Enumeration Interface 
public interface Enumeration{
	hasMoreElements();
	nextElement();
}


// New Iterator Interface, 

public interface Iterator{
	hasNext();
	next();
	remove();
}


// adapter to map from Iterator to Enumeration

public class EnumerationIterator implements Iterator{
	Enumeration enum;

	public EnumerationIterator(Enumeration enum){
		this.enum = enum;
	}

	public boolean hasNext(){
		return enum.hasMoreElements();
	}

	public Object next(){
		return enum.nextElement();
	}


	// due to the fact that Enumeration interface do not have
	// remove method, we could throw an exception
	public void remove(){
		throw new UnsupportedOperationException();
	}
}




/* Facade Pattern
	 
	 provides a unified interface to a set of interfaces in a subsytem. 
	 Facade defines a higher-level interface that makes the subsystem easier to use.


*/

// Build a interface to provide simplified access to functionality, but not encapsulate them
// So there is still access to low level system


// Home Theater Example : 

public class HomeTheaterFacade{
	Amlifier amp;
	Tuner tuner;
	DvdPlayer dvd;

	public HomeTheaterFacade(Amlifier amp, Tuner tuner, DvdPlayer dvd){
		this.amp = amp;
		this.tuner = tuner;
		this.dvd = dvd;
	}


	// Combine all necessary method to watch movie into
	// one method in Facade Interface
	public void watchMovie(){
		amp.on();
		tuner.on;
		dvd.on();
		dvd.play();
	}
}
// After using Facade Pattern, the way to use method watchMovie is much more simplified
public class TestRun{
	public static void main(String[] args) {
		HomeTheaterFacade hometheater = new HomeTheaterFacade(amp,tuner,dvd);
		hometheater.watchMovie();
	}
}


// model

public class Client{
	Facade facade;

	facade.action();
}

public class Facade{
	Application app1;
	Application app2;
	action(){
		app1.action();
		app2.action();
	}
}


// Principle of Least Knowledge 
// talk only to your immediate friends.
/*
	It means when you are designing a system, 
	for any object, be careful of the number of classes
	it interacts with and also how it comes to interact with those classes.
	
	This principle prevents us from creating designs that have
	a large number of classes coupled together so that changes in one
	part of the system cascade to other parts. When you build a lot of
	dependencies between many classes, you are building a fragile system 
	that will be costly to maintain and complex for others to understand.

*/



// Principle : Invoke Method belong to 
	// 1. The object itself
	// 2. Objects passed in as a parameter to the method
	// 3. Any object the method creates or instantiated
	// 4. Any components of the object


	// Do not call the methods on objects that were returned from calling other method


// eg

public class Car{
	Engine engine;

	public Car(){
		engine = new Engine();
	}

	public void start(Key key){
		Doors doors = new Doors();

		// 2. Call method of passed in object : key
		boolean authorized = key.turns();


		if (authorized) {

			// call method of a component of class : engine
			engine.start();

			// call method of the object self : Car
			updateDashboardDisplay();

			// call method on an object you create : doors
			doors.lock();
		}
	}

	public void updateDashboardDisplay(){

	}

}


// Disadvantage of Principle of Least Knowledge
// applying this principle could result in more “wrapper” classes being 
// written to handle method calls to other components. This can result 
// in increased complexity and development time as well as decreased runtime performance.



































