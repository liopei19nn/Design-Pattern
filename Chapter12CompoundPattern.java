/*Compound Pattern
	Patterns working together

	A Compound Pattern combines two or more patterns into a solution that
	solves a recurring or general problem
*/



// Example : Rebuild a Duck
//  Quackable Interface 

public interface Quackable {
	public void quack();
}

// Some ducks implement the interface
public class MallardDuck implements Quackable { 
	public void quack() {
		System.out.println(“Quack”); 
	}
}

public class RedheadDuck implements Quackable { 
	public void quack() {
		System.out.println(“Quack”); 
	}
}

// add different kind of duck calls
public class DuckCall implements Quackable { 
	public void quack() {
		System.out.println(“Kwak”); 
	}
}
public class RubberDuck implements Quackable { 
	public void quack() {
		System.out.println(“Squeak”); 
	}
}


//  Goose Honk

public class Goose {
	 public void honk() {
	 	System.out.println(“Honk”); 
	 }
}


//  Goose Adapter

public class GooseAdapter implements Quackable {
	// For Adapter pattern, Adapter must implement
	// target interface
	Goose goose;

	public GooseAdapter(Goose goose){
		this.goose = goose;
	}

	public void quack(){
		goose.honk();
	}
}


// Now you can use 

Quackable goose = new GooseAdapter(new Goose());
goose.quack();

// to make a goose honk through a quack method


/* If you want to count quack without change previous duck class*/

// Decorator method to wrap the previous class

public class QuackCounter implements Quackable {
	Quackable duck;
	// make it static so you can count number 
	// from all ducks
	static int numberOfQuacks;

	public QuackCounter (Quackable duck) {
		this.duck = duck;
	}

	// add additianl operation and wrap the original quack method
	public void quack(){
		duck.quack();
		numberOfQuacks++;
	}

	// you could get quack count right through this method
	public static int getQuacks(){
		return numberOfQuacks;
	}
}


// Then you could run simulation, notice you could use QuackCounter
// to instantiate a new duck 
Quackable mallardduck = new QuackCounter(new MallardDuck());

public class DuckSimulator {
	public static void main(String[] args) {
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulate(); 
	}
	void simulate() {
		Quackable mallardDuck = new QuackCounter(new MallardDuck()); 
		Quackable redheadDuck = new QuackCounter(new RedheadDuck()); 
		Quackable duckCall = new QuackCounter(new DuckCall()); 
		Quackable rubberDuck = new QuackCounter(new RubberDuck()); 
		Quackable gooseDuck = new GooseAdapter(new Goose());
		System.out.println("\nDuck Simulator: With Decorator");
		simulate(mallardDuck);
		simulate(redheadDuck); 
		simulate(duckCall); 
		simulate(rubberDuck); 
		simulate(gooseDuck);
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	void simulate(Quackable duck) { 
		duck.quack();
	} 
}



// To make sure all ducks are wrapped correctly, we need
// a Factory Pattern to make sure all ducks is wrapped in the counter

public abstract class AbstractDuckFactory {
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedHeadDuck();
	public abstract Quackable createDuckCall();
	public abstract Quackable createRubberDuck();
}


public class DuckFactory extends AbstractDuckFactory {
	public Quackable createMallardDuck() { 
		return new MallardDuck();
	}
	public Quackable createRedheadDuck() { 
		return new RedheadDuck();
	}
	public Quackable createDuckCall() { 
		return new DuckCall();
	}
	public Quackable createRubberDuck() { 
		return new RubberDuck();
	} 
}

public class CountingDuckFactory extends AbstractDuckFactory {
	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck());
	}
	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedheadDuck());
	}
	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}
	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	} 
}
//  You need to create new Duck object through this Factory, which promise
//  you create duck correctly


// Use Composite Pattern to manage differet ducks in the same way

public class Flock implements Quackable {
	ArrayList quackers = new ArrayList();

	public void add (Quackable quacker) {
		quacker.add(quacker);
	}

	public void quack(){
		Iterator iterator = quackers.iterator();
		while(iterator.hasNext()) {
			Quackable quacker = (Quackable) iterator.next();
			quacker.quack();
		}
	}
}


// simulation 

