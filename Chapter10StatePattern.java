/* State Pattern

	allows an object to alter its behavior when its internal state changes. 
	The object will appear to change its class.

*/

// Example : New Gumball machine

// States : No quarter, Has quarter, Gumball sold, Out of Gumball

// Possible Operation : Inserts Quarters, Turn Cranks, Dispense, Ejects Quarter







public class Gumball {

	// four states
	final static int SOLD_OUT = 0; 
	final static int NO_QUARTER = 1; 
	final static int HAS_QUARTER = 2; 
	final static int SOLD = 3;


	// initial state for class, because count of gumball is 0
	int state = SOLD_OUT; 

	// count of gumball
	int count = 0;

	public GumballMachine(int count) { 
		this.count = count;
		if (count > 0) {
			state = NO_QUARTER; 
		}
	}
	

	public void insertQuarter() { 
		if (state == HAS_QUARTER) {
			System.out.println("You can’t insert another quarter"); 
		} else if (state == NO_QUARTER) {
			state = HAS_QUARTER;
			System.out.println("You inserted a quarter"); 
		} else if (state == SOLD_OUT) {
			System.out.println("You can’t insert a quarter, the machine is sold out"); 
		} else if (state == SOLD) {
			System.out.println("Please wait, we’re already giving you a gumball"); 
		}
	}


	public void ejectQuarter() { 
		if (state == HAS_QUARTER) {
			System.out.println("Quarter returned");
			state = NO_QUARTER;
		} else if (state == NO_QUARTER) {
			System.out.println("You haven’t inserted a quarter");
		} else if (state == SOLD) {
			System.out.println("Sorry, you already turned the crank"); 
		} else if (state == SOLD_OUT) {
			System.out.println("You can’t eject, you haven’t inserted a quarter yet"); 
		}
	}


	public void turnCrank() { 
		if (state == SOLD) {
			System.out.println("Turning twice doesn’t get you another gumball!");
		} else if (state == NO_QUARTER) {
			System.out.println("You turned but there’s no quarter");
		} else if (state == SOLD_OUT) {
			System.out.println("You turned, but there are no gumballs");
		} else if (state == HAS_QUARTER) {
			System.out.println("You turned..."); 
			state = SOLD;
			dispense();
		} 
	}


	public void dispense() { 
		if (state == SOLD) {
			System.out.println(“A gumball comes rolling out the slot”); 
			count = count - 1;
		if (count == 0) {
			System.out.println(“Oops, out of gumballs!”);
			state = SOLD_OUT; 
		} else {
			state = NO_QUARTER; }
		} else if (state == NO_QUARTER) { 
			System.out.println(“You need to pay first”);
		} else if (state == SOLD_OUT) { 
			System.out.println(“No gumball dispensed”);
		} else if (state == HAS_QUARTER) { 
			System.out.println(“No gumball dispensed”);
		} 
	}







}



// New Design : 

/*
	
	1. Define a state interface that contains a method for every action in the 
	Gumball machine


	2. Implment a state class for every state of the machine. These classes will be responsible
	for the behavior of the machine when it is in the corresponding state.

	3. Get rid of all of our condition code and delegate to the state class to do the work.

*/


public interface State{
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}


public class NoQuarterState implements State{
	GumballMachine gumballMachine;

	// Pass in a gumball object
	public NoQuarterState(GumballMachine gumballMachine){
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter(){
		System.out.println("You insert a quarter");

		gumballMachine.setState(gumballMachine.getHasQuaterState());
	}

	public void ejectQuarter(){
		System.out.println(“You haven’t inserted a quarter”);
	}

	public void turnCrank() {
		System.out.println(“You turned, but there’s no quarter”);
	}

	public void dispense() { 
		System.out.println(“You need to pay first”);
	}

}


public class GumballMachine {
	State soldOutState; 
	State noQuarterState; 
	State hasQuarterState; 
	State soldState;
	State state = soldOutState;
	int count = 0;
	public GumballMachine(int numberGumballs) { 
		soldOutState = new SoldOutState(this); 
		noQuarterState = new NoQuarterState(this); hasQuarterState = new HasQuarterState(this); 
		soldState = new SoldState(this);
		this.count = numberGumballs; 
		if (numberGumballs > 0) {
			state = noQuarterState; 
		}
	}

