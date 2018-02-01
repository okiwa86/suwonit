package command;

import java.util.Iterator;
import java.util.Stack;

public class MacroCommand implements Command {
	private Stack<Command> commands = new Stack<Command>(); // 명령의 집합

	@Override
	public void execute() {
		Iterator<Command> it = commands.iterator();
		while (it.hasNext()) {
			it.next().execute();
		}
	}

	public void append(Command cmd) {
		if (cmd != this) {
			commands.push(cmd);
		}
	}

	public void undo() {
		if (!commands.empty()) {
			commands.pop();
		}
	}

	public void clear() {
		commands.clear();
	}

}
