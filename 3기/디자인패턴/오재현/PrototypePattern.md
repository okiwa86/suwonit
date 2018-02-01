# Prototype Pattern

### 설명
- 패턴 내에서 object의 생성을 제공합니다.
- object 생성이 높은 비용으로 많은 요청을 하는 경우, 또는 비슷한 object를 지속적으로 생성해야 할 때 유용하게 사용합니다.

### 장점
- 객체를 생성하기 위한 별도의 객체 생성 클래스가 불필요합니다.
- 객체의 각 부분을 조합해서 생성되는 형태에도 적용 가능합니다.
- 서브클래스의 수를 줄일 수 있습니다.

### 단점
- 생성될 객체들의 자료형인 클래스들이 모두 clone() 메서드를 구현해야 합니다.

### User Class
	public class Users implements Cloneable {
    	private List userList;
        
        public Users(){
        	userList = new ArrayList();
        }
        
        public Users(List list){
        	this.userList = list;
        }
        
        public void loadData(){
        	userList.add("aaa");
            userList.add("bbb");
            userList.add("ccc");
            userList.add("ddd");
        }
        
        public List getUserList(){
        	return userList;
        }
        
        @Override
        public Object clone() throws CloneNotSupportedException{
        	List temp = new ArrayList();
            for(String s : this.getUserList()){
            	temp.add(s);
            }
            return new Users(temp);
        }
    }

### main Class
	public class main {
    	public static void main(String[] args) throws Exception {
        	Users originUsers = new Users();
            originUsers.loadData();
            
            Users cloneUsers = (Users)originUsers.clone();
        }
    }


### 참고
http://leetaehoon.tistory.com/55
https://blog.seotory.com/post/2015/09/java-prototype-pattern