public class DuckSimulator { // main method here
	void simulate(AbstractDuckFactory duckFactory) {
	Quackable redheadDuck = duckFactory.createRedheadDuck(); 
	Quackable duckCall = duckFactory.createDuckCall();
	Quackable rubberDuck = duckFactory.createRubberDuck(); 
	Quackable gooseDuck = new GooseAdapter(new Goose()); 
	System.out.println("\nDuck Simulator: With Composite - Flocks");

	Flock flockOfDucks = new Flock();
	flockOfDucks.add(redheadDuck); 
	flockOfDucks.add(duckCall); 
	flockOfDucks.add(rubberDuck); 
	flockOfDucks.add(gooseDuck);

	Quackable mallardOne = duckFactory.createMallardDuck(); 
	Quackable mallardTwo = duckFactory.createMallardDuck(); 
	Quackable mallardThree = duckFactory.createMallardDuck(); 
	Quackable mallardFour = duckFactory.createMallardDuck();

	Flock flockOfMallards = new Flock();
	flockOfMallards.add(mallardOne); 
	flockOfMallards.add(mallardTwo); 
	flockOfMallards.add(mallardThree); 
	flockOfMallards.add(mallardFour);


	flockOfDucks.add(flockOfMallards);
	System.out.println("\nDuck Simulator: Whole Flock Simulation");

	simulate(flockOfDucks);
	System.out.println("\nDuck Simulator: Mallard Flock Simulation"); simulate(flockOfMallards);
	System.out.println("\nThe ducks quacked " + QuackCounter.getQuacks() + " times");
	}

	void simulate(Quackable duck) { 
		duck.quack();
	}	 
}

// Using Observer Pattern to track individual behavoir of duck



public interface QuackObservable {
	public void registerObserver(Observer observer);
	public void notifyObservers();
}



public interface Quackable extends QuackObservable {
	public void quack();
}

public class MallardDuck implements Quackable {
	Observable observable;

	public MallardDuck(){
		observable = new Observable(this);
	}

	public void quack(){
		System.out.println("Quack");
		notifyObservers();
	}

	public void registerObserver(Observer observer){
		observable.registerObserver(observer);
	}

	public void notifyObservers(){
		observable.notifyObservers();
	}
}



public class QuackCounter implements Quackable{
	Quackable duck;
	static int numberOfQuacks;

	public QuackCounter(Quackable duck){
		this.duck = duck;
	}

	public void quack(){
		duck.quack();
		numberOfQuacks++;
	}

	public static int getQuacks(){
		return numberOfQuacks;
	}

	public void registerObserver(Observer observer){
		duck.registerObserver(observer);
	}

	public void notifyObservers(){
		duck.notifyObservers();
	}
}



public interface Observer{
	public void update(QuackObservable duck);
}

// when you run update method, it will show which duck just quack
public class Quackologist implements Observer{
	public void update(QuackObservable duck) {
		System.out.println("Quackologist: " + duck + " just quacked.");
	}
}

// To observe the entire flock 

public class Flock implements Quackable { 
	ArrayList ducks = new ArrayList();
	public void add(Quackable duck) { 
		ducks.add(duck);
	}
	public void quack() {
		Iterator iterator = ducks.iterator(); 
		while (iterator.hasNext()) {
			Quackable duck = (Quackable)iterator.next();
			duck.quack(); 
		}
	}

	public void registerObserver(Observer observer) { 
		Iterator iterator = ducks.iterator();
		while (iterator.hasNext()) {
			Quackable duck = (Quackable)iterator.next();
			duck.registerObserver(observer); 
		}
	}
	public void notifyObservers() { 

	}

}

// Above is not compount, it is a series of pattern working together




// Model View Controller

// Model : 
// The model holds all the data, state and application logic.The model is oblivious to the view
// and controller, although it provides an interface to manipulate and retrieve its state and it 
// can send notifications of state changes to observers.



// The model implements the Observer Pattern to keep interested objects updated when state changes occur. Using the Observer Pattern keeps the model completely independent of the views and controllers. 
// It allows us to use different views with the same model, or even use multiple views at once.



// View : 
// Gives you a presentation of the model. The view usually gets the state and data it needs to display directly from the 
// model

// The display consists of a nested set of win- dows, panels, buttons, text labels and so on. Each display component is a composite (like
// a window) or a leaf (like a button). When the controller tells the view to update, it only has to tell the top view component, and Composite takes care of the rest.


// Controller : 
// Takes user input and figures out what it means to the model

// The view and controller implement the classic Strategy Pattern: 
// the view is an object that is configured with a strategy. 
// The controller provides the strategy. The view is concerned only with the visual 
// aspects of the application, and delegates to the controller for any decisions about 
// the interface behavior. Using the Strategy Pattern also keeps the view decoupled from the model 
// because it is the controller that is responsible for interacting with the model to carry out user requests. 
// The view knows nothing about how this gets done.


// example of DJ control 
// 代码太复杂，简略写写， 不懂再看看书好么亲
// Example Model Interface
public interface BeatModelInterface { 
	void initialize();
	void on();
	void off();
	void setBPM(int bpm);
	int getBPM();
	void registerObserver(BeatObserver o);
	void removeObserver(BeatObserver o);
	void registerObserver(BPMObserver o);
	void removeObserver(BPMObserver o); 
}


