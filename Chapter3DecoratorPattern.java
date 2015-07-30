// Decorator Pattern 
// attaches additional responsibilities to an object dynamically. 
// Decorators provide a flexible alternative to subclassing for extending functionality.
// Meant to add behavior to the object they wrap

// Down Side : designs using this pattern often result in a large number of small
// classes that can be overwhelming to a developer trying to use the Decorator-based API.


// Open Closed Principle : 
// Classes Should be open to extension, but closed for modification
// Notice : Be careful choose where you choose the area of code that need to be extended; apply
// 			the Open-Closed Principle everywhere is wasteful, unnecessary and can lead to complex, hard
//			to understand code


// Star Buzz Beverage 
// calculate different coffee price with condiments

// initial design

public abstract class Beverage{
	private String description;

	getDescription();

	// calculate the cost of coffee
	// with customized condiments
	cost();
}


public class HouseBlend extends Beverage{
	cost();
}

public class DarkRoast extends Beverage{
	cost();	
}

public class Decaf extends Beverage{
	cost();
}

public class Espresso extends Beverage{
	cost();	
}


// Decorator Pattern
// attaches additional responsibilities to an object dynamically. 
// Decorators provide a flexible alternative to subclassing for extending functionality.


// Model of Decorator Pattern

public class Component{
	// each component can be used on its, or wrapped by a decorator

	methodA();
	methodB();
}


public class ConcreteComponent extends Component{
	// THIS IS WHERE we want to ADD new behaviors

	methodA();
	methodB();
}


public class Decorator{

	// each decorator wraps a component, which means the decorator 
	// has an instance variable that holds a reference to a component

	// Decorator implement the same interface or abstract class as the component they are going to
	// decorate
	methodA();
	methodB();
}


public class ConcreteDecorator{
	// Concrete Decorator has an instance variable for the thing it decorate

	// decorators can add new methods, however, new behavior is typlically added by
	// doing computation before or after an existing method in the component

	// Decorators can extend the state of the component

	Component wrappedObj;
	Object newState;

	methodA();
	methodB();
	newBehavior();

}


// Composition ? Extenstion?

// For decorator extends components, the extension did not mean to inherite the behavior, 
// but just for type matching


// Use decorator pattern for this project


public abstract class Beverage{
	String description = "Unknown Beverage";


	public String getDescription(){
		return this.description;
	}

	public abstract double cost();
}


public abstract class CondimentsDecorator extends Beverage{
	public abstract String getDescription();
}

// one concrete component
public class Espresso extends Beverage{
	public Espresso(){
		this.description = "Espresso";
	}

	public double cost(){
		return 1.99;
	}
}

public class HouseBlend{
	public HouseBlend(){
		this.description = "HouseBlend";
	}

	public double cost(){
		return 0.89;
	}
}

// concrete decorator

public class Mocha extends CondimentsDecorator{
	Beverage beverage;

	public Mocha(Beverage b){
		this.beverage = beverage;
	}

	public String getDescription(){
		return beverage.getDescription() + ", Mocha";
	}

	public double cose(){
		return beverage.cost() + 0.2:
			
		
	}

}



// real example for Decorator Pattern

// java.io


// Abstract Component : InputStream
// Concrete component : FileInputStream, ByteArrayInputStream, StringBufferInputStream


// Abstract Decorator : FilterInputStream
// Concrete Decorator : BufferedInputStream, LineNumberInputStream, DataInputStream




























































