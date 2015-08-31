/* Proxy Pattern

	provides a surrogate or placeholder for another object
	to control access to it
*/
//Problem : add a monitor for Gumball machine
public class GumballMachine{
	// Other instance variable
	String location;

	public GumballMachine(String location,int count){
		this.location = location;
	}

	// get the location of current Gumball machine
	public String getLocation(){
		return location;
	}

	// other methods here
}

public class GumballMonitor{
	GumballMachine machine;

	public GumballMonitor(GumballMachine machine){
		this.machine = machine;
	}

	public void report(){
		System.out.println(“Gumball Machine: “ + machine.getLocation()); 
		System.out.println(“Current inventory: “ + machine.getCount() + “ gumballs”); 
		System.out.println(“Current state: “ + machine.getState());
	}
}



/*
	Proxy : local representative to a remote object

	It lives in the heap of different JVM, with different address space, but access the same
	object in remote 

	Client would treat it as a local and call local method on them, but proxy will have them sent to the remote object
*/

// To achive the result which client object access service object
// client object -> client helper
// client helper -> service helper
// service helper -> service object


// RMI term : 
	// The client helper is a 'stub' and the service helper is 
	// a 'skeleton'


/*Steps to make remote service*/

// Step 1 : Make a remote interface 
	// this interface will be implemented both by Stub and actual service
	// this interface defines the remote methods that you want clients to call
// Step 2 : Make a remote implementation
	// this is the object that client wants to call methods on
// Step 3 : Generate the stubs and skeletons using rmic
// Step 4 : Start RMI registry (rmiregistry) 
	// client goes to get proxy
// Step 5 : start remote service
	// Open another terminal to run the remote implementation

// Detail

// Step 1 : Make a remote interface 

// 1. Extends java.rmi.Remote, to tell us this interface used to support remote call
// interface is able to extends interface
public interface MyRemote extends Remote{
	// 2. throw RemoteException enforece every call of this method must handle the exception
	public String sayHello() throws RemoteException;

	// 3. arguments and return values must be primitives or Serializable
}

// Step 2 : Make a remote implementation
import java.rmi.*;
import java.rmi.server.*;

// 1. Implements the Remote interface with method client is going to call
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{
	//2. Extends UnicastRemoteObject， UnicastRemoteObject is used for exporting a remote object 
		// with Java Remote Method Protocol (JRMP) and obtaining a stub that communicates to the remote object.
	// if you want to extends other class, you could use UnicastRemoteObject.exportObject()
	public String sayHello(){
		return "Server says, 'hey'";
	}

	// 3. Wirte a no-arg constructor that declares a RemoteException
	// UnicastRemoteObject's constructor will throw RemoteException, so you have to have a constructor throw the exception
	public MyRemoteImpl() throws RemoteException{

	}

	public static void main(String[] args){
		// 4. Register the service with the RMI registry

		// make the remote service available to remote clients. When you register the implementation object, the RMI system actually
		// puts the stub in the registry. Registrer your service using the static rebind() method of jara.rmi.Naming class

		try{
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("RemoteHello", service);
		}catch(Exception e){

		}
	}

} 



// Step 3 : Generate the stubs and skeletons using rmic

// 1. Run rmic on the remote implementation class
	// rmic tool of java JDK will create two classes, stub and skeleton. 
	// 命名模式为remoteImplementationName_Stub 和 remoteImplementationName_Skel

	// terminal 输入 : rmic MyRemoteImple， 会在cd的目录里生成

// Step 4 : run rmiregistry
	// Use terminal and start rmiregistry
		// start it from your 'classes' directory is the simplest way


// Step 5 : start remote service

	// run java MyRemoteImpl



// Service End
// MyRemote is a interface both in service end and client end
// because UnicastRemoteObject create two
MyRemote service = (MyRemote)Naming.lookup("rmi://127.0.0.1/RemoteHello");

// 1. Client does a lookup on the RMI registry
// 2. RMI registry return the stub object

import java.rmi.*;
public class MyRemoteClient{
	public static void main(String[] args){
		new MyRemoteClient().go();
	}

