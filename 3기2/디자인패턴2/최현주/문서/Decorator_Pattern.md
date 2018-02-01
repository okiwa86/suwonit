# Decorator Pattern 



## 1. Decorator Pattern 
- 객체에 `추가적인 요소를 동적`으로 첨가할 수 있다.

- 유연한 확장을 위해 상속 대신 사용할 수 있다.

  ​



## 2. UML Diagram
![uml](https://www.codeproject.com/KB/architecture/479635/GoFClassDiagram.jpg)



## 3. 장/단점

### [장점]
- 클래스에 새로운 기능을 추가할 때 기존 소스변경 없이 클래스만 추가 가능


### [단점]

- 잡다한 클래스들이 많아질 수 있다.
- 구조 파악이 복잡해질 수 있다 (클래스를 겹겹이 쌓는 구조이기 때문에)
  ​

## 4. 코드 설명

- 기본적인 음료(Espresson, HouseBlend, DarkLost, Decaf)에 동적으로 

  첨가물(Soy, Mocha, Whip, Steam Milk)을 설정하는 코드

### [Beverage.cs]

~~~~c#
    /// <summary>
    /// Component 추상 클래스
    /// </summary>
    public abstract class Beverage
    {
        protected string description = "설명 없음";

        public virtual string GetDescription() {
            return description;
        }

        public abstract float Cost();
    }
~~~~


### [Espresso.cs]

~~~~c#
    /// <summary>
    /// ConcreteComponent 역할
    /// Component를 상속받아 구체적인 구현을 한다.
    /// </summary>
    public class Espresso : Beverage
    {
        //생성자부분에서 description을 설정.
        public Espresso() {
            description = "에스프레소";
        }

        //각 클래스의 기능에 맞는 가격을 설정하여 반환.
        public override float Cost() {
            return 1.99f;
        }
    }
~~~~


### [HouseBlend.cs]

~~~~c#
    public class HouseBlend : Beverage
    {
        public HouseBlend() {
            description = "하우스 블렌드";
        }

        public override float Cost() {
            return .89f;
        }
    }
~~~~


### [DarkRoast.cs]

```c#
    public class DarkRoast : Beverage
    {
        public DarkRoast() {
            description = "다크 로스트";
        }

        public override float Cost() {
            return .99f;
        }
    }
```

---

### [CondimentDecorator.cs]

```c#
    /// <summary>
    /// 첨가물에 들어가는 Decorator 클래스들의 추상클래스
    /// </summary>
    public abstract class CondimentDecorator : Beverage
    {
        //Beverage의 GetDescription()을 override.
        public abstract override string GetDescription();
    }
```



### [Mocha.cs]

```c#
    /// <summary>
    /// ConcreteDecorator - 구체적인 Decorator
    /// </summary>
    public class Mocha : CondimentDecorator
    {
        //자신이 장식할 대상을 알아야하기때문에 Beverage를 가지고 있음.
        Beverage beverage;

        public Mocha(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 모카";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.20f);
        }
    }
```



### [Soy.cs]

```c#
    public class Soy : CondimentDecorator
    {
        Beverage beverage;

        public Soy(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 두유";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.15f);
        }
    }
```



### [Whip.cs]

```c#
    public class Whip : CondimentDecorator
    {
        Beverage beverage;

        public Whip(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 휘핑크림";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.10f);
        }
    }
```



### [SteamMilk.cs]

```c#
    public class SteamMilk : CondimentDecorator
    {
        Beverage beverage;

        public SteamMilk(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 스팀 밀크";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.10f);
        }
    }
```



---

### [StarbuzzCoffee.cs]

~~~~c#
    public class StarbuzzCoffee : MonoBehaviour
    {
        void Start()
        {
            // 첨가를 하지 않음.
            Beverage beverage = new Espresso();

            Debug.Log(beverage.GetDescription() + " - $" + beverage.Cost());

            // 기본 음료에 더블모카, 휘핑크림을 첨가.
            Beverage beverage2 = new DarkRoast();
            beverage2 = new Mocha(beverage2);
            beverage2 = new Mocha(beverage2);
            beverage2 = new Whip(beverage2);

            Debug.Log(beverage2.GetDescription() + " - $" + beverage2.Cost());

            // 기본 음료에 두유, 모카, 휘핑크림을 첨가.
            Beverage beverage3 = new HouseBlend();
            beverage3 = new Soy(beverage3);
            beverage3 = new Mocha(beverage3);
            beverage3 = new Whip(beverage3);

            Debug.Log(beverage3.GetDescription() + " - $" + beverage3.Cost());
        }
~~~~




### [실행 결과]

	에스프레소 - $1.99
	다크 로스트, 모카, 모카, 휘핑크림 - $1.49
	하우스 블렌드, 두유, 모카, 휘핑크림 - $1.34
