package Adapter_Ex1;

import java.util.Random;

//adapter pattern.
// 한 클래스의 interface를 사용하고자 하는 다른 interface로 변환.
// 인터페이스 호환성때문에 같이 쓸 수 없는 클래스들을 연결해서 쓸 수 있음.

public class DuckAdapter implements ITurkey {

	IDuck duck;
	Random rnd;
	
	public DuckAdapter(IDuck duck) {
		this.duck = duck;
		
		rnd = new Random();
	}
	
	public void gobble() {
		duck.quack();
	}

	public void fly() {
		if(rnd.nextInt(5) == 0)
			duck.fly();
	}
}
