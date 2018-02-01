# Builder Pattern

## 왜쓸까

빌딩을 세울 때 우선 지반을 다지고, 골격을 세우고, 아래에서 위로 조금씩 만들어 간다.

일반적으로 복잡한 건축물을 세울 때 한 번에 완성시키기는 어렵다.

우선 전체를 구성하고 있는 각 부분을 들고 단계를 밟아 만들어 나간다.

이와 같이 구조를 가진 인스턴스를 쌓아 올리는 것이 Builder 패턴이다.

## 사고 넓히기

## 누가 무엇을 알고 있을까?

OOP(객체지향 프로그램)에서는 '누가 무엇을 알고 있을까?'라는 화두는 상당히 중요하다. 

즉, 어떤 클래스가 어떤 메소드를 사용할 수 있을까?에 주의하며 프로그래밍을 할 필요가 있다. 

예제를 상기해보자. Main 클래스는 Builder 클래스의 메소드를 모른다. 

Main 클래스는 Director 클래스의 construct 메소드만을 호출한다. 

그러면 Direcotr 클래스 안에서 조용히 일이 진행되고 문서가 완성된다. 

한편 Director클래스가 알고 있는것은 Builder클래스이다. 

Director 클래스는 Builder 클래스의 메소드를 사용해서 문서를 구축한다. 

그러나 Director 클래스는 자신이 실제로 이용하고 있는 클래스가 사실 모른다. 

HawaiianPizzaBuilder인지, SpicyPizzaBuilder인지 또는 Builder의 하위 클래스인지 모른다.

Director클래스는 Builder 클래스의 메소드만을 사용하고 있고 Builder 클래스의 하위 클래스는 그 메소드를 구현하기 때문이다.

Director 클래스가 자신이 이용하고 있는 Builder클래스의 하위 클래스를 모르는 것은 정말 잘 된 일입니다.

왜냐하면 모르기 때문에 교체할 수 있기 때문이다.

TextBuilder의 인스턴스를 Director에 제공하거나 Director의 HTMLBuilder의 인스턴스를 Director에 제공해도 제대로 기능하는 것은 Director 클래스가 Builder클래스의 구체적인 하위 클래스를 모르기 때문이다.

모르기 때문에 교환이 가능하고, 교체가 가능하기 때문에 부품으로서 가치가 높다.

이 '교환가능성'에 대해서 클래스의 설계자는 항상 기억할 필요가 있다.

출처 : http://jellyms.kr/227

## 코드로 보기

```java
/* "Product" */
// 1) 만들 클래스에 대해 정의 한다.
class Pizza {
    private String dough = "";
    private String sauce = "";
    private String topping = "";

    public void setDough(String dough) {
        this.dough = dough;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }
}

/* "Abstract Builder" */
// 2) 어떻게 만들지 빌더 클래스로 정의해본다.
abstract class PizzaBuilder {
    protected Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void createNewPizzaProduct() {
        pizza = new Pizza();
    }

    public abstract void buildDough();
    public abstract void buildSauce();
    public abstract void buildTopping();
}

/* "ConcreteBuilder" */
// 3) 빌더 클래스르르 구현해본다.
class HawaiianPizzaBuilder extends PizzaBuilder {
    public void buildDough() {
        pizza.setDough("cross");
    }

    public void buildSauce() {
        pizza.setSauce("mild");
    }

    public void buildTopping() {
        pizza.setTopping("ham+pineapple");
    }
}

/* "ConcreteBuilder" */
// 3-2) 빌더 구현 클래스 #2
class SpicyPizzaBuilder extends PizzaBuilder {
    public void buildDough() {
        pizza.setDough("pan baked");
    }

    public void buildSauce() {
        pizza.setSauce("hot");
    }

    public void buildTopping() {
        pizza.setTopping("pepperoni+salami");
    }
}

/* "Director" */
// 중요! 디렉터를 추가해, 빌더 클래스를 수행하는 부분을 만든다.
// template pattern 이라면, PizzaBuilder에 constructPizza()를 넣었을텐데
// 디렉터를 추가해서 디렉터가 수행하도록 했다.
class Waiter {
    private PizzaBuilder pizzaBuilder;

    public void setPizzaBuilder(PizzaBuilder pb) {
        pizzaBuilder = pb;
    }

    public Pizza getPizza() {
        return pizzaBuilder.getPizza();
    }

    public void constructPizza() {
        pizzaBuilder.createNewPizzaProduct();
        pizzaBuilder.buildDough();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildTopping();
    }
}

/* A customer ordering a pizza. */
//사용자는 디렉터의 메소드 / 빌더 구현클래스들을 사용하지만,
//어떻게 빌드되는지 알수가 없다.(피자의 제조과정을 손님이 알 필요 없는것)
public class PizzaBuilderDemo {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        PizzaBuilder hawaiianPizzabuilder = new HawaiianPizzaBuilder();
        PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();

        waiter.setPizzaBuilder( hawaiianPizzabuilder );
        waiter.constructPizza();

        Pizza pizza = waiter.getPizza();
    }
}

```
