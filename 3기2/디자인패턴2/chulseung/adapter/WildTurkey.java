package dp.adapter;
//免贸: http://jusungpark.tistory.com/22 [沥府沥府沥府]
public class WildTurkey implements Turkey{

	@Override
	public void gobble() {
		 System.out.println("Gobble gobble");

	}

	@Override
	public void fly() {
		System.out.println("I'm flying a short distance");
		
	}

}
