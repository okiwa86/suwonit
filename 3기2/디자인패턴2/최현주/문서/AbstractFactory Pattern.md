# AbstractFactory Pattern    



## 1. AbstractFactory Pattern 정의

- 서로 연관성있는 객체들을 생성하기 위한 인터페이스를 제공한다.
- 구체적인 클래스를 정의하지않고도 객체를 생성할 수 있다.



## 2. 장/단점

#### [장점]

- 객체의 생성을 한 곳에서 관리.
- 새로운 객체가 추가되도 소스 수정이 거의 없다.
- 일관성

#### [단점]

- 새로운 객체를 추가하고 싶다면, 기존의 모든 팩터리에 새 객체의 생성함수를 추가해야한다.

  ​

## 3. 코드 설명

#### [IPizzaIngredientFactory.cs]

- 객체의 생성 메소드 정의를 포함한 인터페이스

  ``` c#
      /// <summary>
      /// 각 재료별 생성 메소드를 정의.
      /// </summary>
      public interface IPizzaIngredientFactory
      {
          IDough CreateDought();
          ISauce CreateSauce();
          ICheese CreateCheese();
      }
  ```

#### [NYPizzaIngredientFactory.cs]

- IPizzaIngredientFactory를 상속받아 객체를 생성하는 메소드를 구현한 서브 클래스

``` c#
    public class NYPizzaIngredientFactory : IPizzaIngredientFactory
    {
        // 뉴욕 버전으로 각 재료를 구현.
        public IDough CreateDought() {
            return new ThinCrustDough();
        }

        public ISauce CreateSauce() {
            return new MarinaraSauce();
        }

        public ICheese CreateCheese() {
            return new CheddarCheese();
        }
    }
```

#### [ChicagoPizzaIngredientFactory.cs]

```c#
     public class ChicagoPizzaIngredientFactory : IPizzaIngredientFactory
    {
        //시카고  버전으로 각 재료를 구현.
        public IDough CreateDought() {
            return new ThinkCrustDough();
        }

        public ISauce CreateSauce() {
            return new TomatoSauce();
        }

        public ICheese CreateCheese() {
            return new CheddarCheese();
        }
    }
```

---

#### [Pizza.cs]

- 상품 객체(피자)의 형태를 정의한 인터페이스

``` c#
 // 여러 객체를 생성하기 위한 추상화 인터페이스
    public abstract class Pizza
    {
        protected string name;

        protected IDough dough;
        protected ISauce sauce;
        protected ICheese cheese;

        //피자를 만드는데 필요한 재료들을 정돈.
        public abstract void Prepare();

        // 모든 피자를 만드는데 공통적으로 필요한 부분.
        public void Bake() {
            Debug.Log("피자를 25분동안 굽는다.");
        }

        public void Cut() {
            Debug.Log("피자를 8등분한다.");
        }

        public void Box() {
            Debug.Log("피자를 포장한다.");
        }

        public void SetName(string name) {
            this.name = name;
        }

        public string GetName() {
            return name;
        }
    }
```

#### [CheesePizza.cs]

```c#
    public class CheesePizza : Pizza
    {
        IPizzaIngredientFactory ingredientFactory;

        //피자를 만드는 Factory를 받아서 준비.
        public CheesePizza(IPizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }

        public override void Prepare()
        {
            Debug.Log(string.Format(" * *** {0} 피자 준비중 *****", name));

            //해당 Factory에서 정의한 재료들로 준비한다.
            dough = ingredientFactory.CreateDought();
            sauce = ingredientFactory.CreateSauce();
            cheese = ingredientFactory.CreateCheese();

            Debug.Log(dough.ToString());
            Debug.Log(sauce.ToString()); 
            Debug.Log(cheese.ToString()); 
        }
    }
```

---

#### [PizzaStore.cs]

``` c#
    // 생산자 클래스.
    public abstract class PizzaStore
    {
        public Pizza OrderPizza(PizzaType type)
        {
            Pizza pizza = CreatePizza(type);

            pizza.Prepare();

            pizza.Bake();
            pizza.Cut();
            pizza.Box();

            return pizza;
        }

        public abstract Pizza CreatePizza(PizzaType type);
    }

    public enum PizzaType
    {
        Cheese,
    }
```

#### [NYPizzaStore.cs]

```c#
    public class NYPizzaStore : PizzaStore
    {
        public override Pizza CreatePizza(PizzaType type)
        {
            Pizza pizza = null;
            IPizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

            if (type == PizzaType.Cheese) {
                pizza = new CheesePizza(ingredientFactory);
                pizza.SetName("뉴욕 스타일의 치즈 피자");
            }

            return pizza;
        }
    }
```

#### [ChicagoPizzaStore.cs]

```c#
    public class ChicagoPizzaStore : PizzaStore
    {
        public override Pizza CreatePizza(PizzaType type)
        {
            Pizza pizza = null;
            IPizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

            if (type == PizzaType.Cheese) {
                pizza = new CheesePizza(ingredientFactory);
                pizza.SetName("시카고 스타일의 치즈 피자");
            }

            return pizza;
        }
    }
```

---

#### [MainProgram.cs]

``` c#
    public class MainProgram : MonoBehaviour {

        void Start () {
            PizzaStore store = new NYPizzaStore();
            Pizza pizza = store.OrderPizza(PizzaType.Cheese);

            PizzaStore store2 = new ChicagoPizzaStore();
            Pizza pizza2 = store2.OrderPizza(PizzaType.Cheese);
        }
    }
```

---

#### [결과]

```
 ***** 뉴욕 스타일의 치즈 피자 피자 준비중 *****
 얇은 반죽 도우
 마리나라 소스
 체다 치즈
 피자를 25분동안 굽는다.
 피자를 8등분한다.
 피자를 포장한다.
 
  ***** 시카고 스타일의 치즈 피자 피자 준비중 *****
  매우 두꺼운 반죽 도우
  토마토 소스
  모짜렐라 치즈
  피자를 25분동안 굽는다.
  피자를 8등분한다.
  피자를 포장한다.
```

