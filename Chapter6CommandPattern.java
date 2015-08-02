// Command Pattern
// encapsulates a request as an object, thereby letting you parameterize other 
// objects with different requests, queue or log requests, and support undoable operations.

// seperate the request object from the detailed object know how to perform the request


// Example : Build an application to remote control 7 appliance in home, you have 
// 7 pair of "On" and "Off" buttons, each pair of buttons is responsible for one
// appliance ,and 1 undo buttons



// model like :

// Client -(createCommand,setCommand)-> Invoker --(execute)--> receiver

// a command interface for all command
public interface Command{
	public void execute();
}


public class LightOnCommand implements Command{
	Light light;


	// light is the receiver for execution 
	public LightOnCommand(Light light){
		this.light = light;
	}

	public void execute(){
		light.on();
	}
}

public class GarageDoorOpenCommand implements Command{
	GarageDoor door;
		
	public GarageDoorOpenCommand(GarageDoor door) {
		this.door = door;
	}
	
	public void execute() {
		// TODO Auto-generated method stub
		door.open();
		
		
	}
}



// here is the Invoker
public class SimpleRemoteControl{
	Command slot;

	public SimpleRemoteControl(){}

	public void setCommand(Command command){
		slot = command;
	}

	// button pressed is the 
	// trigger for action to receiver
	public void buttonWasPressed(){
		slot.execute();
	}
}

// test runner

public class TestRunner {
	public static void main(String[] args) {
		Light light = new Light();
		GarageDoor door = new GarageDoor();
		SimpleRemoteController controller = new SimpleRemoteController();
		Command lightOnCommand  = new LightOnCommand(light);
		Command garageDoorOpenCommand = new GarageDoorOpenCommand(door);
		
		
		controller.setCommand(lightOnCommand);
		
		controller.buttonPressed();
		
		controller.setCommand(garageDoorOpenCommand);
		controller.buttonPressed();
		
		
	}
}

// Defination of Command Pattern

/*
Command Pattern
encapsulates a request as an object, thereby letting you parameterize other 
objects with different requests, queue or log requests, and support undoable operations.
*/

public class Client{

	// responsible for instantiate receiver
	// and set receiver to command

	Receiver receiver = new Receiver();
	Command command = new Command(receiver);
}

public class Invoker{

	Command command;

	setCommand(Command command){
		this.command = command;
	}

	reactionToClient(){
		command.execute();
	}
}


public class Receiver{
	behavior(){

	}
}

public class ConcreteCommand{
	Receiver receiver;

	public Command(Receiver r){
		this.receiver = r;
	}

	execute(){
		receiver.behavior();
	}
}



// Invoker handle multiple command 

public class RemoteControl{
	Command[] onCommands;
	Command[] offCommands;

	public RemoteControl(){
		onCommands = new Command[7];
		offCommands = new Command[7];


		// initialize the command array

//!!!	// why we need a noCommand ??

		// because at run time, we do not want to test if any action is not loaded with 
		// command, which is a waste of performance, so we put a no-command there, and this
		// nocommand do nothing in its execute!

		// This NoCommand object is know as "NULL Object" for moving the null handle process
		// away from client
		Command noCommand= new NoCommand();

		for (int i = 0; i < 7 ; i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}

	}


	public void setCommand(int slot, Command onCommand, Command offCommand){
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}

	public void onButtonWasPushed(int slot){
		onCommands[slot],execute();
	}

	public void offButtonWasPushed(int slot){
		offCommands[slot].execute();
	}

	public String toString() {

	}

}


// Add undo method to command

public interface Command{
	public void execute();

	public void undo();
}


public class LightOnCommand implements Command{
	Light light;


	// light is the receiver for execution 
	public LightOnCommand(Light light){
		this.light = light;
	}

	public void execute(){
		light.on();
	}

	public void undo(){
		light.off();
	}
}

// Add undo to undo the exa
// this is a dumb version, if you press "On", and press undo
// light will be off, but if you press undo the second time
// the light will still be off because the undoCommand reference
// to a LightOnCommand

// may be a Stack of status could be used to handle this issue

public class RemoteControl{
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;

	public RemoteControl(){
		onCommands = new Command[7];
		offCommands = new Command[7];


		// initialize the command array

		Command noCommand= new NoCommand();

		for (int i = 0; i < 7 ; i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}

		// do as other command, considering no assigned command issue
		undoCommand = noCommand; 

	}


	public void setCommand(int slot, Command onCommand, Command offCommand){
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}

	public void onButtonWasPushed(int slot){
		onCommands[slot].execute();

		// set the executed command to undoCommand for undo preparation
		undoCommand = onCommand[slot];
	}

	public void offButtonWasPushed(int slot){
		offCommands[slot].execute();

		// set the executed command to undoCommand for undo preparation
		undoCommand = onCommand[slot];
	}

	public String toString() {

	}


	public void undoButtonPushed(){
		undoCommand.undo();
	}

}


// add status for undo

public class CeilingFanHighCommand implements Command{
	CeilingFan ceilingFan;
	int prevSpeed;

	public CeilingFanHighCommand(CeilingFan fan){
		this.ceilingFan = fan;
	}

	public void execute(){
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.high();
	}

	public void undo(){
		if (prevSpeed == CeilingFan.HIGH) {
			ceilingFan.high();
		}else if (prevSpeed == CeilingFan.MEDIUM) {
			ceilingFan.medium()
		}else if(prevSpeed == CeilingFan.LOW){
			ceilingFan.low();
		}
	}
}



// Macro Command to handling more than one request to multiple receiver

public class MacroCommand implements Command{
	Command[] commands;

	public MacroCommand(Command[] commands){
		this.commands = commands;
	}

	public void execute(){
		for (int i = 0; i < commands.length ; i++ ) {
			commands[i].execute;
		}
	}

	public void undo(){
		for (int i = 0; i < commands.length ; i++ ) {
			commands[i].undo();
		}
	}
}


// application of Command Pattern

// 1 Queueing Request
	// handle job queues with limited number of threads in pool


// 2 Logging Request
	// save the states of process in case they face program crash
	// they could load the previous status from disk

public interface Command{
	execute();
	undo();
	save();
	load();
}


























