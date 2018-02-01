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
