package game;

import java.util.*;

public class Gamer {
	private int money;
	private List<String> fruits = new ArrayList<String>();
	private Random random = new Random();
	private static String[] fruitsname = { "사과", "포도", "바나나", "귤" };

	public Gamer(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void bet() {
		int dice = random.nextInt(6) + 1; //주사위 던지기
		if (dice == 1) {
			money += 100;
			System.out.println("소지금이 증가했다.");
		} else if (dice == 2) {
			money /= 2;
			System.out.println("소지금이 절반이 되었다.");
		} else if (dice == 6) {
			String f = getFruit();
			System.out.println("과일(" + f + ")를 받았다.");
			fruits.add(f);
		} else {
			System.out.println("pass");
		}
	}

	public Memento createMemento() { // memento 저장
		Memento m = new Memento(money);
		Iterator<String> it = fruits.iterator();
		while (it.hasNext()) {
			String f = it.next();
			String prefix = "맛있는 "; // 맛있는것만 저장
			if (f.startsWith(prefix)) {
				m.addFruit(f);
			}
		}
		return m;
	}

	public void restoreMemento(Memento memento) { // 기존 memento에서 복구
		this.money = memento.money;
		this.fruits = memento.getFruit();
	}

	private String getFruit() {
		String prefix = "";
		if (random.nextBoolean()) {
			prefix = "맛있는 ";
		}
		return prefix + fruitsname[random.nextInt(fruitsname.length)];
	}

	@Override
	public String toString() {
		return "Gamer [money=" + money + ", fruits=" + fruits + "]";
	}

}
