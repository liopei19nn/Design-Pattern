//Good OO designs are reusable, extensible and maintainable.

// Object Oriented Principles
// 1. Encapsulate What Varies
// 2. Favor composition than inheritance
// 3. Program to interfaces, not implementations
// 4. Strive for loosely coupled designs between object that interact
// 5. Classes Should be open to extension, but closed for modification
// 6. Dependency Inversion Principle : Depend upon abstractions. DO NOT Depend upon concrete classes.
			// from chapter 4, detailed in Chapter4FactoryPattern.java



// Object Oriented Patterns

/* 1.Strategy Pattern

	 defines a family of algorithms, encapsulates each one, 
	 and makes them interchangeable. Strategy lets the algorithm 
	 vary independently from clients that use it.

*/


/* 2.Observer Pattern
	
	defines a one-to-many dependency between 
	objects so that when one object changes state,
	all of its dependents are notified and 
	updated automatically.
	

*/


 /*3.Decorator Pattern
	
	attaches additional responsibilities to an object dynamically. 
	Decorators provide a flexible alternative to subclassing for extending functionality.
	Meant to add behavior to the object they wrap
*/

/*4.Factory Method Pattern
 	Defines an interface for creating an object, 
 	but lets subclasses decide which class to instantiate. 
 	Factory Method lets a class defer instantiation to subclasses.

*/


/*5.The Abstract Factory Pattern

	provides an interface for creating families of related 
	or dependent objects without specifying their concrete classes.

*/

/*4 and 5 comparison
	
	Factory method create objects through inheritance
	Abstract Factory create objects through composition
	
	Abstract Factory can handle a set of related objects.
	But need change interface when to extends the set of products
	 
	Factory method intent Defer instantiation to its subclasses
	Abstract Factory intent to create families of related objects without having to depend on their concrete classes 

*/











