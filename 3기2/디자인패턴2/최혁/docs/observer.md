# Observer 패턴

- [Observer 패턴](#observer-%ED%8C%A8%ED%84%B4)
    - [예제소스](#%EC%98%88%EC%A0%9C%EC%86%8C%EC%8A%A4)
    - [왜쓸까](#%EC%99%9C%EC%93%B8%EA%B9%8C)

옵저버 패턴은 두가지 오브젝트가 필요합니다.

1. 관찰대상 Object(Subject)

1. 관찰자 Object(Observer)

Subject는, 누가 관찰하고 있는지를 저장하고 있고, 그 대상들에게 자신이 변경 됬다라는걸 알립니다.(notify)

Observer는 `notify`를 받았을때, 각자 어떻게 수행할지 가지고 있다가 수행하게 됩니다.

![클래스다이어그램](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Observer.svg/500px-Observer.svg.png)

## 예제소스

```python
"""옵저버 패턴 모듈"""
    import random


    class Subject:
        """Subject 클래스"""

        def __init__(self):
            self.__observer_collection = []  # private variable

        def register_observer(self, observer):
            """옵저버를 Subject의 observer_collection에 저장"""
            self.__observer_collection.append(observer)

        def notify_observers(self, num):
            """observer_collection에서 observer를 꺼내서 notify"""
            for observer in self.__observer_collection:
                observer.notify(num)


    class RandomNumberSubject(Subject):
        """Subject 구현 클래스"""

        def random_number_create(self):
            """랜덤 숫자를 넣어서 알림"""
            rand_num = random.randrange(1, 7)
            self.notify_observers(rand_num)


    class OddNumberSubject(Subject):
        """Subject 구현 클래스"""

        def odd_number_create(self):
            """홀수 숫자 알림"""
            for num in range(1, 5):
                if num % 2 != 0:
                    self.notify_observers(num)


    class Observer:
        """옵저버 클래스"""

        def __init__(self, subject):
            subject.register_observer(self)

        def notify(self, num):
            """
            abstract method
            각자 notify 받을때 실행할 method
            """
            raise NotImplementedError


    class DecimalObserver(Observer):
        """10진수 옵저버"""

        def notify(self, num):
            print("Decimal value = %s" % num)


    class HexObserver(Observer):  # 상속(Observer)
        """16진수 옵저버"""

        def notify(self, num):
            print("Hex value = %s" % hex(num))


    class BinaryObserver(Observer):
        """2진수 옵저버"""

        def notify(self, num):
            print("Binary value = %s" % bin(num))


    subject1 = RandomNumberSubject()
    observer1 = DecimalObserver(subject1)
    observer2 = HexObserver(subject1)
    observer3 = BinaryObserver(subject1)

    subject2 = OddNumberSubject()
    observer4 = DecimalObserver(subject2)

    subject1.random_number_create()
    subject2.odd_number_create()

    """
    실행결과
    Decimal value = 6
    Hex value = 0x6
    Binary value = 0b110

    Decimal value = 1
    Decimal value = 3
    """
```


## 왜쓸까

디자인패턴의 목적 중 하나는, 클래스를 재이용 가능한 부품으로 만드는 일입니다.

Concrete Subject는 누가 관찰하는지 까지는 관심 없습니다.
observer_collection에 있는 observer들이 notify 메소드를 가지고 있다는 것만 알고 있으면, 됩니다.

Concrete Observer도 어떤 Subject를 관찰하고 있는지는 알 수 없습니다.
Subject의 하위 클래스라는것만 알고, notify에 int 값만 들어오면 됩니다.

다른 패턴들과 마찬가지로,
1. 추상클래스나 인터페이스를 사용해서 구상 클래스로부터 추상 메소드를 분리한다.
1. 인수로 인스턴스를 전달할 때, 필드에서 인스턴스를 저장할 때에는 구상클래스의 형태로 하지 않고, 추상클래스나 인터페이스의 형태로 둔다

위와 같이 해두면, 구상 클래스의 부분을 쉽게 교환 가능합니다.


주의사항

무한루프에 빠질 우려를 주의 해야합니다.(flag를 두는것도 방법)

ex) Subject 상태변화 -> Observer에게 전달 -> Observer가 Subject의 메소드를 호출 -> 이로인해 Subject 상태 변화 -> Observer에게 전달 .... 무한루프

옵저버 패턴은 관찰하기보다, 전달받기를 기다립니다.

능동적으로 Observer가 관찰하는 것이 아니고, Subject 역할로 부터 전달되기를 수동적으로 기다립니다.

그래서 옵저버 패턴을 Publish - Subscribe 패턴이라고도 합니다.
