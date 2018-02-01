# Adaptor 패턴

프로그래밍을 진행하다보면 **이미 제공되어 있는 것**과 **필요한 것** 사이에서

**차이** 가 발생하게 되는데 이때 이 '차이'를 없애주는 것이 **Adapter 패턴**이다. 

_(Adapter 패턴은 **Wrapper 패턴**으로 불리기도 한다.)_

Adapter 패턴에는 다음과 같은 두 가지 종류가 있다.

```
클래스에 의한 Adapter 패턴(상속을 사용한 Adapter 패턴)

인스턴스에 의한 Adapter 패턴(위임을 사용한 Adapter 패턴)
```

예제프로그램은 주어진 문자열을 아래와 같이 표시하는 간단한 것이다.
```
(Hello)
*Hello*
```

## 예제프로그램 (1)  - 상속을 사용한 Adapter 패턴 -

### Banner (Adaptee:어댑터가 변환해줄 클래스)

미리제공된 클래스라고 가정
```java
//API
public class Banner {
    private String string;
    
    public Banner(String string){
        this.string = string;
    }
    public void showWithParen(){
        System.out.println("("+string+")");
    }
    public void showWithAster(){
        System.out.println("*"+string+"*");
    }
}
```

### Print

사용자가 필요로 하는 기능 interface 선언
```java
public interface Print{
    public abstract void printWeak();
    public abstract void printStrong();
}
```

### Main 호출
```java
public class Main {
    Public static void main(Stringp[] args) {
        //PrintAdapter가 banner를 상속 받아 super("Hello")로 구현
        Print p = new PrintAdapter("Hello");
        p.printWeak();
        p.printStrong();
    }
}
```

### PrintAdapter 상속Style (Adapter)
어댑터 역할클래스 
```java
//교환장치(Adapter)
public class PrintAdapter extends Banner implements Print{
    //생성자
    //PrintAdapter가 banner를 상속 받아 super("Hello")로 구현
	public PrintAdapter(String string){
		super(string);
	}
	public void printWeak(){
		showWithParen();
	}
	public void printStrong(){
		showWithAster();
	}
}
```
### 위임Style
```java
public abstract class Print{ // interface -> abstract class
    public abstract void printWeak();
    public abstract void printStrong();
}
public class PrintAdapter extends Print{
    private Banner banner; //!!!
    //상속style:PrintAdapter가 Banner를 상속 받아 super("Hello")로 구현
    //위임style:PrintAdapter가 Print를 상속 받아 필드에 Banner생성
	public PrintAdapter(String string){
        this.banner = new Banenr(string);
	}
	public void printWeak(){
		banner.showWithParen();
	}
	public void printStrong(){
		banner.showWithAster();
	}
}
```

## Adapter 패턴의 등장인물


**Target(대상)** 의 역할 지금 필요한 메소드를 결정합니다.

예제에서는 **(1)Print 인터페이스**나, **(2)Print abstract클래스**에 해당됩니다


**Client(의뢰자)** 의 역할 Target역할의 메소드를 사용해서 일을 처리합니다. 

예제에서는 **Main 클래스** 가 해당됩니다.

**Adaptee(개조되는 쪽)** 의 역할 이미 준비되어 있는 메소드를 가지고 있는 역할입니다. 

예제에서는 **Banner 클래스**에 해당합니다. Target과 Adaptee의 역할이 일치할경우에는 Adapter가 필요 없습니다.

## 왜 쓸까?

다음 지도 API를 사용하는 앱을 구글 지도 API소스를 추가해서 둘 다 사용한다고 할때,호출하는 메소드 이름이 각각 다를것이다.

기존 메소드를 사용하고 있는 부분을 최소한으로 수정하면서 적용하고자 한다면

공통적으로 구현하고자 하는 method를 interface에 정의해 adapter를 이용해 한가지 메소드 호출로 이용할 수 있다.

추후에 네이버 지도 API를 추가 한다면 기존 interface를 이용해 더욱 깔끔하게 사용 가능하다.



## 연습문제
```java
Print p = new PrintAdapter("Hello"); //예제

PrintAdapter p = new PrintAdapter("Hello"); // 왜 이렇게 안했을까?
p.printWeak();
p.printStrong();
```
물론 돌아간다.

Print Interface에선언된 내용만 사용할 수 있기때문에, 좀더 정확한 명세가 가능하다.

## 나만의 Adaptor Example

### Ver1.0 
다음Map API를 사용하고 있다고 가정


### DaumMap.java
```java
public class DaumMap {
  String mapName = "다음맵";

  public String drawMap() {
    return mapName;
  }
  public String moveToSuwon() {
    return "수원";
  }
  public String moveToSeoul() {
    return "서울";
  }
}
```

### Client.java 사용자
```java
public class Client {
  public static void main(String[] args) {
    MapApi map = new DaumMap();
    map.drawMap();
    map.moveToSuwon();
    map.moveToSeoul()
   }
```

### Ver 2.0
새로운 기능 추가로 Google API도 추가로 사용하기로 결정했다고 가정

Adaptor = interface

Adaptee = GoogleMap

### MapApi (Adaptor)
```java
public interface MapApi {
  //GoogleMap 에서 어댑터패턴이 적용될 메소드
  String drawMap();
  String moveToSuwon();
  String moveToSeoul();
}
```

### GoogleMap.java (Adaptee)
```java
public class GoogleMap implements MapApi{
  String ver = "GoogleMap 1.0";
  String location = "CA";

  public String version() {
    return ver;
  }
  public String thisLocation() {
    return location;
  }
  public void moveTo(double lat, double lng) {
    //이동했다.
  }
  public String darkTheme() {
    return "DarkTheme-Apply";
  }
  @Override
  public String drawMap() {
    return this.version();
  }
  @Override
  public String moveToSuwon() {
    moveTo( 37.263363,127.028625 );
    return "수원 (37.263363,127.028625)";
  }
  @Override
  public String moveToSeoul() {
    moveTo( 37.564374,126.975586 );
    return "서울 ( 37.564374,126.975586 )";
  }
}
```



### DaumMap.java
```java
public class DaumMap implements MapApi {
  String mapName = "다음맵";

  public String drawMap() {
    return mapName;
  }
  public String moveToSuwon() {
    return "수원";
  }
  public String moveToSeoul() {
    return "서울";
  }
}
```

### Client.java 사용자 Ver2.0
```java
public class Client {
  public static void main(String[] args) {
    MapApi map = new DaumMap();
    map.drawMap();
    map.moveToSuwon();
    map.moveToSeoul();

    MapApi googleMap = new GoogleMap();
    map.drawMap();
    map.moveToSuwon();
    map.moveToSeoul();
    //map.darkTheme(); // error:The method darkTheme() is undefined for the type MapApi

    ((GoogleMap) googleMap).darkTheme(); // OK! MapApi에 없는 기능을 쓰려면 다운캐스팅
}
```