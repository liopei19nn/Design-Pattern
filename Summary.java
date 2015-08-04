//Good OO designs are reusable, extensible and maintainable.

// Object Oriented Principles
// 1. Encapsulate What Varies
// 2. Favor composition than inheritance
// 3. Program to interfaces, not implementations
// 4. Strive for loosely coupled designs between object that interact
// 5. Classes Should be open to extension, but closed for modification
// 6. Dependency Inversion Principle : Depend upon abstractions. DO NOT Depend upon concrete classes.
// 7. Principle of Least Knowledge : talk only to your immediate friends. Limit the object method call to reduce the object dependency
// 8. Hollywood Principle : Don’t call us, we’ll call you. The Hollywood principle gives us a way to prevent “dependency rot.” With the Hollywood Principle, we allow low-level components to hook themselves into a system, but the high-level components determine when they are needed, and how. In other words, the high-level components give the low-level components a “don’t call us, we’ll call you” treatment.
// 9. Single Responsibility Principle : A class should have only one reason to change. Every responsibility of a class is an area of potential change. More than one responsibility means more than one area of change.




// Comparison of Dependency Inversion Principle and Hollywood Principle 
/*
	

		The Dependency Inversion Principle teaches us to avoid the use of concrete classes
		and instead work as much as possible with abstractions. 

		The Hollywood Principle is a technique for building frameworks or components so that
		lower-level components can be hooked into the computation, but without creating dependencies between the lower-level components and the higher-level layers. 


		So, they both have the goal of decoupling, but the Dependency Inversion Principle makes a much
		stronger and general statement about how to avoid dependencies in design.

		The Hollywood Principle gives us a technique for creating designs that allow low-level structures to interoperate while preventing 
		other classes from becoming too dependent on them.



*/

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

/*6.Singleton Pattern
	
	ensures a class has only one instance,
	and provides a global point of access to it.
*/

/*7.Command Pattern

	encapsulates a request as an object, thereby letting you parameterize other 
	objects with different requests, queue or log requests, and support undoable operations.

	seperate the request object from the detailed object know how to perform the request
*/



/*8.The Adapter Pattern 

	converts the interface of a class into another interface the clients expect. 
	Adapter lets classes work together that couldn’t otherwise because of incompatible interfaces.
	

	Object adapter and class adapter : class adapter needs multiple inheritance, which is not applicable in Java

*/



/*9.Facade Pattern
	
	 provides a unified interface to a set of interfaces in a subsytem. 
	 Facade defines a higher-level interface that makes the subsystem easier to use.

*/


/* Intent Comparison
	
	1. Decorator : Do not alter interface, but add responsibility 
	2. Adapter : Alter an interface so that it matches one a client is expecting.
	3. Facade : Provide a simplified interface to subsystems.

*/



/*10.Template Method Pattern
	defines the skeleton of an algorithm in a method, deferring some
	steps to subclasses. Template Method lets subclasses redefine certain 
	steps of an algorithm without changing the algorithm’s structure.
*/





/*11.Iterator Pattern

	provides a way to access the elements of an aggregate object 
	sequentially without exposing its underlying representation.


*/

/*12. The Composite Pattern 

	allows you to compose objects into tree structures to represent part-whole 
	hierarchies. Composite lets clients treat individual objects and compositions 
	of objects uniformly.


	
*/





/*13.State Pattern

	allows an object to alter its behavior when its internal state changes. 
	The object will appear to change its class.

	State transitions can be controlled by the State classes or by the Context classes.

	The class diagram is same to Strategy Pattern, but the intent is not the same

*/


/*  Comparisons

	Strategy : Encapsulate interchangeble behaviors and use delegation to decide which behavior to use

	Template Method : Subclasses decide how to implement steps in an algorithm, algorithm is set in super abstract class

	Factory Method : Subclass decide which concrete class to create. Factory Method is a specialization of Template Method
	
	Adapter : Change the interface of one or more classes

	Iterator : Provides a way to traverse a collection of objects without exposing the collections's implementation.

	Facade : Simplifiy the interface of a group of classes

	Composite : Clients treat collections of objects and individual of objects uniformly

	Observer : Allows a group of objects to be notified when some state changes

	State : Encapsulate state-based behavior and delegate behavior to the current state



