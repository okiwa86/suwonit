import java.awt.Button;
import java.awt.HeadlessException;

public class ColleagueButton extends Button implements Colleague {

	private Mediator mediator;

	public ColleagueButton(String caption) {
		super(caption);
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void setColleagueEnabled(boolean enabled) {
		// Mediator 에서 유효 / 무효 지시
		setEnabled(enabled);
	}

}
