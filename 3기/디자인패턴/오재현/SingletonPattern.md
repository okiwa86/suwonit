# Singleton Pattern

### 설명
- 프로그램 실행시 많은 인스턴스가 생성되지만 싱글톤 패턴은 하나의 인스턴스만 생성하도록 제한합니다.
- static 필드로서 Singleton 클래스의 인스턴스에서 초기화됩니다.
- 싱글톤 클래스를 로드할 때 1회만 실행횝니다.
- 싱글톤 클래스의 생성자는 private , 외부에서 생성자의 호출을 금지하기 위한 것입니다.

### Singleton Class
	public class Singleton {
        private static Singleton singleton = null;

        // 외부에서는 new Singleton()를 이용하여 생성자를 호출할 수 없다.
        private Singleton(){
            System.out.println("인스턴스를 생성하였습니다.");
        }

        // getInstance 메서드는 singleton 인스턴스가 이미 생성되어 있는지를 검사한다.
        // 만약 처음 호출되어 아직 인스턴스가 생성되지 않은 상황이라면 생성자를 호출해 인스턴스를 생성한다.
        // 이렇게 생성된 인스턴스는 정적 변수 singleton에 의해 참조가 된다.
        // 만약 인스턴스가 생성되었다면 singleton 변수에서 참조하는 인스턴스를 반환한다.
        public static Singleton getInstance(){
            if(singleton == null){
                singleton = new Singleton();
            }
            return singleton;
        }
    }

### Main Class
	public class Main {
        public static void main(String[] args){
            System.out.println("Start");
            Singleton obj1 = Singleton.getInstance();
            Singleton obj2 = Singleton.getInstance();
            if(obj1 == obj2){
                System.out.println("obj1과 obj2는 같은 인스턴스 입니다.");
            } else {
                System.out.println("obj1과 obj2는 다른 인스턴스 입니다.");
            }
            System.out.println("End");
        }
    }

### 실행결과
	Start
    인스턴스를 생성하였습니다.
    obj1과 obj2는 같은 인스턴스 입니다.
    End


### 참고
http://devbox.tistory.com/entry/DesignPattern-%EC%8B%B1%EA%B8%80%ED%84%B4-%ED%8C%A8%ED%84%B4

