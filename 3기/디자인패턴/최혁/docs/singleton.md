# Singleton 패턴

## 이해한 내용

왜쓸까? 클래스의 instance가 하나만 필요할때가 있다.

예를 들면, 설정파일을 불러온다던가, 상수를 가지고 있는다던가...

연속적으로 하나의 instance만 호출해서 사용한다던가

프로그래머가 직접 이 클래스는 

프로그램상에서 한번만 생성되어야 한다고 강제하고 싶을때 사용한다.

## Singleton.java
싱글톤의 생성자는 private이다.

클래스의 외부에서 생성자의 호출을 금지하기 위해서 private을 사용한다.
```java
public class Singleton {
	
	private static Singleton singleton = new Singleton();
    // why private? can't create this class outside
	private Singleton() {
		// check created
		System.out.println("create singleton class");
	}

	public static Singleton getInstance() {
		return singleton;
	}
}
```
## Main.java
obj1과 obj2를 두번 instance를 가져오지만, 결국 같은 instance!
```java
public class Main {
	public static void main(String[] args) {
		System.out.println("Start");
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance(); // doesn't create instance

		if (obj1 == obj2) {
			System.out.println("obj1 == obj2 is same instance!");
		}
		System.out.println("End");
	}
}

```

## Main 결과
```
Start
create singleton class
obj1 == obj2 is same instance!
End
```

