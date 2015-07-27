// Strategy Pattern
// defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.


// 1. Sim Duck Problem
// Extends Duck behavior, quack(), fly(), some of the 
// ducks do not have fly() or quack() method, 

// 1.1 too complicated to implemented fly or quack interface because
// there is too much subclass to 
// 1.2 too complicated to add quack() and fly() method for new added
// subclass, because you should do it for every new added class and recode
// their same behavior

// solution thinking : Identify the aspects of your application that vary and separate them from what stays the same.
//  take the parts that vary and encapsulate them, so that later you can alter or extend the parts that vary without affecting those that don’t.


			// make it more flexible : programming to a supertype so that the actual runtime object isn’t locked into the code.
			public interface Animal{
				public void makeSound();
			}
			public class Dog implements Animal{
				public void makeSound(){
					bark();
				}
				public void bark(){

				}
			}


			public class Cat implements Animal{
				public void makeSound(){
					meow();
				}
				public void meow(){

				}
			}



			// eg1. Forced to a code to a concrete implementation
			Dog d = new Dog();
			d.bark();

			// eg2. Programming to an interface or supertype
			Animal animal = new Dog();
			animal.makeSound(); // use the animal reference polymorphically


			// eg3. Assign the concrete implementation object at runtime
			Animal a;
			// at run time
			a = getAnimal();
			a.makeSound(); // we dont event know the actual 
			// subtype is, and we only care about how to respond to makeSound()


// for Duck sim, put the changing behavior into a interface, and implemented them in seperate
// class

// e.g.
// flying behavior interface

public interface FlyBehavior{
	public void fly();
}

// flying behavior 1 implementation class

public class FlyWithWings implements flyBehavior{
	@Override
	public void fly(){
		flyMethod();
	}
	public void flyMethod(){
		// implementation;
	}
}

// flying behavior 2 implementation class

public class NotFly implements flyBehavior{
	@Override
	public void fly(){
		flyMethod();
	}
	public void flyMethod(){
		// implementation;
	}
}

// add fly not as interface, but as instance field in Duck

public abstract class Duck{
	FlyBehavior flyBehavior; // add method as instance field, not method

	public void fly(){
		flyBehavior.fly();
	}

}


// for specific kind of duck

public class YellowDuck extends Duck{
	// in constuctor, instantiate the 
	// fly behavior with specific implementation
	public YellowDuck(){

		// use interface as reference, 
		// so you do not have to know 
		// the detail on fly detail, but only
		// on the response for fly method call
		this.flyBehavior = new FlyWithWings();
	}
}

public class RubberDuck extends Duck{
	public RubberDuck(){
		this.flyBehavior = new NotFly();
	}
}


// add more method to fly at runtime?

public class FlyWithRocket implements FlyBehavior{
	@Override
	public void fly(){
		flyMethod();
	}

	public void flyMethod(){
		// detailed implementation
	}
}


public class RocketDuck extends Duck{
	public RocketDuck(){
		this.flyBehavior = new FlyWithRocket();
	}
}



// You could Change the behavior of Duck using setter at runtime!

public abstract class Duck{
	FlyBehavior flyBehavior; // add method as instance field, not method

	public void fly(){
		flyBehavior.fly();
	}

	public void flyMethodSetter(FlyBehavior f){
		this.flyBehavior = f;
	}
}

// For a specific Duck model in runtime
public static void main(String[] args){
	YellowDuck yd = new YellowDuck();
	yd.fly(); // fly as a normal Yellow Duck

	yd.flyMethodSetter(new NotFly());
	ye.fly(); // fly behavior of this duck has been changed


}



// Duck "has-a" FlyBehavior, it is a "composition". Instead of "inheriting" their behavior
// the ducks gets their behavior by being composed with the right behavior object.


// Design Principle
// Favor composition over inheritance
	// composition lets you
	// 1. encapsulate a set of algorithm into their own class sets
	// 2. you could change behavior at runtime



// Strategy Pattern
// defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.


