// for the view of Current beats
public class DJView implements ActionListener, BeatObserver, BPMObserver { 
	BeatModelInterface model;
	ControllerInterface controller;
	JFrame viewFrame;
	JPanel viewPanel; 
	BeatBar beatBar; 
	JLabel bpmOutputLabel;
	public DJView(ControllerInterface controller, BeatModelInterface model) { 
		this.controller = controller;
		this.model = model;
		model.registerObserver((BeatObserver)this); 
		model.registerObserver((BPMObserver)this);
	}
	public void createView() {
		// Create all Swing components here
	}
	public void updateBPM() { 
		// when the model state change, update here
		int bpm = model.getBPM(); 
		if (bpm == 0) {
			bpmOutputLabel.setText(“offline”); 
		} else {
			bpmOutputLabel.setText(“Current BPM: “ + model.getBPM()); 
		}
	}
	public void updateBeat() { 
		// called when the model starts a new beats
		beatBar.setValue(100);
	} 
}

// for the view as UI to control model



public class DJView implements ActionListener, BeatObserver, BPMObserver { 
	BeatModelInterface model;
	ControllerInterface controller;
	JLabel bpmLabel;
	JTextField bpmTextField; 
	JButton setBPMButton; 
	JButton increaseBPMButton; 
	JButton decreaseBPMButton; 
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem startMenuItem; 
	JMenuItem stopMenuItem;
	public void createControls() {
		// Create all Swing components here
	}
	public void enableStopMenuItem() {
		stopMenuItem.setEnabled(true); 
	}
	public void disableStopMenuItem() { 
		stopMenuItem.setEnabled(false);
	}
	public void enableStartMenuItem() { 
		startMenuItem.setEnabled(true);
	}
	public void disableStartMenuItem() { 
		startMenuItem.setEnabled(false);
	}
	public void actionPerformed(ActionEvent event) { 
		if (event.getSource() == setBPMButton) {
			int bpm = Integer.parseInt(bpmTextField.getText());
			controller.setBPM(bpm);
		} else if (event.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if (event.getSource() == decreaseBPMButton) {
			controller.decreaseBPM(); 
		}
	} 
}



// Controller 

public interface ControllerInterface { 
	void start();
	void stop();
	void increaseBPM(); 
	void decreaseBPM(); 
	void setBPM(int bpm);
}

// Here is the implementation of BeatController
public class BeatController implements ControllerInterface { 
	BeatModelInterface model;
	DJView view;

	public BeatController(BeatModelInterface model) { 
		this.model = model;
		view = new DJView(this, model); 
		view.createView();
		view.createControls(); 
		view.disableStopMenuItem(); 
		view.enableStartMenuItem(); 
		model.initialize();
	}

	public void start() { 
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem(); 
	}

	public void stop() { 
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem(); 
	}

	public void increaseBPM() { 
		int bpm = model.getBPM(); 
		model.setBPM(bpm + 1);
	}	
	public void decreaseBPM() { 
		int bpm = model.getBPM(); 
		model.setBPM(bpm - 1);
	}
	public void setBPM(int bpm) { 
		model.setBPM(bpm);
	} 
}


// Using Adatper Pattern to change view and controller, so 
// you could reuse model 


// first, adapt a HeartModel to a BeatModel
public class HeartAdapter implements BeatModelInterface { 
	HeartModelInterface heart;
	public HeartAdapter(HeartModelInterface heart) { 
		this.heart = heart;
	}
	public void initialize() {}
	public void on() {}
	public void off() {}
	public int getBPM() {
		return heart.getHeartRate();
	}
	public void setBPM(int bpm) {}
	public void registerObserver(BeatObserver o) { 
		heart.registerObserver(o);
	}
	public void removeObserver(BeatObserver o) { 
		heart.removeObserver(o);
	}
	public void registerObserver(BPMObserver o) { 
		heart.registerObserver(o);
	}
	public void removeObserver(BPMObserver o) { 
		heart.removeObserver(o);
	} 
}




// here is the new controller for the heart
public class HeartController implements ControllerInterface { 
	HeartModelInterface model;
	DJView view;
	public HeartController(HeartModelInterface model) { 
		this.model = model;
		view = new DJView(this, new HeartAdapter(model)); 
		view.createView();
		view.createControls(); 
		view.disableStopMenuItem(); 
		view.disableStartMenuItem();
	}
	public void start() {}
	public void stop() {}
	public void increaseBPM() {}
	public void decreaseBPM() {}
	public void setBPM(int bpm) {} 
}



















