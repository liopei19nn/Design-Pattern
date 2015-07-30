// 1. Factory Method Pattern
// defines an interface for creating an object, but lets subclasses decide which 
// class to instantiate. Factory Method lets a class defer instantiation to subclasses.
// intent Defer instantiation to its subclasses


// 2. Abstract Factory Pattern
//	provides an interface for creating families of related 
//	or dependent objects without specifying their concrete classes.
//  intent to create families of related objects without having to depend on their concrete classes

// Factory method create objects through inheritance
// Abstract Factory create objects through composition



// Seperate or Encapsulate what varies in object

// Pizza Order class with multiple type of pizzas
// initial idea 

Pizza orderPizza(String type){
	Pizza pizza;


	// vary part start
	// To modify this method, you have to 
	// change code here

	/*
		Pull this code out, put it into 
		a Factory Object
	*/

	if (type.equals("Cheese")) {
		pizza = new CheesePizza();
	} else if(type.equals("pepperoni")){
		pizza = new PepperoniPizza();
	} else if(type.equals("clam")){
		pizza = new ClamPizza();
	} else if(type.equals("veggie")){
		pizza = new VeggiePizza();
	}

	//vary part end
	pizza.prepare();
	pizza.bake();
	pizza.cut();
	pizza.box();
	return pizza;

}

// static factory
// Define a simple factory as a static method 

// but, the problem is you cannot subclass and override
// the method, the static method will be just hidden , not
// overriden in the subclass



// Change with Factory Object
// you could instantiate object by not calling "new"
// but calling the method you could get one


// with static method, you do not even have to instantiate the 
// factory, you could use the static method
public class SimplePizzaFactory{
	public Pizza createPizza(String type){
		Pizza pizza = null;

		if (type.equals("Cheese")) {
			pizza = new CheesePizza();
		} else if(type.equals("pepperoni")){
			pizza = new PepperoniPizza();
		} else if(type.equals("clam")){
			pizza = new ClamPizza();
		} else if(type.equals("veggie")){
			pizza = new VeggiePizza();
		}

		return pizza;
	}

}


// usage of the Factory Object

public class PizzaStore{
	SimplePizzaFactory factory;

	public PizzaStore(SimplePizzaFactory factory){
		this.factory = factory;
	}

	public Pizza orderPizza(String type){
		Pizza pizza;

		pizza = factory.createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;

	}
}

// Simple factory is not a pattern, but only a "idiom"



/*
	Build Franchise and Allow local franchise to decide
	how to create pizza
*/


public abstract class PizzaStore{
	public Pizza orderPizza(String type){
		Pizza pizza;

		pizza = createPizza(type);

		pizza.prepare();
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;

	}

	// createPizza method is back to PizzaStore


	// isolate the client code from knowing the 
	// concrete product from factory
	abstract Pizza createPizza(String type);
}


// one concrete object represent one store

public class NYPizzaStore extends PizzaStore{


	Pizza createPizza(String item){
		Pizza pizza;


		if (type.equals("Cheese")) {
			pizza = new NYStyleCheesePizza();
		} else if(type.equals("pepperoni")){
			pizza = new NYStylePepperoniPizza();
		} else if(type.equals("clam")){
			pizza = new NYStyleClamPizza();
		} else if(type.equals("veggie")){
			pizza = new NYStyleVeggiePizza();
		}else{
			return null;
		}

		return pizza;
	}
}

/*
 Factory Method Pattern
 defines an interface for creating an object, but lets subclasses decide which 
 class to instantiate. Factory Method lets a class defer instantiation to subclasses.

*/

// model of Factory Pattern
// change of product will not affect 
// creator, because only Concrete Creator
// touches the concrete product


public class Creator{
	// creator class should have all method 
	// implemented to manipulate data, except
	// for factory method
	abstract factoryMethod();
	operation();
}

public class ConcreteCreator{
	ConcreteProduct factoryMethod(){
		// only class and method know 
		// how to create these products
	}
}

