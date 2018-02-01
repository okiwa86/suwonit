import game.*;

public class Main {
	public static void main(String[] args) {
		int money = 100;
		Gamer gamer = new Gamer(money);
		Memento memento = gamer.createMemento();
		
		for (int i = 0; i < 50; i++) {
			System.out.println("=== "+ i);
			System.out.println("현재상태 : "+ gamer);
			
			gamer.bet(); // 게임을 시작하지..
			
			System.out.println("소지금은 " + gamer.getMoney() + "원");
			
			if(gamer.getMoney() > memento.getMoney()) {
				System.out.println("많이 증가했다. 저장한다.");
				memento = gamer.createMemento();
			} else if (gamer.getMoney() < memento.getMoney() / 2) {
				System.out.println("많이 감소했다. 복원한다.");
				gamer.restoreMemento(memento);			
			}
		}
		System.out.println("게임오버 : "+ gamer);
	}
	/* 결과
		=== 45
		현재상태 : Gamer [money=500, fruits=[맛있는 바나나, 맛있는 포도, 포도]]
		과일(맛있는 포도)를 받았다.
		소지금은 500원
		=== 46
		현재상태 : Gamer [money=500, fruits=[맛있는 바나나, 맛있는 포도, 포도, 맛있는 포도]]
		소지금이 절반이 되었다.
		소지금은 250원
		많이 감소했다. 복원한다.
		=== 47
		현재상태 : Gamer [money=800, fruits=[맛있는 바나나, 맛있는 포도]]
		pass
		소지금은 800원
		=== 48
		현재상태 : Gamer [money=800, fruits=[맛있는 바나나, 맛있는 포도]]
		소지금이 증가했다.
		소지금은 900원
		많이 증가했다. 저장한다.
		=== 49
		현재상태 : Gamer [money=900, fruits=[맛있는 바나나, 맛있는 포도]]
		pass
		소지금은 900원
		게임오버 : Gamer [money=900, fruits=[맛있는 바나나, 맛있는 포도]]

	 */
}
