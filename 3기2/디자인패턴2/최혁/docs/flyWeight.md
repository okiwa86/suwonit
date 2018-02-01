# Flyweight 패턴

![UML](https://upload.wikimedia.org/wikipedia/commons/4/4e/W3sDesign_Flyweight_Design_Pattern_UML.jpg)


## 왜쓸까

플라이웨잇 복싱에서 가벼운 체급.

(미니멈급), 플라이급, 밴텀급, 페더급, 라이트급, 미들급, 헤비급

암튼 가볍다.

이 디자인패턴은 오브젝트를 가볍게 하기 위한것 입니다.
여기서 가볍다는, 메모리 사용량을 의미합니다.

Java에서는 new를 많이하면, 메모리의 사용량이 커집니다.

Flyweight 패턴을 한마디로 말하면,
`인스턴스를 가능한 대로 공유시켜서 쓸데 없이 new하지 않도록 한다`

인스턴스가 필요할때 항상 new 하는것이 아니라 이미 만들어져 있는 인스턴스를 사용해도 될때, 재사용해서, 아껴쓰고 나눠쓰고..받아쓰고 다시쓰...

## 예제

예제에선 각 숫자를 표시하는 txt파일이 있습니다.

### big0.text

```txt
....######......
..##......##....
..##......##....
..##......##....
..##......##....
..##......##....
....######......
................
```

### big1.txt

```txt
......##........
..######........
......##........
......##........
......##........
......##........
..##########....
................
```

| 역할               | 이름             | 해설                              |
| ---------------- | -------------- | ------------------------------- |
| FlyWeight        | BigChar        | big0.txt를 나타내는 클래스              |
| FlyweightFactory | BigCharFactory | BigChar의 인스턴스를 공유하면서 생성하는 클래스   |
| Client           | BigString      | BigChar를 모아서 만든 큰 문자열을 나타내는 클래스 |
|                  | Main           | 동작 테스트용 클래스                     |

### BigChar.java

```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;

    public class BigChar {
        private char charname; // 인스턴스 이름 0,1,2...
        private String fontdata; // 파일 읽은 데이터

        public BigChar(char charname) {
            this.charname = charname;
            String filePath = "big" + charname + ".txt";
            InputStream is = this.getClass().getResourceAsStream(filePath);
            try {
                BufferedReader reader =
                    new BufferedReader(new InputStreamReader(is));

                String line;

                StringBuffer buf = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buf.append(line);
                    buf.append("\n");
                }
                reader.close();
                this.fontdata = buf.toString();
            } catch (IOException e) {
                this.fontdata = charname + "?" + filePath;
            }
        }

        public void print() {
            System.out.println(fontdata);
        }
    }

```

### BigCharFactory

```java
import java.util.HashMap;

    public class BigCharFactory {
        private HashMap<String, BigChar> pool = new HashMap<String, BigChar>(); // BigChar인스턴스 관리

        // 싱글톤 생성
        private static BigCharFactory singleton = new BigCharFactory();

        private BigCharFactory() {
        }

        public static BigCharFactory getInstance() {
            return singleton;
        }

        /**
         * BigChar 인스턴스 생성 공유
         * 
         * @param charname
         * @return
         */
        public synchronized BigChar getBigChar(char charname) { // synchronized 이유? 스레드 여러개 일때 방어 코드
            BigChar bc = pool.get("" + charname); // 해쉬맵에서 꺼내옴
            if (bc == null) {
                bc = new BigChar(charname); // null이면 새로 만들어버림 :)
                pool.put("" + charname, bc); // 해쉬맵에 새로 담기
            }
            return bc;
        }
    }

```

### BigString

```java
    public class BigString {
        private BigChar[] bigchars; // BigChar 배열
        // 생성자

        public BigString(String string) { // String으로 들어온걸 1개씩 쪼개서, file을 읽는다
            bigchars = new BigChar[string.length()];
            BigCharFactory factory = BigCharFactory.getInstance(); // Factory도 싱글턴
            for (int i = 0; i < bigchars.length; i++) {
                bigchars[i] = factory.getBigChar(string.charAt(i)); // Factory에서 하나 꺼냄
            }
        }

        // 표시
        public void print() {
            for (int i = 0; i < bigchars.length; i++) {
                bigchars[i].print();
            }
        }
    }
```

### Main

```java
    public class Main {

        public static void main(String[] args) {
            BigString bs = new BigString("111-222-333");
            bs.print();
        }
    }
```

## 사고를 넓히기 위한 포인트

### 여러 장소에 영향을 미친다

Flyweight 패턴에서는 인스턴스를 공유하는 것이 테마입니다.

인스턴스를 공유할때 주의해야 하는점은 `여러 장소에 영향을 미친다`는 점 입니다.

즉 하나의 인스턴스를 변경하면 그 인스턴스를 사용하고 있는 여러 장소에 동시에 영향을 미칩니다.

따라서, 제공해야 할 정보는 신중히 생각해야 합니다.

예를들어 예제에 색을 추가한다고 하면, 어느 클래스에 두어야 할까요?

BigChar 에 두면, 동일한 글자에 동일한 색 1개만 적용가능합니다.

BigString 에 두면, 3번째 문자는 빨강 처럼 관리할 수 있습니다.

이 두가지는 누가 옳고 그름이 아니라, 목적에 주의해서 정의하면 됩니다.

### intrinsic 와 extrinsic

공유시키는 정보 intrinsic

공유시키지 않는 정보 extrinsic

intrinsic은 인스턴스를 어디에 가지고 있더라도 어떠한 상황에서도 변하지 않는정보.

상태에 의존하지 않는 정보입니다.

BigChar에 필드 데이터는 BigString 어디에 등장해도 변하지 않습니다.

BigChar의 fontdata는 intrinsic한 정보가 됩니다.


extrinsic은 인스턴스를 두는 장소에 따라서 변화하는 정보. 상황에따라 변화하는 정보.

상태에 의존하는 정보 입니다.

예를 들어 BigChar의  인스턴스가 BigString의 몇번째 문자인가 하는 정보는

BigChar가 놓이는 장소에 따라 변하기 때문에 BigChar에게 제공할 수는 없습니다.

이 정보는 extrinsic한 정보가 됩니다.

앞에 설명한 '색'의 정보를 BigChar에게 제공할 것인가 아닌가라는 이야기는,

색을 intrinsic한 정보로 취급할것인가, extrinsic한 정보로 취급할 것인가로 바꿔 말할 수 있습니다.

### 인스턴스와 GC(garbage collection)

BigCharFactory에서 HashMap을 사용해서 생성한 인스턴스를 관리하고 있습니다.

이와 같이 인스턴스를 관리하는 기능을 Java에서 실현할때는 

반드시 `관리되고 있는 인스턴스는 garbage collection되지 않는다` 라는 점을 주의해야 합니다.