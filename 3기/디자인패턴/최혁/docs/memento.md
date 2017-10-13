# Memento 패턴
- [Memento 패턴](#memento-%ED%8C%A8%ED%84%B4)
    - [왜쓸까](#%EC%99%9C%EC%93%B8%EA%B9%8C)
    - [등장인물](#%EB%93%B1%EC%9E%A5%EC%9D%B8%EB%AC%BC)
        - [Originator(작성자)의 역할](#originator%EC%9E%91%EC%84%B1%EC%9E%90%EC%9D%98-%EC%97%AD%ED%95%A0)
        - [Memento(기억)의 역할](#memento%EA%B8%B0%EC%96%B5%EC%9D%98-%EC%97%AD%ED%95%A0)
        - [Caretaker(관리인)의 역할](#caretaker%EA%B4%80%EB%A6%AC%EC%9D%B8%EC%9D%98-%EC%97%AD%ED%95%A0)
    - [예제 코드](#%EC%98%88%EC%A0%9C-%EC%BD%94%EB%93%9C)
    - [독자의 사고를 넓히기 위한 힌트](#%EB%8F%85%EC%9E%90%EC%9D%98-%EC%82%AC%EA%B3%A0%EB%A5%BC-%EB%84%93%ED%9E%88%EA%B8%B0-%EC%9C%84%ED%95%9C-%ED%9E%8C%ED%8A%B8)
        - [Memento 클래스에서 사용되고 있는 액세스 제어](#memento-%ED%81%B4%EB%9E%98%EC%8A%A4%EC%97%90%EC%84%9C-%EC%82%AC%EC%9A%A9%EB%90%98%EA%B3%A0-%EC%9E%88%EB%8A%94-%EC%95%A1%EC%84%B8%EC%8A%A4-%EC%A0%9C%EC%96%B4)
        - [Memento를 몇 개 가질까?](#memento%EB%A5%BC-%EB%AA%87-%EA%B0%9C-%EA%B0%80%EC%A7%88%EA%B9%8C)
        - [Memento의 유효기간은?](#memento%EC%9D%98-%EC%9C%A0%ED%9A%A8%EA%B8%B0%EA%B0%84%EC%9D%80)
        - [Caretaker 역할과 Originator 역할을 분리하는 이유](#caretaker-%EC%97%AD%ED%95%A0%EA%B3%BC-originator-%EC%97%AD%ED%95%A0%EC%9D%84-%EB%B6%84%EB%A6%AC%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0)

![UML](https://upload.wikimedia.org/wikipedia/commons/3/38/W3sDesign_Memento_Design_Pattern_UML.jpg)
## 왜쓸까

저장한 정보를 가지고 RollBack(undo) 하는 패턴

RollBack을 하려면?
1. 기존 인스턴스 정보를 저장
1. 인스턴스 내부에 접근해 기존 정보를 꽂아줘야 함.

이렇게 내부의 정보를 자유롭게 액세스 하다보면, 원하지 않는 액세스를 허용해야 할 수도 있다.
이것을 `캡슈화의 파괴`라고 합니다.

인스턴스의 상태를 나타내는 역할을 도입해서, 캡슐화의 파괴에 빠지지 않고, 저장과 복원을 실행하는 것이 Memento 패턴의 목적입니다.

## 등장인물

### Originator(작성자)의 역할

Originator는 Memento를 만들고, 자기를 과거 Memento로 돌리는 처리를 실행합니다.
예제 : Gamer

### Memento(기억)의 역할

Memento의 역할은 Originator의 내부정보를 기억합니다.

정보를 누구에게도 공개하지 않습니다.

Memento역할은 두가지 인터페이스를 가집니다.

1. wide interface

    오브젝트의 상태를 원래상태로 돌리기 위해 필요한 정보를 모두 얻을 수 잇는 메소드의 집합.

    Memento 역할의 내부 상태를 속속들이 들어내기 때문에 이것을 사용하는 것은 Originator 역할뿐입니다.

1. narrow interface

    외부의 Carelaker 역할에게 보여주는 것

    이 interface 로 할 수 있는일에 한계를 두고, 내부상태가 외부에 공개되는것을 방지 합니다.

### Caretaker(관리인)의 역할

Caretaker역할은 Originator에게 Memento를 지금 저장해서 달라고 요청합니다.

그렇게 받은 Memento를 관리합니다. (예제는 필드 1개지만, Collection으로 관리 가능)

하지만 Memento 내부엔 접근할 수 있는건 최소한의 narrow interface부분들 뿐입니다.

필요할때 꺼내서, Originator에게 전달해 복원하도록 합니다.

## 예제 코드

```java
    package game;

    import java.util.ArrayList;

    public class Memento {
        int money; // 소지금
        ArrayList<String> fruits; // 과일

        Memento(int money) { // 생성자
            this.money = money;
            this.fruits = new ArrayList<String>();
        }

        public int getMoney() { // 소지금을 얻는다. (얘만 public - main 조회용)
            return money;
        }

        ArrayList<String> getFruit() {
            return fruits;
        }

        void addFruit(String fruit) {
            this.fruits.add(fruit);
        }
    }

```

```java
    package game;

    import java.util.*;

    public class Gamer {
        private int money;
        private List<String> fruits = new ArrayList<String>();
        private Random random = new Random();
        private static String[] fruitsname = { "사과", "포도", "바나나", "귤" };

        public Gamer(int money) {
            this.money = money;
        }

        public int getMoney() {
            return money;
        }

        public void bet() {
            int dice = random.nextInt(6) + 1; //주사위 던지기
            if (dice == 1) {
                money += 100;
                System.out.println("소지금이 증가했다.");
            } else if (dice == 2) {
                money /= 2;
                System.out.println("소지금이 절반이 되었다.");
            } else if (dice == 6) {
                String f = getFruit();
                System.out.println("과일(" + f + ")를 받았다.");
                fruits.add(f);
            } else {
                System.out.println("pass");
            }
        }

        public Memento createMemento() { // memento 저장
            Memento m = new Memento(money);
            Iterator<String> it = fruits.iterator();
            while (it.hasNext()) {
                String f = it.next();
                String prefix = "맛있는 "; // 맛있는것만 저장
                if (f.startsWith(prefix)) {
                    m.addFruit(f);
                }
            }
            return m;
        }

        public void restoreMemento(Memento memento) { // 기존 memento에서 복구
            this.money = memento.money;
            this.fruits = memento.getFruit();
        }

        private String getFruit() {
            String prefix = "";
            if (random.nextBoolean()) {
                prefix = "맛있는 ";
            }
            return prefix + fruitsname[random.nextInt(fruitsname.length)];
        }

        @Override
        public String toString() {
            return "Gamer [money=" + money + ", fruits=" + fruits + "]";
        }

    }

```

```java
    import game.*;

    public class Main {
        public static void main(String[] args) {
            int money = 100;
            Gamer gamer = new Gamer(money);
            Memento memento = gamer.createMemento();

            for (int i = 0; i < 50; i++) {
                System.out.println("=== "+ i);
                System.out.println("현재상태 : "+ gamer);

                gamer.bet(); // 게임을 시작하지..

                System.out.println("소지금은 " + gamer.getMoney() + "원");

                if(gamer.getMoney() > memento.getMoney()) {
                    System.out.println("많이 증가했다. 저장한다.");
                    memento = gamer.createMemento();
                } else if (gamer.getMoney() < memento.getMoney() / 2) {
                    System.out.println("많이 감소했다. 복원한다.");
                    gamer.restoreMemento(memento);
                }
            }
            System.out.println("게임오버 : "+ gamer);
        }
    }

        /* 결과
        === 45
        현재상태 : Gamer [money=500, fruits=[맛있는 바나나, 맛있는 포도, 포도]]
        과일(맛있는 포도)를 받았다.
        소지금은 500원
        === 46
        현재상태 : Gamer [money=500, fruits=[맛있는 바나나, 맛있는 포도, 포도, 맛있는 포도]]
        소지금이 절반이 되었다.
        소지금은 250원
        많이 감소했다. 복원한다.
        === 47
        현재상태 : Gamer [money=800, fruits=[맛있는 바나나, 맛있는 포도]]
        pass
        소지금은 800원
        === 48
        현재상태 : Gamer [money=800, fruits=[맛있는 바나나, 맛있는 포도]]
        소지금이 증가했다.
        소지금은 900원
        많이 증가했다. 저장한다.
        === 49
        현재상태 : Gamer [money=900, fruits=[맛있는 바나나, 맛있는 포도]]
        pass
        소지금은 900원
        게임오버 : Gamer [money=900, fruits=[맛있는 바나나, 맛있는 포도]]

        */

```

## 독자의 사고를 넓히기 위한 힌트

### Memento 클래스에서 사용되고 있는 액세스 제어

| 액세스제어  | 필드 메소드 생성자 | 누구에게 보이지?            |
| ------ | ---------- | -------------------- |
| 없음     | money      | Memento, Gamer       |
| 없음     | fruits     | Memento, Gamer       |
| public | getMoney   | Memento, Gamer, Main |
| 없음     | Memento    | Memento, Gamer       |
| 없음     | addFruit   | Memento, Gamer       |

getMoney 메소드에만 public 을 붙여서 narrow interface로 사용하고 있습니다.

Main클래스와 패키지가 달라 접근이 불가능.

### Memento를 몇 개 가질까?

예제는 Main클래스가 갖고 있는 1개지만, 배열들을 사용해 여러 인스턴스의 상태를 저장할 수 있습니다.

### Memento의 유효기간은?

예제같이 메모리상에만 저장할 경우 별로 문제가 없지만, 파일로 지속적으로 저장할 경우, 유효기간이 문제가 될 수 있습니다.

버전업등으로 구조가 바뀐다던지. 등

### Caretaker 역할과 Originator 역할을 분리하는 이유

스냅샷을찍고, Memento 관리를 Originator가 직접 다 하면 안될까?

이렇게 역할 분담을 해두면,

1. 여러 단계의 undo를 실행하도록 변경하고 싶다.
1. undo 기능뿐만 아니라 현재의 상태를 파일에 저장하고싶다. 등등

여러 수정사항이 생길때마다, Originator를 수정하지 않고, Caretaker만 수정해 사용이 가능합니다.