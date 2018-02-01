package drawer;

import java.awt.Point;

import command.Command;

public class DrawCommand implements Command {
	protected Drawable drawable; // 그림 그릴 대상
	private Point position; // 그림 그리는 위치
	
	public DrawCommand(Drawable drawable, Point position) {
		super();
		this.drawable = drawable;
		this.position = position;
	}


	@Override
	public void execute() {
		drawable.draw(position.x, position.y);
	}

}
