// Singleton Pattern
// ensures a class has only one instance,
// and provides a global point of access to it.


// Only one object was instantiated

// used to handle pool of things, like thread pool or connection
// used to handle something you donot want a copy exist


// 1st Model 

public class Singleton{
	private static Singleton uniqueInstance;

	private Singleton(){

	}

	public static Singleton getInstance(){
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}

		return uniqueInstance;
	}
}

// 1st model cannot handle thread race condition
// if thread1 executes 
//"if (uniqueInstance == null)" and stop

// then 
// thread 2 call
//"if (uniqueInstance == null)" 

// two thread will both find uniqueInstance == null and instantiate two 
// different instance!! which on the violate singleton pattern


// simple thread lock

public class Singleton{
	private static Singleton uniqueInstance;

	private Singleton(){

	}


	// add synchronized to lock thread for 
	// only one thread access to this method
	public static synchronized Singleton getInstance(){
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}

		return uniqueInstance;
	}
}


// 加锁方法开销太大，提升多线程性能的方法？

// 1 如果getInstance()方法使用的不多，可以继续使用这种加锁的方法

// 2 将这种lazy instantiation 的方法转化为一种eager instantiation的方法

	// 用于getInstance任务量适中的情况

	public class Singleton{
		private static Singleton uniqueInstance = new Singleton();
		private Singleton(){ }

		public static Singleton getInstance(){
			return uniqueInstance;
		}

	}


// 3 Double-checked locking to reduce the use of synchronization in getInstance()
	// 1ST check is the instance is null
	// if not null, no need to synchronization

	// 2ND check in the synchronized part


	// volatile keyword is not thread safe before Java 1.4

	// 极大的减小开销的情况
	public class Singleton{

		// volatile means the variable will be accessed
		// by more than 1 thread and could be changed
		// unexpectedly, and ensured it could be handled correctly


		private volatile static Singleton uniqueInstance;

		private Singleton(){

		}

		public static Singleton getInstance(){

			// 1st lock check
			if (uniqueInstance == null) {

				// synchronized(Singleton.class)
				// used for static method on class



				// synchronized(this)
				// used for non static method, which not 
				// instance method for each instance
				synchronized(Singleton.class){

					// 2nd lock check
					if (uniqueInstance == null) {
						uniqueInstance = new Singleton();
					}
				}
			}

			return uniqueInstance;
		}

	}





// Subclass a Singleton class?

// If you subclass a Singleton, you have to change its
	// constructor to public, which make it not a Singleton any more

// The static variable of Singleton will also extended in subclass, which is not desirable