	public void go(){
		try{
			MyRemote service = (MyRemote)Naming.lookup("rmi://127.0.0.1/RemoteHello");
			String s = service.sayHello();

			System.out.println(s);
		}catch(Exception e){
			e.printStackTrace();
		}


	}
}

// 1. At server end, javac all the .java file into class folder, and run rmic, you will have a _Stub
// 2. Run rmiregistry in terminal
// 3. open another terminal and run MyRemoteImpl
// 4. copy _Stub.class file, MyRemote interface .java file to client 
// 5. At client end, compile MyRemote.java and MyRemoteClient.java, and copy _Stub in class folder
// 6. run java MyRemoteClient

//client 
// client.class MyServiceImpl_Stub.class MyRemote.class
//Server
// MyServiceImpl.class MyServiceImpl_Stub.class MyServiceImpl_Skel.class (可能不会有，可能后缀Skel没有)，MyRemote.class




// 千万不要忘了！！
// 1. rmiregistry before starting remote service
// 2. forget to make arguments and return types serializable
// 3. give stub class to the client



// Write the gumball machine

import java.rmi.*;
public interface GumballMachineRemote extends Remote{
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;

}


import java.io.*;
public interface State extends Serializable{
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}


import java.rmi.*; 
import java.rmi.server.*;
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote
{
	// instance variables here
	public GumballMachine(String location, int numberGumballs) throws RemoteException { 
		// code here
	}
	public int getCount() { 
		return count;
	}
	public State getState() { 
		return state;
	}
	public String getLocation() {
		return location;
	}
	// other methods here 
}
public class GumballMachineTestDrive {
	public static void main(String[] args) { 
		GumballMachineRemote gumballMachine = null; int count;
		if (args.length < 2) {
			System.out.println(“GumballMachine <name> <inventory>”);
			System.exit(1); 
		}
		try {
			count = Integer.parseInt(args[1]);
			gumballMachine = new GumballMachine(args[0], count);￼
			￼Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachine);
		} catch (Exception e) { 
			e.printStackTrace();
		}
￼￼} 
}


// for client : monitor
import java.rmi.*;
public class GumballMonitor {
	GumballMachineRemote machine;
	public GumballMonitor(GumballMachineRemote machine){ 
		this.machine = machine;
	}
	public void report() {
		try {
			System.out.println(“Gumball Machine: “ + machine.getLocation()); System.out.println(“Current inventory: “ + machine.getCount() + “ gumballs”); System.out.println(“Current state: “ + machine.getState());
		} catch (RemoteException e) { 
			e.printStackTrace();
	}
￼￼} 
}

// Test drive for monitor