public abstract class Product{
	
}

public class ConcreteProduct extends Product{

}

// Dependency Inversion Principle : Depend upon abstractions. DO NOT Depend upon concrete classes.

// It suggests that our high-level components should not depend on our low-level components; rather, 
// they should both depend on abstractions.

// Think not invertly : 
// Design a pizza store, store have to create pizza, prepare pizza, bake and box pizza, 
//              you may design the store access pizza directly

// Think invertly :
// You have all kinds of pizza, they all subclass of abstract Pizza class. Now your store could access
// the abstract pizza, not low level concrete pizza

// Guidelines for optimized OO design, used as reference in background
// 			for most changed part of program

// 1. No Variable should hold a reference to a concrete class
			// -- less use "new"
// 2. No class should derive from a concrete class
			// -- derive from an abstract class, like interface or abstract class, not a concrete one


// 3. No method should override an implemented method of any of its base classes 
			// if you override an implemented method, your base class is not actually abstract to start.
			// because class subclass your base class will be affected if the implemented method is changed.




/*
	Adding Ingredients for Pizza?

*/

public interface PizzaIngredientFactory{
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
}


public class NYPizzaIngredientStore implements PizzaIngredientFactory{
	public Dough createDough(){
		return new ThinCrustDough();
	}
	public Sauce createSauce(){
		return new MarinaraSauce();
	}
	public Cheese createCheese(){
		return new ReggianoCheese();
	}
	public Veggies[] createVeggies(){
		Veggies[] veggies = {new Garlic(), new Onion()};
		return veggies;
	}
	public Pepperoni createPepperoni(){
		return new SlicedPepperoni();
	}
	public Clams createClam(){
		return new FreshClams();
	}
}

// abstract product for pizza store

// abstract creator for pizza ingredient store
public abstract class Pizza{
	String name;
	Dough dough;
	Sauce sauce;
	Veggies[] veggies;
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;


	// abstract for ingredients access
	abstract void prepare();

	void bake(){

	}

	void box(){

	}

	void setName(String name){
		this.name = name;
	}

	void getName(){
		return this.name;
	}
}


// concrete pizza

public class CheesePizza extends Pizza{
	PizzaIngredientFactory ingredientsFactory;

	public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory){
		this.ingredientsFactory = pizzaIngredientFactory;
	}

	void prepare(){
		dough = ingredientsFactory.createDough();
		sauce = ingredientsFactory.createSauce();
		cheese = ingredientsFactory.createCheese();
	}
}

// Rewrite the NYPizza store
// notice that we instantiate a NY pizza ingredient store
// so we could localize the ingredients

// so when you make a pizza, you have to pass in the ingredients factory


public class NYPizzaStore extends PizzaStore{





	Pizza createPizza(String item){

		Pizza pizza;

		PizzaIngredientFactory ingredientsFactory = 
		new NYPizzaIngredientStore();

		if (type.equals("Cheese")) {
			pizza = new NYStyleCheesePizza(ingredientsFactory);
		} else if(type.equals("pepperoni")){
			pizza = new NYStylePepperoniPizza(ingredientsFactory);
		} else if(type.equals("clam")){
			pizza = new NYStyleClamPizza(ingredientsFactory);
		} else if(type.equals("veggie")){
			pizza = new NYStyleVeggiePizza(ingredientsFactory);
		}else{
			return null;
		}

		return pizza;
	}
}



/*

	The Abstract Factory Pattern

	provides an interface for creating families of related or dependent objects without specifying their concrete classes.

*/


// model 

// client(Pizza)

public class client{
	// access abstract factory and product
}


// Abstract Factory (PizzaIngredientFactory)
public interface AbstractFactory{

}

// Concrete Factory (NYIngredientStore, ChicagoIngredientStore)
public interface ConcreteFactory implements AbstractFactory{

}

// abstract product (eg. dough)
public interface Dough {

}

// concrete product (eg. thick dough)
public class ThickDough implements Dough{

}





