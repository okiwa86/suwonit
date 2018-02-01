# Factory Method 패턴

## 이해한 내용

**Creator** 라는 abstract class 가 템플릿패턴의 템플릿 역할을 한다.

공통함수를 갖고 생성할때, 템플릿 메소드 처럼 step을 가지고 있는다.

**Prodcut**는 생성이 될 객체에 대한 Interface.

**Concrete Creator**는 각 Step의 메소드 내용을 구현한다.

**Concrete Product**는 Product의 Interface를 구현한다.

## Creator 
create(String owner) 이 부분이 생성할때 시작하는 공통 메소드
```java
package com.hyuky.framework;

// Creator
public abstract class Factory {
	// Product make and some other function Like Template pattern!!
	public final Product create(String owner) {
		Product product = createProduct(owner); // step1
		registerProduct(product); // step2
		return product;
	}
	// Product create do only! create
	// tip: protected = 동일 패키지에 속하는 클래스와 하위 클래스 관계의 클래스에 의해 접근이 가능하다. 
	protected abstract Product createProduct(String owner);
	// Some other function #1
	protected abstract void registerProduct(Product product);
}

```
## Product
생성될 객체의 인터페이스 정도, abstract class 기능을 가져도 무방할 듯
```java
package com.hyuky.framework;

public abstract class Product {
	public abstract void use();
}
```
## Concrete Creator
Creator method에서 동작할 상세 구현내용을 작성한다.
```java
package com.hyuky.idcard;

import java.util.ArrayList;
import java.util.List;
import com.hyuky.framework.*;

// Creator Factory
public class IDCardFactory extends Factory {
	private List<String> ownerList = new ArrayList<String>();
	
	// Create only
	@Override
	protected Product createProduct(String owner) {
		return new IDCard(owner);
	}

	// Some other function #1
	@Override
	protected void registerProduct(Product product) {
		ownerList.add(((IDCard) product).getOwner());
	}

	public List<String> getOwnerList() {
		return ownerList;
	}
}

```
## Concrete Product
```java
package com.hyuky.idcard;

import com.hyuky.framework.Product;

public class IDCard extends Product {
	private String owner;

	//Constructor
	IDCard(String owner) {
		System.out.println(owner + "'s card make");
		this.owner = owner;
	}

	@Override
	public void use() {
		System.out.println(owner + "'s use card");
	}

	public String getOwner() {
		return owner;
	}
}

```

## Main
```java
package com.hyuky;

import com.hyuky.framework.Factory;
import com.hyuky.framework.Product;
import com.hyuky.idcard.IDCardFactory;

public class Main {

	public static void main(String[] args) {
		Product card1 = factory.create("Ann");
		Product card2 = factory.create("Mike");
		Product card3 = factory.create("Brown");

		card1.use();
		card2.use();
		card3.use();

		System.out.println(((IDCardFactory) factory).getOwnerList());
	}
}

```

## Main 결과
```
Ann's card make
Mike's card make
Brown's card make
Ann's use card
Mike's use card
Brown's use card
[Ann, Mike, Brown]
```