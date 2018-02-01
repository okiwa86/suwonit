# Prototype 패턴

## 이해한 내용
왜쓸까...? 

클래스로부터 인스턴스 생성이 어려운 경우...
```java
// ex 1000개의 파라미터를 가진 오브젝트라고 가정;;
WTF obj = new WTF(1,2,3,4,4,5,6,7,,3,3,5,5,,3,1)

// 이런 말도 안되는 생성자가 필요한 복잡한 코드라고 치자...
// 503번째 파라미터에 값만 바꾸어서 새로운 instance로 생성한다고 할때,
// 아래처럼 일일이 쳐서 파라미터를 하나 바꾸기보다는...
WTF obj2 = new WTF(1,2,3,4,4,5,6,7,,3,3,5,5,,3,1)

// 아래처럼 간단하게 쓰자...
WTF protoTypeObj = new WTF(1,2,3,4,4,5,6,7,,3,3,5,5,,3,1)
WTF obj2 = protoTypeObj.clone();
obj2.setParam503("1");
WTF obj3 = protoTypeObj.clone()
obj3.setParam503("2")
```

클래스를 생성하는데 비용이 상당한 경우...

클래스를 새로 생성하는데 수많은 DB콜을 하여 계산되어지는 클래스라고 가정해보자.

새로운 클래스를 생성하기 위해 DB콜과 계산에대한 부담보다,

간단히 Clone하여 값을 수정하여 사용하거나 한다면, 성능에서 이득을 취할 수 있다.



## 사용하려면
Java에선 Cloneable interface를 implements 해서 사용한다.

CloneNotSupportedException 는 Cloneable 이 implements되지 않을때 발생
```java
public class Shape implements Cloneable {
   
   String field1;
   String field2;
   
   public Object clone() {
      Object clone = null;
      try {
         clone = super.clone();
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      return clone;
   }
}
```