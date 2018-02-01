package dp.adapter;

public class TurkeyAdapter implements Duck {

	Turkey turkey;//인터페이스가 다르기 때문에 Turkey객체를 바로 사용할 수는 없다.

	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}

	@Override
	public void quack() {
		turkey.gobble();
	}

	@Override
	public void fly() {
		turkey.fly();
	}

}
