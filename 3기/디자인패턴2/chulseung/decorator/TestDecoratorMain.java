package dp.decorator;
/*
 * 객체를 동적(dynamic)으로 서브 클래스를 이용해 확장한다.
 * 보다시피 데코레이터 패턴이 사용해서 유연하게 기능 확장을 할 수 있지만, 대신 각각 데코레이터 클래스들이 어떤 기능을 수행하는지 알고 있어야 하고 자잘한 클래스들이 많이 생기는 것이 단점
 */
public class TestDecoratorMain {

	public static void main(String[] args) {
		// 그냥 용감한 쿠키 만들기
		Cookie braveCookie = new BraveCookie();

		// 우유를 얹은 용감한 쿠키 만들기
		Cookie milkBraveCookie = new MilkTopping(braveCookie);

		// 그위에 초콜릿을 얹은 용감한 쿠키 만들기
		Cookie chocolateMilkBraveCookie = new ChocolateTopping(milkBraveCookie);

		// 그위에 우유를 한번 더 넣은 용감한 쿠키 만들기
		Cookie chocolateDoubleMilkBraveCookie = new MilkTopping(chocolateMilkBraveCookie);

		System.out.println(chocolateDoubleMilkBraveCookie.getName());

		// 소다 쿠키 만들기
		Cookie SodaCookie = new Cookie() {
			@Override
			public String getName() {
				return "소다 쿠키";
			}
		};

		// 초콜릿을 두번 넣은 소다 쿠키 만들기
		SodaCookie = new ChocolateTopping(new ChocolateTopping(SodaCookie));

		System.out.println(SodaCookie.getName());

	}

}
