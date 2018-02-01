## Iterator 패턴
이터레이터 패턴

여행정보를 제공받는다고 할때, 

고객사와 제공회사가 다르다고 할 경우 고객이 forloop를 자료구조에 따라 바꾸지 않아도 됨.

고객사에게 캡슐화 되어 넘어감. 알필요도 없고 공통성격이라 좋고 일석이조...?

제공회사가 사정에 의해 자료구조를 바꿨다고 할 경우...난감.(ex:고객사가 1000개)


    기대결과
    ----Suwon----
	광교산
	수원성
	중평떡
	수원갈비
	----Seoul----
	경복궁
	한강
	뭐가있지?
	----Busan----
	광안리 해수욕장
	해운대 해수욕장
	돼지국밥
**고객코드**
```java

public class Client {

	public static void main(String[] args) {
		
		Aggregate suwon = new Suwon();
		Aggregate seoul = new Seoul();
		Aggregate busan = new Busan();
		
		//수원명소 
		System.out.println("----Suwon----");
		print(suwon.iterator());
		//서울명소
		System.out.println("----Seoul----");
		print(seoul.iterator());
		//부산명소
		System.out.println("----Busan----");
		print(busan.iterator());
		
	}
	
	public static void print(IteratorFunc it) {
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}
```

**Aggregate.java**
```java
//인터페이스 Iterator기능을 포함하도록 설정
public interface Aggregate {
	public IteratorFunc iterator();
}
```

**Suwon.java**
```java
import java.util.ArrayList;
import java.util.List;

public class Suwon implements Aggregate {
	List<String> listStr;
	public Suwon() {
		listStr = new ArrayList<String>();
		listStr.add("광교산");
		listStr.add("수원성");
		listStr.add("중평떡");
		listStr.add("수원갈비");
	}
	@Override
	public IteratorFunc iterator() {
		return new SuwonIterator(this);
	}

}
```
**Iterator**
```java
//임의로 만들어본 Iterator 기능 Interface
interface IteratorFunc {
	public Object next();
	public boolean hasNext();
}
```


**Iterator 구현**
```java
//Suwon ArrayList 기능에 맞춘 인터페이스 구현impl
public class SuwonIterator implements IteratorFunc {
	Suwon suwon;
	int index;
	
	public SuwonIterator(Suwon suwon) {
		this.suwon = suwon;
		this.index = 0;
	}
	
	@Override
	public Object next() {
		String x = suwon.listStr.get(index);
		index ++;
		return x;
	}

	@Override
	public boolean hasNext() {
		if (index < suwon.listStr.size()) {
			return true;
		}else {
			return false;
		}
	}

}
```

Suwon / Seoul / Busan 이 각자 다른 자료구조를 가지고 있다라고 할때,

데이터를 받는 고객은 항상 동일한 메소드를 호출해 데이터를 뽑을 수 있다.

    예제
    Suwon = ArrayList<String>
    Seoul = HashMap<Integer, String>
    Busan = String[]
