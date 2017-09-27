
public class LimitSupport extends Support {
	private int limit;

	public LimitSupport(String name, int limit) {
		super(name);
		this.limit = limit;
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		if (trouble.getNumber() < limit) { // limit 숫자보다 작을때만 해결!
			return true;
		} else {
			return false;
		}
	}
}
