// Template Method Pattern
/*
	defines the skeleton of an algorithm in a method, deferring some
	steps to subclasses. Template Method lets subclasses redefine certain 
	steps of an algorithm without changing the algorithm’s structure.
*/



// EG : making tea and coffee share same ingredient : caffeine, and similar process


public class Coffee{
	void prepare(){
		boilWater();
		brewCoffeeGrinds();
		pourInCup();
		addSugarAndMilk();
	}

	public void boilWater(){

	}

	public void brewCoffeeGrinds(){

	}


	public void addSugarAndMilk(){

	}


	public void pourInCup(){

	}
}



public class Tea{

	void prepare(){
		boilWater();
		steepTeaBag();
		pourInCup();
		addSugarAndMilk();
	}

	public void boilWater(){

	}

	public void steepTeaBag(){

	}


	public void addLemon(){

	}

	public void pourInCup(){

	}

}


// Use Templates Method to define the steps of an algorithm and allows subclasses to provide the implementation
// for one or more steps


public abstract class CaffeineBeverage{
	// Template method 
	// 1. A method
	// 2. Serves as a template for an algorithm
	// each step is represented by a method here
	void final prepare(){
		boilWater();
		brew();
		pourInCup();
		addCondiments();

	}

	public void boilWater(){

	}
	public void pourInCup(){

	}

	public abstract void brew();
	public abstract void addCondiments();
}

// Sub concrete class could override some steps concrete implementation

public class Tea extends CaffeineBeverage{
	public void brew(){
		steepTeaBag();
	}

	public void addCondiments(){
		addLemon();

	
	}
}

public class Coffee extends CaffeineBeverage{
	public void brew(){
		brewCoffeeGrinds();
	}

	public void addCondiments(){
		addSugarAndMilk();
	}
}


// Template Method Pattern
/*
	defines the skeleton of an algorithm in a method, deferring some
	steps to subclasses. Template Method lets subclasses redefine certain 
	steps of an algorithm without changing the algorithm’s structure.
*/

// model

public abstract class AbstractClass{

	// To prevent subclass change the template method, define it final
	final templateMethod(){
		concreteMethod1();
		concreteMethod2();
		primitiveMethod1();
		primitiveMethod2();
		hook();

	}
	concreteMethod1(){

	}

	concreteMethod2(){

	}

	abstract primitiveMethod1(); 

	abstract primitiveMethod2(); 

	hook(){
		// do nothing by default
		// subclass are free to override it or not
	}
}

public class ConcreteClass extends AbstractClass{
	primitiveMethod1(){

	}

	primitiveMethod2(){

	}
}

// Use hook to do something optional 


// Example for using "hook" method

public abstract class CaffeineBeverageWithHook{
	void final prepare(){
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();
		}

	}

	public void boilWater(){

	}
	public void pourInCup(){

	}

	public abstract void brew();
	public abstract void addCondiments();

	boolean customerWantsCondiments(){
		// This is a hook because the subclass can override this method, but doesn’t have to.
		// Default return true, so if you do not override this method, addCondiments() method
		// will act automatically in your subclass, like there is no hook method
		return true;
	}
}



public class CoffeeWithHood extends CaffeineBeverageWithHook{
	public void brew(){
		brewCoffeeGrinds();
	}

	public void addCondiments(){
		addSugarAndMilk();
	}

	@override
	public boolean customerWantsCondiments(){
		String answer = getUserInput();
		if (answer.toLowerCase().startsWith("y")) {
			return true;
		}else{
			return false;
		}
	}

	private String getUserInput(){
		// get user input
	}

}


/*The Hollywood Principle*/

// Don’t call us, we’ll call you.

// The Hollywood principle gives us a way to prevent “dependency rot.” With the Hollywood Principle, we allow low-level components to hook themselves into a system, but the high-level components determine when they are needed, and how. In other words, the high-level components give the low-level components a “don’t call us, we’ll call you” treatment.


// Application for Template Method Pattern

// Do sort method as template, but compareTo() method is subclass defined


public class Duck implements Comparable {
	String name;
	int weight;

	public Duck(String name, int weight){
		this.name = name;
		this.weight = weight;
	}

	public String toString(){
		return name + "Weighs" + weight;
	}

	@Override
	public int compareTo(Object object){
		Duck otherDuck = (Duck)object;

		if (this.weight < otherDuck.weight) {
			return -1;
		} else if (this.weight == otherDuck.weight){
			return 0;
		} else {
			return -1;
		}


	}
}


// Template method for sort
// Not same as Template Method Pattern, but intent is same

// Step 2 and Step 4 use high level method, and Step 3 use low level method
// low level method is detailed implemented in subclass
/*
	public class Arrays{
		swap();
		sort();
	}

	1. make an array of ducks

	2. Call the Arrays.sort() method


	3. Sort Method use the compareTo() method in Duck class
		public class Duck{
			compareTo();
			toString();
		}
	
	4. Call Arrays.swap() method
*/









































