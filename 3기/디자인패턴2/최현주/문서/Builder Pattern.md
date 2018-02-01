# Builder Pattern



## 1. Builder Pattern 정의

- 객체의 `생성과 표현을 분리`시킨다.

- 하나의 객체를 생성할 때, 객체를 이루고 있는 구성 요소들을 분리하여 세부 구성요소 클래스들을 별도로 만들고, 그 구성 요소 클래스들을 조합하여 하나의 객체를 만든다.

  ​

## 2. Bridge Pattern 의 사용 이유

- 인터페이스와 구현부가 서로 독립적인 구조를 가지면서 확장하기를 원할때 사용





## 3. 장/단점

### [장점]

- 객체를 만드는 방법을 따로 관리함으로서, 객체의 생성과정과 실제 생성부분의 분리가 가능.
- 코드가 깔끔해진다.

### [단점]

- 객체를 생성하려면 Builder 객체를 먼저 생성해아한다.
- 객체의 속성을 추가하면 Builder에도 추가해주어야한다.




## 4. 코드 설명

#### [Pizza.cs]

``` c#
    /// <summary>
    /// 객체 (Product)에 대한 정의.
    /// </summary>
    public class Pizza
    {
        private string dough = string.Empty;
        private string sauce = string.Empty;
        private string topping = string.Empty;

        public void SetDough(string dough) {
            this.dough = dough;
        }

        public void SetSauce(string sauce) {
            this.sauce = sauce;
        }

        public void SetTopping(string topping) {
            this.topping = topping;
        }
```

#### [PizzaBuilder.cs]

``` c#
    /// <summary>
    /// 객체를 생성하는 추상 인터페이스
    /// </summary>
    public abstract class PizzaBuilder
    {
        protected Pizza pizza;

        public void CreateNewPizza() {
            pizza = new Pizza();
        }

        public Pizza GetPizza() {
            return pizza;
        }

        public abstract void BuildDough();
        public abstract void BuildSauce();
        public abstract void BuildTopping();
    }
```

#### [HawaiianPizzaBuilder.cs]

``` c#
    /// <summary>
    /// 빌더의 구현 클래스. 
    /// 객체를 생성할 수 있도록 하는 클래스.(객체를 만들기 위해 부품을 조립)
    /// </summary>
    public class HawaiianPizzaBuilder : PizzaBuilder
    {
        public override void BuildDough() {
            pizza.SetDough("cross");
        }

        public override void BuildSauce() {
            pizza.SetSauce("mild");
        }

        public override void BuildTopping() {
            pizza.SetTopping("ham + pineapples");
        }
    }
```

#### [SpicyPizzaBuilder.cs]

``` c#
    public class SpicyPizzaBuilder : PizzaBuilder
    {
        public override void BuildDough() {
            pizza.SetDough("pan baked");
        }

        public override void BuildSauce() {
            pizza.SetSauce("hot");
        }

        public override void BuildTopping() {
            pizza.SetTopping("pepparoni + salami");
        }
    }
```

#### [Director.cs]

``` c#
    /// <summary>
    /// 객체 생성의 순서를 정함.
    /// </summary>
    public class Director 
    {
        private PizzaBuilder pizzaBuilder;

        // Builder를 받아 필요한 동작을 처리.
        public void SetPizzaBuilder(PizzaBuilder builder)
        {
            pizzaBuilder = builder;
        }

        public Pizza GetPizza()
        {
            return pizzaBuilder.GetPizza();
        }

        //조립 순서를 정함.
        public void ConstructPizza()
        {
            pizzaBuilder.CreateNewPizza();
            pizzaBuilder.BuildDough();
            pizzaBuilder.BuildSauce();
            pizzaBuilder.BuildTopping();

            Debug.Log("ConstructPizza : " + pizzaBuilder);
        }
    }
```

#### [MainProgram.cs]

``` c#
    public class MainProgram : MonoBehaviour
    {
        private void Start()
        {
            Director director = new Director();
            director.SetPizzaBuilder(new HawaiianPizzaBuilder());
            director.ConstructPizza();

            director.SetPizzaBuilder(new SpicyPizzaBuilder());
            director.ConstructPizza();
        }
    }
```

---

#### [실행결과]

```
ConstructPizza : BuilderPattern.ex1.HawaiianPizzaBuilder
ConstructPizza : BuilderPattern.ex1.SpicyPizzaBuilder
```

