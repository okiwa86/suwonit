# Chain Of Responsibility

## 왜쓸까

인터넷을 해지하려고 한다.

헬조선식 해지 방법.

```
고객 -> 콜센터 상담직원 -> 본사영업관리 -> 담당지역 영업부 -> 담당지역 영업부 해지 방지부서 -> 본사 해지 처리부서 -> 전산부서 -> 딥빡(?)
```

이 경로를 고객이 다 알아야 할 필요는 없다.

고객은 맨처음 받은 상담직원한테 '나 해지할거요!' 하고, 해지가 될때까지 기다리면 그만이다.

이렇게 Client 와 Handler(처리하는사람)의 역할을 분리해서,

각각 오브젝트들을 부품처럼 사용하기 위해 사용한다.

## 책임 떠넘기기

Responsibility는 책임이라는 의미이므로 책임을 '떠넘기는 구조'라고 생각하면 편합니다.

이 패턴을 사용하면 '요청하는 쪽'과 '처리하는 쪽'의 연결을 유연하게 해서 각 오브젝트를 **부품으로 독립**시킬 수 있습니다.

또한 상황에 따라서 요청을 처리할 오브젝트가 변하는 프로그램에도 대응할 수 있습니다.

어떤 사람에게 요구를 합니다.그사람이 그것을 처리할 수 있으면, 처리하고, 처리할 수 없으면 그 요구를 **다음 사람**에게 넘깁니다.

다음 사람이 할 수 있으면 하고 없으면, 또 넘깁니다.
이것이 Chain of Responsibility 패턴입니다.

복수의 오브젝트(객체)를 사슬(chain)처럼 연결해 두면,
그 오브젝트(객체)의 사슬을 차례로 돌아다니면서 목적한 오브젝트(객체)를 결정하는 방법

Talk is cheap. Show me the code. -Linus Torvalds
그렇다면 코드로 보자...

### 트러블 class

트러블을 발생 시킬 클래스 입니다.

```java
    public class Trouble {
        private int number; // 트러블 번호

        public Trouble(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "Trouble [number=" + number + "]";
        }
    }
```

### Support 클래스

해결방법등을 정의 할 클래스 입니다.

Abstract Class로 Template패턴을 사용했습니다.

Template패턴은?
> 프로세스 흐름을 abstract 클래스에서 메소드를 사용하고, 프로세스 상세 구현은 자식클래스에서 구현

next필드는 다음 Support클래스를 지정해,
LinkedList 처럼 다음 Support클래스를 불러와서 support(Trouble trouble)메소드를 실행하게 합니다.

```java
    public abstract class Support {
        private String name;
        private Support next;

        public Support(String name) {
            this.name = name;
        }
        // LinkedList 처럼 다음에 누가 올지를 set 해준다.
        public Support setNext(Support next) {
            this.next = next;
            return next;
        }

        public final void support(Trouble trouble) { // 트러블 해결의 수순 (Template패턴)
            if (resolve(trouble)) {
                done(trouble);
            } else if (next != null) {
                next.support(trouble);
            } else {
                fail(trouble);
            }
        }

        protected abstract boolean resolve(Trouble trouble); // 해결용 method

        protected void done(Trouble trouble) { // 해결
            System.out.println("Done! " + trouble + " resolved by " + this);
        }

        protected void fail(Trouble trouble) { // 해결 불가
            System.out.println("Fail  " + trouble);
        }

        @Override
        public String toString() {
            return "[" + name + "]";
        }
    }
```

## Support 구현

resolve 메소드를 override 해 자신만의 해결법을 작성

```java
    public class NoSupport extends Support {
        public NoSupport(String name) {
            super(name);
        }

        @Override
        protected boolean resolve(Trouble trouble) { // 해결 메소드 구현
            return false; // 난 안될거야 아마...
        }
    }


    public class LimitSupport extends Support {
        private int limit;

        public LimitSupport(String name, int limit) {
            super(name);
            this.limit = limit;
        }

        @Override
        protected boolean resolve(Trouble trouble) { // 해결 메소드 구현
            if (trouble.getNumber() < limit) { // limit 숫자보다 작을때만 해결!
                return true;
            } else {
                return false;
            }
        }
    }


    public class OddSupport extends Support {
        public OddSupport(String name) {
            super(name);
        }

        @Override
        protected boolean resolve(Trouble trouble) { // 해결 메소드 구현
            if (trouble.getNumber() % 2 == 1) { // odd 만 해결 한다
                return true;
            } else {
                return false;
            }
        }
    }

    public class SpecialSupport extends Support {

        private int specialNumber;

        public SpecialSupport(String name, int specialNumber) {
            super(name);
            this.specialNumber = specialNumber;
        }

        @Override
        protected boolean resolve(Trouble trouble) { // 해결 메소드 구현
            if (trouble.getNumber() == specialNumber) { // specail Number만 해결
                return true;
            } else {
                return false;
            }
        }
    }
```

