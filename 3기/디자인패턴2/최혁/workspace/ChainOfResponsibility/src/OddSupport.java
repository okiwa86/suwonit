
public class OddSupport extends Support {
	
	public OddSupport(String name) {
		super(name);
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		if (trouble.getNumber() % 2 == 1) { // odd 만 해결 한다
			return true;
		} else {
			return false;
		}
	}
}
