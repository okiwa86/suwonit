# State 패턴

![UML](https://upload.wikimedia.org/wikipedia/commons/e/ec/W3sDesign_State_Design_Pattern_UML.jpg)

## 왜쓸까

맥도날드 상태값을 나눈다고 생각하면 보통 아래처럼 나눌 것이다.

```java
    if(주간){
        // 평상시 동작
    }else if(런치) {
        // 런치 동작
    }
```

예를들어 method 가 20개 있다고 가정하자.

```java
    빅맥
        if(주간){

        } else if(런치){

        }
    베이컨토마토디럭스
        if(주간){

        } else if(런치){

        }
```

예제로 치기도 힘드니까...대충 20개 있다고 가정하고..

여기에 갑자기...모닝 시간 동작이 추가 된다고 하면...

else if 를 20개 넣고...그안에서 또 다른 분기가 탄다고 가정하면 눈이 빠질것이다.

OOP에서 오브젝트를 사물에 기대어 사용하기도 하지만,

이 패턴에서처럼 상태에 대해서도 Class를 만들어 사용해 볼수 있다.

## 예제

예제에선 찾아본 패턴들 중에 가장 이해하기 쉽고 정석적으로 설명해 놓은 코드를 적어 보았다.

천장에달린 선풍기 예제

당길때 마다 상태가 변하는 걸 코드로 표현했다.

### Before.java

```java
// Not good: unwieldy "case" statement
    class CeilingFanPullChain {
        private int currentState;

        public CeilingFanPullChain() {
            currentState = 0;
        }

        public void pull() {
            if (currentState == 0) {
                currentState = 1;
                System.out.println("fan : LOW SPEED");
            } else if (currentState == 1) {
                currentState = 2;
                System.out.println("fan : MID SPEED");
            } else if (currentState == 2) {
                currentState = 3;
                System.out.println("fan : HIGH SPEED");
            } else {
                currentState = 0;
                System.out.println("fan : OFF SPEED");
            }
        }
    }

    public class Before {

        public static void main(String[] args) {
            CeilingFanPullChain chain = new CeilingFanPullChain();
            chain.pull();
            chain.pull();
            chain.pull();
            chain.pull();
        }
    }
    /* Result
    * fan : LOW SPEED
    * fan : MID SPEED
    * fan : HIGH SPEED
    * fan : OFF SPEED
    */
```

### After.java

```java
    /*
    The CeilingFanPullChain class is now a wrapper that delegates to its m_current_state reference.
    Each clause from the "before" case statement is now captured in a State derived class.
    For this simple domain, the State pattern is probably over-kill.
    */
    interface State {
        abstract void pull(CeilingFanPullChainAfter wrapper);
    }

    class OffState implements State {
        @Override
        public void pull(CeilingFanPullChainAfter wrapper) {
            wrapper.currentState = new LowState();
            System.out.println("fan : OFF SPEED");
        }
    }

    class LowState implements State {
        @Override
        public void pull(CeilingFanPullChainAfter wrapper) {
            wrapper.currentState = new MidState();
            System.out.println("fan : LOW SPEED");
        }
    }

    class MidState implements State {
        @Override
        public void pull(CeilingFanPullChainAfter wrapper) {
            wrapper.currentState = new HighState();
            System.out.println("fan : MID SPEED");
        }
    }

    class HighState implements State {
        @Override
        public void pull(CeilingFanPullChainAfter wrapper) {
            wrapper.currentState = new OffState();
            System.out.println("fan : HIGH SPEED");
        }
    }

    class CeilingFanPullChainAfter {
        public State currentState;

        CeilingFanPullChainAfter() {
            this.currentState = new OffState();
        }

        void pull() {
            currentState.pull(this);
        }
    }

    public class After {
        public static void main(String[] args) {
            CeilingFanPullChainAfter chain = new CeilingFanPullChainAfter();
            chain.pull();
            chain.pull();
            chain.pull();
            chain.pull();
        }
    }

    /* Result
    * fan : LOW SPEED
    * fan : MID SPEED
    * fan : HIGH SPEED
    * fan : OFF SPEED
    */

```

코드를 보면 너무 단순한 예제를 사용하다 보니

왜 저고생을 하나? 생각 할 수도 있지만,

코드는 시간이 지날수록 복잡도가 증가하게 되고, 새로운 상태를 항상 준비해야 한다.

확장에 용이 하도록 미리 설계해 두는게 이 패턴의 목적!

## 책에서 설명하는 생각해보기

### 분할해서 통치해라(divide and conquer)

분할해서 통치해라(divide and conquer) 는 복잡하고 규모가 큰 프로그램을 취급할 경우의 방침입니다.

규모가 크고 복잡한 문제는 그대로 해결하려고 해서는 안됩니다.

문제를 작은 단위로 나누고, 그래도 해결이 힘들면 더 작게 나눕니다.

State패턴은 각각의 상태를 클래스로 구현해 문제를 분할합니다.

하나의 ConcreteState 역할의 클래스를 코딩하고 있으면 프로그래머는 다른 클래스에 대한 생각을 잠시(어느 정도) 잊을 수 있습니다.(?!)

### 상태에 의존한 처리

chain.pull();을하면, chain은 Context안에 있는 method지만,

Context안의 pull을 들여다보면,State.pull() 로 위임하고 있습니다.

### 상태전환은 누가 관리해야 하는가?

상태클래스를 표현해서 상태에 의존한 동작을 각각의 ConcreteState 역할에 분담시키는 방법은 매우 좋은 방법입니다.

그러나, State패턴을 사용할 경우 상태전환은 누가 관리해야 하는 지는 주의해야 합니다.

예제 프로그램에선 pull() 메소드를 실행하면,
ConcreteState에서 Context의 state를 다음 state로 변경해줍니다.

상태전환을 상태에 의존한 동작으로 간주하고 있습니다.

이방법은 장점과 단점이 있습니다.

장점은 다른상태로 전환하는것은 언제하는것인가 하는 정보가 하나의 클래스 내에 정리되어 있는 점 입니다.

즉, Mid클래스가 다른상태로 어떤걸 전환하는지 알려면,
Mid클래스를 열어보면 됩니다.

단점은 하나의 ConcreteState 역할이 다른 ConcreteState 역할을 알아야 한다는 점입니다.(다른 Concrete 클래스에 의존성이 생김.)

예제 프로그램과 같은 방법을 포기하고,
Context가 모든 상태전환을 맡을 수도 있습니다.

그렇게 하면 각각의 ConcreteState 역할의 독립성이 높아져서 프로그램 전체의 예측이 좋아지는 경우가 있습니다.

반면, Context역할이 모든 ConcreteState역할을 알아야 하는 문제가 발생합니다.

여기에 Mediator 패턴을 적용할 수 있을지도 모릅니다.

### 자기모순에 빠지지 않는다.

State패턴을 사용하지 않고 공통변수로 담아서 사용한다고 할때,
중간에 값이 바뀌지 않는 경우가 생기면, 엉뚱한 값을 들고 있을 수 있습니다.

ex) 모닝 상태인데, 런치 메뉴를 팔고 있다 던지...

### 새로운 상태를 추가하는 것은 간단하다.

ConcreteClass를 추가하면 되고, interface에 메소드가 강제되니까, 항상 같은 method를 사용합니다.