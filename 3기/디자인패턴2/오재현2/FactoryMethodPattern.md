# Factory Method Pattern

____

### 설명
- 상위 클래스에서 틀을 만들고, 하위 클래스에서 구체적인 처리 내용을 만듭니다.
- 인스턴스를 생성하는 공장을 Template Method 패턴으로 구성한 것이 Factory Method 패턴입니다.
- 객체 생성 기능을 제공하는 Factory 클래스를 정의하고 이를 활용하는 방식으로 설계합니다.
- 인스턴스를 만드는 방법을 상위 클래스 측에서 결정하지만 구체적인 클래스 이름까지는 결정하지 않습니다.
- 따라서 인스턴스 생성을 위한 골격과 실제의 인스턴스 생성의 클래스를 분리해서 생각할 수 있습니다.
- 객체의 생성 코드를 별도의 class / method 로 분리함으로써 객체 생성의 변화에 대비하는데 유용합니다.
- 객체 생성을 전담하는 별도의 클래스를 두는 대신 하위 클래스에서 적합한 클래스의 객체를 생성하는 방식으로도 적용할 수 있습니다.

### 등장 클래스
| 패키지       | 이름           |          해설         |
| :--------- | :-----------: | -------------------: |
| framework  | Product       | 추상메서드 use만 정의되어 있는 추상 클래스 |
| framework  | Factory       | 메서드 create를 구현하는 추상 클래스          |
| idcard     | IDCard        | 메서드 use만 정의되어 있는 추상 클래스 |
| idcard     | IDCardFactory | 메서드 createProduct, registerProduct를 구현하고 있는 클래스 |
| Anonymous     | Main          | 동작 테스트용 클래스 |

### 1. Product 클래스 : 팩토리 메서드로 생성될 객체의 공통 인터페이스
- Product 역할
- 패턴에서 생성되는 인스턴스가 가져야 할 인터페이스를 결정하는 추상 클래스
- 구체적인 내용은 하위 클래스의 ConcreateProduct 역할이 결정합니다.
___
	public framework;
    
    public abstract class Product {
    	public abstract void use();
    }

### 2. Factory 클래스 : 구체적으로 객체가 생성되는 클래스
- Creator 역할
- Product 역할을 생성하는 추상 클래스는 framework 쪽에 가까우며 구체적인 내용은 하위 클래스의 ConcreateCreator 역할이 결정합니다.
- 인스턴스 생성을 위한 메서드를 호출해서 구체적인 클래스 이름에 의한 속박에서 상위 클래스를 자유롭게 만듭니다.
____
	package framework;
    
    public abstract class Factory {
    	public final Product create(String owner){
        	Product p = createProduct(owner);
            registerProduct(p);
            return p;
        }
        protected abstract Product createProduct(String owner);
        protected abstract void registerProduct(Product product);
    }

### 3. IDCard 클래스 : 구체적으로 객체가 생성되는 클래스
- ConcreateProduct 역할
- 구체적인 제품을 결정하며, idcard 쪽에 해당합니다.
____
	package idcard;
    import framework.*;
    
    public class IDCard extends Product {
    	private String owner;
        IDCard(String owner){
        	System.out.println(owner + "의 카드를 만듭니다.");
            this.owner = owner;
        }
        
        public void use(){
        	System.out.println(owner + "의 카드를 사용합니다.");
        }
        
        public String getOwner(){
        	return owner;
        }
    }
    

### 4. IDCardFactory 클래스 : 팩토리 메서드를 구현하는 클래스로 ConcreateProduct 객체를 생성합니다.
- ConcreateCreator 역할
- 구체적인 제품을 만드는 클래스를 결정하며, idcard 쪽에 해당합니다.
____
	package idcard;
	import framework.*;
	import java.util.*;

    public class IDCardFactory extends Factory {
        private List owners = new ArrayList();
        protected Product createProduct(String owner) { // 인스턴스 생성을 위한 메서드
            return new IDCard(owner);
            // new 를 사용하지 않고 인스턴스 생성을 위한 메서드를 호출하여 
            // 구체적인 클래스 이름에 의한 속박에서 상위클래스를 자유롭게 만듭니다.
        }

        protected void registerProduct(Product product) {
            owners.add(((IDCard)product).getOwner());
        }

        public List getOwners() {
            return owners;
        }
    }

### 5. Main
	import framework.*;
    import idcard.*;

    public class Main {
        public static void main(String[] args) {
            Factory factory = new IDCardFactory();
            Product card1 = factory.create("홍길동");
            Product card2 = factory.create("이순신");
            Product card3 = factory.create("강감찬");
            card1.use();
            card2.use();
            card3.use();
        }
	}

### 실행결과
	홍길동의 카드를 만듭니다.
    이순신의 카드를 만듭니다.
    강감찬의 카드를 만듭니다.
    홍길동의 카드를 사용합니다.
    이순신의 카드를 사용합니다.
    강감찬의 카드를 사용합니다.



____

### 참고
http://devbox.tistory.com/entry/DesignPattern-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C-%ED%8C%A8%ED%84%B4