	public void insertQuarter() { 
		state.insertQuarter();
	}

	public void ejectQuarter() { 
		state.ejectQuarter();
	}

	public void turnCrank() { 
		state.turnCrank(); 
		state.dispense();
	}

	void setState(State state) { 
		this.state = state;
	}

	void releaseBall() {
		System.out.println(“A gumball comes rolling out the slot...”); 
		if (count != 0) {
			count = count - 1;
		}
	}
// More methods here including getters for each State...
}

public class HasQuarterState implements State { 
	GumballMachine gumballMachine;

	public HasQuarterState(GumballMachine gumballMachine) { 
		this.gumballMachine = gumballMachine;
	}


	public void insertQuarter() {
		System.out.println(“You can’t insert another quarter”);
	}

	public void ejectQuarter() {
		System.out.println(“Quarter returned”); 
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}

	public void turnCrank() {
		System.out.println(“You turned...”); 
		gumballMachine.setState(gumballMachine.getSoldState());
	}

	public void dispense() {
		System.out.println(“No gumball dispensed”); 
	}
}

public class SoldState implements State { //constructor and instance variables here
	public void insertQuarter() {
		System.out.println(“Please wait, we’re already giving you a gumball”);
	}
	public void ejectQuarter() {
		System.out.println(“Sorry, you already turned the crank”);
	}
	public void turnCrank() {
		System.out.println(“Turning twice doesn’t get you another gumball!”);
	}
	public void dispense() { 
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState()); 
		} else {
			System.out.println(“Oops, out of gumballs!”);
			gumballMachine.setState(gumballMachine.getSoldOutState()); 
		}
	}
}

/*State Pattern

	allows an object to alter its behavior when its internal state changes. 
	The object will appear to change its class.

*/
// Model

public class Context {

	// This is the class that can have a number
	// of internal states. 
	State state;
	public void setState(State state){
		this.state = state;
	}

	request(){
		state.handle();
	}
}


public interface State{
	handle();
}

/*
	Concrete States handle request from the Context
	Each Concrete State provides its own implementation for a
	a request. In this way, when the Context changes state, its
	behavior will change as well
*/
public class ConcreteStateA implements State{
	handle(){

	}
}

public class ConcreteStateB implements State{
	handle(){

	}
}


// Delegate state transition to concrete state object may add dependency
// between state objects. So we could also handle state transition in context class.




// Add a winner game for this gumball machine : you have 10% chance to get two gumball with one turning crank


public class GumballMachine{
	State soldOutState; 
	State noQuarterState; 
	State hasQuarterState;
	State soldState; 
	State winnerState;
	State state = soldOutState; 
	int count = 0;
}




public class WinnerState implements State{
	// instance variables and constructor

	// insertQuarter error message

	// ejectQuarter error message

	// turnCrank error message


	public void dispense(){
		System.out.println("YOU’RE A WINNER! You get two gumballs for your quarter"); 

		// Release the gumball first time 
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState()); 
		} else {


			// release a gumball second time
			gumballMachine.releaseBall();
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState()); 
			} else {
				System.out.println(“Oops, out of gumballs!”);
				gumballMachine.setState(gumballMachine.getSoldOutState()); 
			}
		}
	}
}




public class HasQuarterState implements State{
	Random randomWinner = new Random(System.currentTimeMillis());
	GumballMachine gumballMachine;

	public HasQuarterState(GumballMachine gumballMachine){
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter(){
		System.out.println("You cannot insert another quarter");
	}


	public void ejectQuarter() {
		System.out.println(“Quarter returned”); gumballMachine.setState(gumballMachine.getNoQuarterState());
	}

	public void turnCrank(){
		System.out.println("You turned..");

		int winner = randomWinner.nextInt(10);
		if ((winner== 0) && (gumballMachine.getCount() > 1)) {
			gumballMachine.setState(gumballMachine.getWinnerState)
		}
	}


}











