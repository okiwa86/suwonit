import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends Frame implements ActionListener, Mediator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1691757257433082794L;
	private ColleagueCheckBox checkGuest;
	private ColleagueCheckBox checkLogin;

	private ColleagueTextField textUser;
	private ColleagueTextField textPassword;

	private ColleagueButton buttonOk;
	private ColleagueButton buttonCancel;

	public LoginFrame(String title) {
		super(title);
		setBackground(Color.lightGray);
		setLayout(new GridLayout(4, 2));

		createColleagues();
		add(checkGuest);
		add(checkLogin);
		add(textUser);
		add(textPassword);
		add(buttonOk);
		add(buttonCancel);
		pack();
		colleagueChanged();
		setVisible(true);
	}

	@Override
	public void createColleagues() {
		// 생성
		CheckboxGroup g = new CheckboxGroup();
		checkGuest = new ColleagueCheckBox("Guest", g, true);
		checkLogin = new ColleagueCheckBox("Login", g, false);
		textUser = new ColleagueTextField("", 10);
		textPassword = new ColleagueTextField("", 10);
		textPassword.setEchoChar('*');

		buttonOk = new ColleagueButton("OK");
		buttonCancel = new ColleagueButton("Cancel");

		// Mediator 세트
		checkGuest.setMediator(this);
		checkLogin.setMediator(this);
		textUser.setMediator(this);
		textPassword.setMediator(this);
		buttonOk.setMediator(this);
		buttonCancel.setMediator(this);

		// Listener 세트

		checkGuest.addItemListener(checkGuest);
		checkLogin.addItemListener(checkLogin);
		textUser.addTextListener(textUser);
		textPassword.addTextListener(textPassword);
		buttonOk.addActionListener(this);
		buttonCancel.addActionListener(this);
	}

	@Override
	public void colleagueChanged() {
		if(checkGuest.getState()) {
			textUser.setColleagueEnabled(false);
			textPassword.setColleagueEnabled(false);
			buttonOk.setColleagueEnabled(true);
		} else {
			textUser.setColleagueEnabled(true);
			userpassChanged();
		}
	}

	private void userpassChanged() {
		if (textUser.getText().length() > 0) {
			textPassword.setColleagueEnabled(true);
			if (textPassword.getText().length() > 0) {
				buttonOk.setColleagueEnabled(true);
			}else { 
				buttonOk.setColleagueEnabled(false);
			}
		} else {
			textPassword.setColleagueEnabled(false);
			buttonOk.setColleagueEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.toString());
		System.exit(0);
	}

}
