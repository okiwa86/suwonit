
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/** The Command interface */
interface Command {
	void execute();
}

/** The Invoker class */
class Switch {
	private List<Command> history = new ArrayList<Command>();

	public void storeAndExecute(final Command cmd) {
		this.history.add(cmd); // optional
		cmd.execute();
	}

	public void reTryAll() {
		System.out.println("---reTryAll---");
		Iterator<Command> it = history.iterator();
		while (it.hasNext()) {
			it.next().execute();
		}
	}

}

/** The Receiver class */
class Light {

	public void turnOn() {
		System.out.println("The light is on");
	}

	public void turnOff() {
		System.out.println("The light is off");
	}
}

/** The Command for turning on the light - ConcreteCommand #1 */
class FlipUpCommand implements Command {
	private Light theLight;

	public FlipUpCommand(final Light light) {
		this.theLight = light;
	}

	@Override // Command
	public void execute() {
		theLight.turnOn();
	}
}

/** The Command for turning off the light - ConcreteCommand #2 */
class FlipDownCommand implements Command {
	private Light theLight;

	public FlipDownCommand(final Light light) {
		this.theLight = light;
	}

	@Override // Command
	public void execute() {
		theLight.turnOff();
	}
}

/* The test class or client */
public class PressSwitch {
	public static void main(final String[] arguments) {

		// Receiver
		final Light lamp = new Light();

		// Command
		final Command switchUp = new FlipUpCommand(lamp);
		final Command switchDown = new FlipDownCommand(lamp);

		// Invoker
		final Switch mySwitch = new Switch();

		mySwitch.storeAndExecute(switchUp);
		mySwitch.storeAndExecute(switchUp);
		mySwitch.storeAndExecute(switchDown);
		mySwitch.storeAndExecute(switchDown);
		mySwitch.storeAndExecute(switchUp);
		
		mySwitch.reTryAll();

	}
}
