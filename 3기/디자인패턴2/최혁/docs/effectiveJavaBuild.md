# Effective Java Builder Pattern

## 왜쓸까

빌더 패턴은 오브젝트 작성 소프트웨어 설계 패턴입니다.

다형성을 가능케하려는 추상 팩토리 패턴 및 팩토리 메소드 패턴과는 달리,
빌더 패턴의 의도는 오브젝트 생성자 매개 변수 조합의 증가가 생성자의 지수 목록으로 연결될 때 발생하는 텔레 스코핑 생성자 anti 패턴에 대한 해결책을 찾는 것입니다.

수많은 생성자를 사용하는 대신 빌더 패턴은 단계별로 각 초기화 매개 변수를 수신 한 다음 작성된 결과 객체를 한 번에 리턴하는 다른 객체 인 빌더를 사용합니다.

## 코드로 보기

### 텔레스코핑 생성자

```java
Class Person(){
    int age, height, weight;
    String name;
    Person(String name){
        this.name = name;
    }
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    Person(String name, int age, int height){
        this.name = name;
        this.age = age;
    }
    Person(String name, int age, int height, int weight){
        this.name = name;
        this.age = age;
    }
}

// 여러가지 조합으로 생성할 수 있도록 생성자를 여러개 만들어서 사용.
// 단점. 정신없음, int값이 어느 필드 값인지 알기 힘듬.
Person p1 = new Person("최혁");
Person p2 = new Person("최혁",31);
Person p3 = new Person("최혁",31,180);
Person p4 = new Person("최혁",31,180,73);
```

### Java Beans 패턴

```java
Class Person(){
    int age, height, weight;
    String name;
    /*setter 작성했다고 가정*/
}

//아래처럼 하나씩 set해주는 방식
//단점. thread safe 하지 않음. 생성된 이후에 set을 해주기 때문
Person person = new Person();
person.setName("최혁");
person.setAge(31);
person.setHeight(180);
person.setWeight(73);
```

### Builder 패턴

```java
Class Person() {
    int age, height, weight;
    String name;

    public static class Builder() {
        int age, height, weight;
        String name;

        Builder(String name) {
            // 필수값일때 Builder생성자 파라미터로 쓰면 좋다.
            this.name = name;
        }
        public Builder age(int age){
            this.age = age;
        }
        public Builder height(int height){
            this.height = height;
        }
        public Builder weight(int weight){
            this.weight = weight;
        }
        public Builder build(){
            return new Person(this);
        }
    }
}

// 사용법
Person person = new Person.Builder("최혁")
                        .age(31)
                        .height(180)
                        .weight(73)
                        .build();

```