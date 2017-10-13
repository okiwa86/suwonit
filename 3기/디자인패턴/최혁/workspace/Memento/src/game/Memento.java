package game;

import java.util.ArrayList;

public class Memento {
	int money; // 소지금
	ArrayList<String> fruits; // 과일
	
	Memento(int money) { // 생성자
		this.money = money;
		this.fruits = new ArrayList<String>();
	}
	
	public int getMoney() { // 소지금을 얻는다. (얘만 public - main 조회용)
		return money;
	}
	
	ArrayList<String> getFruit() {
		return fruits;
	}
	
	void addFruit(String fruit) {
		this.fruits.add(fruit);
	}
}
