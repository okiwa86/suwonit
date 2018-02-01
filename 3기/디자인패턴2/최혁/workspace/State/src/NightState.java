
public class NightState implements State {
	public static NightState singleton = new NightState();

	private NightState() {
	}

	public static State getInstance() {
		return singleton;
	}

	@Override
	public void doClock(Context context, int hour) {
		if (hour >= 9 && hour < 17) {
			context.changeState( DayState.getInstance());
		}
	}

	@Override
	public void doUse(Context context) {
		context.recordLog("금고사용: 야간");
	}

	@Override
	public void doAlarm(Context context) {
		context.callSecurityCenter("비상벨: 야간");
	}

	@Override
	public void doPhone(Context context) {
		context.callSecurityCenter("일반통화: 야간");
	}

	@Override
	public String toString() {
		return "[야간]";
	}

}
