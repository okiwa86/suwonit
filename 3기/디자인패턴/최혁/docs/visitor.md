# Visitor 패턴

![UML](https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Visitor_design_pattern.svg/500px-Visitor_design_pattern.svg.png)

## 왜쓸까

Visitor 패턴의 목적은 처리를 데이터 구조에서 분리하는 일입니다.

(아직 이해 불가...

Elements가 모든일을 수행하는게 아니라, 처리부분을 방문자에게 떠넘겨서

방문자가 확장이 유리하도록 유도하는듯한데... 다른 예제들을 좀 보자...)

## 알아두면 쓸만한 잡다한 지식사전

### 더블 디스패치(이중분리)

```java
Element.accept(Visitor v)
Visitor.visit(Element e)
```

서로 호출함으로써 재귀처럼 사용.

### The Open-Closed Principle(OCP)

Bertrand Meyer가 제시한 것으로 Robert C.Martin이 C++ Report(Jan. 1996)에 쓴 Engineering Notebook 이라는 컬럼에 정리되어 있습니다.

<https://www.cs.duke.edu/courses/fall07/cps108/papers/ocp.pdf>

<http://stg-tud.github.io/sedc/Lecture/ss15/3.3-OCP.pdf>

<https://ko.wikipedia.org/wiki/%EA%B0%9C%EB%B0%A9-%ED%8F%90%EC%87%84_%EC%9B%90%EC%B9%99>

기존의 클래스를 수정하지 않고 확장할 수 있도록 하는 것이 원칙!

확장은 열려 있고, 수정은 닫혀있다!
클래스를 설계할때 특별한이유가 없는 한 확장을 허용해야 합니다.
이유없이 확장을 금지해서는 안되며, 이것이 '확장에 대해서는 열려있다' 는 의미입니다.

그러나 확장을 할 때마다 기존의 클래스를 수정해야 하는 것도 곤란합니다.
확장을 해도 기존의 클래스는 수정할 필요가 없는 것이 '수정에 대해서는 닫혀있다'라는 의미입니다.

### ConcreteVisitor 역할의 추가는 간단하다

### ConcreteElement 역할의 추가는 어렵다

### Visitor가 처리하기 위해서 무엇이 필요한가

데이터 구조의 요소에 대한 처리를 따로 분리해서 Visitor역할에게 맡깁니다.

이렇게 해서 데이터구조와 요소에 대한 처리를 분리할 수 있습니다.

이것은 그럴듯한 말이지만 Element 역할은 Visitor 역할에 대해서 충분한 정보를 공개할 필요가 있습니다.

예제에선 iterator 메소드를 제공할 필요가 있었습니다.

방문자는 데이터구조에 서 필요한 정보를 취득해서 동작합니다.

필요한 정보를 얻을 수 없으면 방문자는 제대로 일할 수 없습니다.

반면에 공개할 필요 없는 정보까지 공개하면, 미래의 데이터 구조를 개량하기 어렵게 됩니다.
