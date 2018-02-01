# Template method Pattern

### 설명
- 상위 클래스에는 템플릿 메소드가 있으며 그 메소드 안에는 추상 메소드를 사용합니다. 그리고 하위 클래스에서 기능 구현을 하는 구조입니다.

- 따라서 전체적인 구조를 파악하기 위해 상위 클래스만 보고 이해할 수 있지만 세부적인 기능을 알기 위해서는 하위 클래스를 봐야 합니다.

- 또한, 상위 클래스에서는 하위 클래스에서 정의해야 할 부분을 유연하게 만듬으로써 공통된 것을 제외하고 각 하위 클래스마다 적절하게 기능을 다르게 처리할 수 있습니다.

- 샌드위치를 만들 때 빵을 올리고 소스를 뿌리고 재료를 넣는 행위는 동일하지만 그 안에 어떤 빵, 소스, 재료를 올리는지는 다른 것을 예로 들을 수 있습니다.

### 코드

	// 부모클래스
	public abstract class Sandwich{
    	public void cook(){
        	bread();
            System.out.println("양상추를 깐다");
            topping();
            System.out.println("토마토를 넣는다");
            bread();
        }
        
        public abstract void bread();
        public abstract void topping();
    }
    
    // 자식클래스 : 햄
    public class Ham extends Sandwich {
    	public void bread(){
    		System.out.println("갈색 빵 한장");
        }
        public void topping(){
        	System.out.println("햄을 넣는다");
        }
    }
    
    // 자식클래스 : 참치
    public class Tuna extends Sandwich{
    	public void bread(){
        	System.out.println("흰색 빵 한장");
        }
        public void topping(){
        	System.out.println("참치를 넣는다");
        }
    }


#### 참고
http://itcrowd2016.tistory.com/22
