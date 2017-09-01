# Adapter Pattern

### 1. 클래스 어댑터 패턴
	- 장점 : 어댑터 전체를 다시 구현할 필요 없이 빠릅니다.
	- 단점 : 상속을 활용하기 때문에 유연하지 못합니다.

	public class Adaptee {
    	public void specificRequest(){
        	System.out.println("Adapter.specificRequest()");
        }
    }
    
    public interface Target{
    	public void request();
    }
    
    public class Adapter extends Adaptee implements Target{
    	public void request(){
        	this.specificRequest();
        }
    } 
    
    public class Client {
    	public static void main(String[] args){
        	Target target = new Adapter();
            
            target.request();
        }
    }

_____


### 2. 객체 어댑터 패턴
	- 장점 : 구성을 사용하기 때문에 유연합니다.
	- 단점 : 어댑터클래스의 대부분의 코드를 구현해야 하므로 효율적이지 못합니다.

	public class Adaptee {
    	public void specificRequest(){
        	System.out.println("Adaptee.speificRequest()");
        }
    }
    
    public interface Target{
    	public void request();
    }
    
    public class Adapter implements Target {
    	Adaptee adaptee = new Adaptee();
        
        public void request(){
        	adaptee.specificRequest();
        }
    }
    
    public class Client{
    	public static void main(String[] args){
        	Target target = new Adapter();
            
            target.request();
        }
    }


##### 참고
https://lalwr.blogspot.kr/2016/03/adapter-vs-facade-vs-decorator.html
