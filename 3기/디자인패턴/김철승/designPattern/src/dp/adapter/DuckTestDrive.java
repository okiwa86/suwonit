package dp.adapter;
/*
 * 클라이언트에서 어댑터를 사용하는 방법.

1. 클라이언트에서 타겟 인터페이스를 사용하여 메소드를 호출함으로써 어댑터에 요청을 한다.
2. 어댑터에서는 어댑티 인터페이스를 사용하여 그 요청을 어댑티 에 대한 하나 이상의 메소드를 호출로 변환한다.
3. 클라이언트에서는 호출 결과를 받긴 하지만 중간에 어댑터가 껴 있는지는 전혀 알지 못한다.


출처: http://jusungpark.tistory.com/22 [정리정리정리]
 */
public class DuckTestDrive {

	public static void main(String[] args) {

		MallardDuck duck = new MallardDuck();

		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("The turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("The Duck says...");
		testDuck(duck);

		System.out.println("The TurkeyAdapter says...");
		testDuck(turkeyAdapter);
	}

	public static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
