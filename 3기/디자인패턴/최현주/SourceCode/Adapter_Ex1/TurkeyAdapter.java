package Adapter_Ex1;

public class TurkeyAdapter implements IDuck {

	ITurkey turkey;
	
	public TurkeyAdapter(ITurkey turkey)
	{
		this.turkey = turkey;
	}
	
	public void quack() {
		turkey.gobble();
	}

	public void fly() {
		for(int i=0; i<5; i++)
		{
			turkey.fly();
		}
	}
}