## Main 클래스

```java
    public class Main {

        public static void main(String[] args) {
            Support alice = new NoSupport("Alice");
            Support bob = new LimitSupport("Bob", 100);
            Support charlie = new SpecialSupport("Charlie", 429);
            Support diana = new LimitSupport("Diana", 200);
            Support elmo = new OddSupport("Elmo");
            Support fred = new LimitSupport("Fred", 300);

            alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo).setNext(fred);
            // 다양한 트러블 발생
            for(int i = 0; i < 500; i += 33) {
                alice.support(new Trouble(i));
            }
        }
    }

```

## 결과

```
Done! Trouble [number=0] resolved by [Bob]
Done! Trouble [number=33] resolved by [Bob]
Done! Trouble [number=66] resolved by [Bob]
Done! Trouble [number=99] resolved by [Bob]
Done! Trouble [number=132] resolved by [Diana]
Done! Trouble [number=165] resolved by [Diana]
Done! Trouble [number=198] resolved by [Diana]
Done! Trouble [number=231] resolved by [Elmo]
Done! Trouble [number=264] resolved by [Fred]
Done! Trouble [number=297] resolved by [Elmo]
Fail  Trouble [number=330]
Done! Trouble [number=363] resolved by [Elmo]
Fail  Trouble [number=396]
Done! Trouble [number=429] resolved by [Charlie]
Fail  Trouble [number=462]
Done! Trouble [number=495] resolved by [Elmo]
```

## 등장인물

### Handler(처리자)의 역할

요구를 처리하는 인터페이스(API)를 결정하는 역할을 합니다.

다음사람을 준비해두고,

자신이 처리할 수 없는 요구가 나오면 떠넘기기를 합니다.

예제 : Support

### ConcreteHandler(구체적인 처리자)의 역할

예제 : NoSupport, LimitSupport, OddSupport, SpecialSupport

### Client(요구자)의 역할

ConcreteHandler에게 요구하는 역할입니다.
예제 : Main

## 독자의 사고를 넓히기 위한 힌트

### 요구하는 사람과 요구를 처리하는 사람을 유연하게 연결한다

이 패턴의 핵심은 Client와 ConcreteHandler들을 유연하게 연결하는 것 입니다.

Client는 최초사람에게 요구를 합니다.

그러면 뒷일은 적절한 처리자에게 전달되서 원하는 정보를 return 받습니다.

만약 이패턴을 사용하지 않는다면, 각 항목에 대한 처리를 누가해야하는지 중앙집권적으로 가지고 있어야 합니다.

그 정보를 Client가 갖게하는것은 별로 현명하지 않습니다.

요구를 하는 사람들이 처리자의 역할분담에 대해서까지 자세히 알아야 하는것은 부품으로써의 독립성이 훼손됩니다.

```
요약 : 인터넷 해지를 위해 전화 한 사람이, 해지해주세요! 하면,

인터넷 업체는 알아서 해지부서에 연락해서 해지 처리하고 정보를 주면 된다.
```

*예제는 단순화를 위해서 Main에 사슬형성을 맡겼지만, 사슬형성은 요구하는 사람이 갖는게 아닙니다.*

### 동적으로 사슬의 형태를 바꾼다

예제에선 Alice 부터 Fred까지, 고정된 순서로 되어 있습니다.

예를들어 패턴 1,2,3 에 따라 처리순서가 바뀌어야 한다고 가정하면,

중앙집권적으로 처리하는 것보다 쉽게, 동적으로 변경이 가능하게 됩니다.

### 자신의 일에 집중할 수 있다

'떠넘기기'는 부정적인 의미가 강하지만,
반대로 각 오브젝트가 '자신의 일에 집중할 수 있다'는 의미이기도 합니다.

Chain Of Responsibility 패턴을 사용하지 않을땐 '위대한 사람 한 명이 누가 요구를 처리할지 전부 결정' 하는 방법으로 합니다.

또는 '자신이 처리할 수 없으면 다른사람에게 맡기고, 만약 그래도 처리할 수 없으면 시스템 상황에 따라서 적절한 사람' 에게 처리를 맡기는 '일의 분담'까지 각각의 ConcreteHandler 역할에게 부담시켜야 합니다.

### 떠넘기기로 처리가 지연되지 않을까

맞습니다. 이건 트레이드 오프의 문제입니다.

요구와 처리자의 관계가 고정적이고 처리속도가 상당히 중요한 경우에는 사용하지 않는 편이 유효한 case도 있습니다.