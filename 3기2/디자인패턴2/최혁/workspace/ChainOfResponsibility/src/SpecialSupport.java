
public class SpecialSupport extends Support {

	private int specialNumber;

	public SpecialSupport(String name, int specialNumber) {
		super(name);
		this.specialNumber = specialNumber;
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		if (trouble.getNumber() == specialNumber) { // specail Number만 해결
			return true;
		} else {
			return false;
		}
	}

}