import java.rmi.*;
public class GumballMonitorTestDrive {
public static void main(String[] args) {
	// all the location
	String[] location = {
		"rmi://santafe.mightygumball.com/gumballmachine",
		"rmi://boulder.mightygumball.com/gumballmachine", 
		"rmi://seattle.mightygumball.com/gumballmachine"};
	GumballMonitor[] monitor = new GumballMonitor[location.length];
	for (int i=0;i < location.length; i++) { 
		try {
			// get the proxy to each remote machine
			GumballMachineRemote machine = (GumballMachineRemote) Naming.lookup(location[i]);

			// once we get a proxy to the remote machine, we create a new GumballMonitor
			// and pass it the machine to monitor
			monitor[i] = new GumballMonitor(machine);
			System.out.println(monitor[i]);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		for(int i=0; i < monitor.length; i++) { 
			// iterate through machines to print report
			monitor[i].report();
		} 
	}
}



// Use Proxy Pattern to create a representative object
// that controls access to another object, which may be remote, expensive or in 
// need of securing




// Proxy controls access
// A remote proxy controls access to a remote object
// A virtual proxy controls access to a resuouse that is expensive to create
// A protection proxy controls access to a resource based on access rights


// model 

public interface Subject{
	// both RealSubject and Proxy implements it
	// so client could treat proxy just like RealSubject
	request();
}
public class RealSubject implements Subject{
	// does the real work
	request();
}
public class Proxy implements Subject{
	// keeps reference of the subject
	request();
}




// Virtual Proxy

// Virtual Proxy acts as a representative for an object that may be expensive to create.
// The Virtual Proxy often defers the creation of the object until it is needed. The Virtual Proxy also 
// acts as a surrogate for the object before and while it is being created. After that, the proxy delegates requests directly to the RealSubject

// Example : image icon loader, before load the full pic, print "Please wait"
// after load the full pic, proxy will print it out

class ImageProxy implements Icon{
	ImageIcon imageIcon;// display pic when it is loaded
	URL imageURL;
	Thread retrievalThread;
	boolean retrieving = false;

	public ImageProxy(URL url){
		imageURL = url;
	}

	public int getIconWidth(){
		if (imageIcon != null) {
			return imageIcon.getIconWidth();
		}else{
			return 800;
		}
	}
	public int getIconHeight(){
		if (imageIcon != null) {
			return imageIcon.getIconHeight();
		}else{
			return 600;
		}
	}

	public void paintIcon(final Component c, Graphics g, int x, int y) { 
		if (imageIcon != null) {
			imageIcon.paintIcon(c, g, x, y); 
		} else {
			g.drawString(“Loading CD cover, please wait...”, x+300, y+190); 
			if (!retrieving) {
				retrieving = true;
				retrievalThread = new Thread(new Runnable() {
					public void run() { 
						try {

							// in thread, we instantiate the icon object, Its constructor
							// will not return until the image is loaded
							imageIcon = new ImageIcon(imageURL, “CD Cover”);
							c.repaint();
						} catch (Exception e) {
							e.printStackTrace(); 
						}
					} 
				});
				retrievalThread.start(); 
			}
		} 
	}
}



// Dynamic Proxy : create a protection proxy usew Dynamic Proxy

public interface Subject{
	request();
}

public class RealSubject implements Subject{
	request();
}


public interface InvocationHandler{
	invoke();
}

// proxy and InvocationHandler class combined as proxy
public class Proxy implements Subject{
	request();
}

public class InvocationHandler{
	invoke();
}

// Example of Dynamic Proxy

public interface PersonBean{
	String getName();
	String getGender();
	String getInterest();
	int getHotOrNotRating();

	void setName(String name);
	void setGender(String gender);
	void setInterests(String interests);
	void setHotOrNotRating(int rating);
}

public class PersonBeanImpl implements PersonBean { 
	String name;
	String gender;
	String interests; 
	int rating;
	int ratingCount = 0;
	public String getName() { 
		return name;
	}
	public String getGender() { 
		return gender;
	}
	public String getInterests() { 
		return interests;
	}
	public int getHotOrNotRating() { 
		if (ratingCount == 0) 
			return 0; 
		return (rating/ratingCount);
	}
	public void setName(String name) { 
		this.name = name;
	}
	public void setGender(String gender) { 
		this.gender = gender;
	}
	public void setInterests(String interests) { 
		this.interests = interests;
	}


	// we have to control access, to make sure a user could
	// not change other's personal info, and not change their own 
	// rating
	public void setHotOrNotRating(int rating) { 
		this.rating += rating;
		ratingCount++;
	}
}

// use java.lang.reflect 
// reflection and InvocationHandler



// Step one 
// create two InvocationHandlers
// Java will  take care of creating the actual proxy class and object, we just need to supply a handler that knows
// what to do when a method is called on it

	// // 1. proxy is called on the method setHotOrNotRating()
	// proxy.setHotOrNotRating(9);

	// // 2. proxy called invoke() on the InvocationHandler
	// invoke(Object proxy, Method method, Object[] args);

	// // 3. handler decides what it should do with the request and possibly forwards it on to the RealSubject
	// return method.invoke(person,args);



import java.lang.reflect.*;
// InvocationHandler is part of java.lang.reflect
public class OwnerInvocationHandler implements InvocationHandler{
	PersonBean person;


	public OwnerInvocationHandler(PersonBean person){
		// pass Real Subject in the constructor and we keep a reference to it
		this.person = person;

	}

	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException{
		// if the method is getter or setter, it is allowed for Owner
		// if the method is setHotOrNotRating, it is not allowed

		try{

			if (method.getName().startWith("get")) {
				return method.invoke(person,args);
			} else if (method.getName().startWith("setHotOrNotRating")){
				throw new IllegalAccessException();
			} else if (method.getName().startWith("set")){
				return method.invoke(person, args);
			}
		
		} catch (InvocationTargetException e){
				// this is when the real subject throws an exception
			e.printStackTrace();
		}

		return null;
	}
}




// Step two 
// Write code that creates the dynamic proxies
// We need to write a little bit of code to generate the proxy 
// class and instantiate it. We’ll step through this code in just a bit.


// this method take in a person object and return proxy for it. Becuase the proxy has the same
// interface as the subject, we return a PersonBean

PersonBean getOwnerProxy(PersonBean person){
	return  (PersonBean) Proxy.newProxyInstance(

		perosn.getClass().getClassLoader(),
		perosn.getClass().getInterfaces(),
		new OwnerInvocationHandler(person)

		);
}

// Step three
// Wrap any PersonBean 
// create appropriate proxy for the PersonBean, for customer himself, it is owner
// for other user is non-owner to check out


public class MatchMakingTestDrive {
	// instance variable here

	public static void main(String[] args) { 
		MatchMakingTestDrive test = new MatchMakingTestDrive(); 
		test.drive();
	}

	public MatchMakingTestDrive() {
		initializeDatabase();
	}

	public void drive() {
		PersonBean joe = getPersonFromDatabase(“Joe Javabean”); 
		PersonBean ownerProxy = getOwnerProxy(joe); 

		System.out.println(“Name is “ + ownerProxy.getName()); 
		ownerProxy.setInterests(“bowling, Go”); 
		System.out.println(“Interests set from owner proxy”); 

		try {
			// this should not work
			ownerProxy.setHotOrNotRating(10); 
		} catch (Exception e) {
			System.out.println(“Can’t set rating from owner proxy”); 
		}


		System.out.println(“Rating is “ + ownerProxy.getHotOrNotRating());
		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println(“Name is “ + nonOwnerProxy.getName()); 
		try {
			// this should not work
			nonOwnerProxy.setInterests(“bowling, Go”);
		} catch (Exception e) {
			System.out.println(“Can’t set interests from non owner proxy”); 
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println(“Rating set from non owner proxy”);
		System.out.println(“Rating is “ + nonOwnerProxy.getHotOrNotRating());
	}
}


// dynamic proxy ： proxy is dynamic because its class is created at runtime. Before you run the code
// there is no proxy class. Proxy class will be created dynamically by the static method Proxy.newProxyInstance() method

// The Proxy class has a static method called isProxyClass(). And proxy class will act like any other class that implements a particular set of interfaces


// restriction on the types of interfaces one can pass into newProxyInstance()

// we always pass newProxyInstance() an array of interfaces, not class
// all non-public interfaces need to be from the same package. You cannot have interface with clashing method names(two interface cannot have same method name)


// Proxy zoo

// 1. Firewall Proxy : control access to a set of netowrk resources, protecting the subject from "bad" clients

// 2. Smart Reference Proxy : provicdes additional actions whenever a subject is referenced, such as counting 
// 							number of references to an object

// 3. Caching Proxy : provides temporary storage for results of operations that are expensive. It can also 
// 						allow multiple clients to share the results to reduce computation or netowrk latency
		 // often seen in web server proxies as well as content management and publishing systems



// 4. Synchronization Proxy : provides safe access to a subject from multiple threads : seen haning around Java Spaces, where it controls
	// synchronized access to an underlying set of objects in a distributed environment


// 5. Complexity Hiding Proxy : hides complexity of and controls access to a complex set of classes. This is sometimes called the Facade Proxy for obvious reasons.
//  	The Complexity Hiding Proxy differs from the Facade Pattern in that the proxy controls access, while the Facade Pattern just provides an alternative interface.



// 6. Copy-On-Write Proxy : controls the copying of an object by deferring the copying of an object until it is required by a client. This is a variant of the Virtual Proxy.











