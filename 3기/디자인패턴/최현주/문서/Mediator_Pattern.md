# Mediator Pattern (중재자 패턴) 



## 1. Mediator Pattern 
- [행위패턴]
- 관련된 **객체들간의 참조를 피하기 위해** 사용하는 패턴.
- 다수의 객체를 다룰 때 사용한다 (객체들간의 관계가 복잡할 때)
- 객체들이 서로 복잡한 연관 관계를 맺고 있을 때, 클래스끼리 서로 호출하는 방법을 피하고 이러한 상호 작용에 관한 행동을 **중재자 클래스** 에서 정의하여 중재.

[참고]

![uml](http://cfile3.uf.tistory.com/image/2630184A53F35F8209BC5D)

- A객체가 B, C 객체를 직접적으로 호출하지 않고, Mediator 객체를 거쳐서 통신을 하고 있다.




## 2. UML Diagram
![uml](http://www.dofactory.com/images/diagrams/net/mediator.gif)



## 3. 장/단점

### [장점]


- 객체간의 통신을 위해서는 Mediator 객체만 참조하면 된다. (다른 객체를 참조할 필요가 없음)
- 객체간의 결합도가 낮아진다.
- 제어 로직을 한 군데 모아놨기 때문에 관리하기가 수월하다.




### [단점]

- Mediator 클래스가 다른 객체들보다 복잡해진다.
  - 객체들간 상호작용을 Mediator 클래스에 전부 정의해주기 때문에.
- Mediator 클래스에 집중화가 이루어져 유지보수가 어려워질 수도 있다.

​

## 4. 코드 설명

- 채팅 중재자 클래스(ChatRoom)를 만들고, 유저 클래스들을(UserBase)를 Dictionary 형태로 관리.
- A 유저가 B 유저에게 `중재자 클래스를 통해서` 메시지를 전송하게 되면 해당 유저에게 메시지를 전송.





### [IChatRoom.cs]

~~~~c#
    /// <summary>
    /// Mediator(중재자) 인터페이스.
    /// Colleague 객체들과 의사소통을 하기위한 인터페이스를 정의.
    /// </summary>
    public interface IChatRoom
    {
        //채팅방에 참가하는 유저를 등록하는 메서드.
        ChatRoom AddUser(UserBase user);
        void SendMessage(string from, string to, string message);
    }
~~~~



### [ChatRoom.cs]

~~~~c#
    /// <summary>
    /// Concrete Mediator
    /// 
    /// Mediator 인터페이스를 구현.
    /// Colleague 객체들간의 의사소통을 위한 클래스!!!
    public class ChatRoom : IChatRoom
    {
        //Colleague 객체들을 관리하는 Dictionay.
        private Dictionary<string, UserBase> userDic = new Dictionary<string, UserBase>();

        /// <summary>
        /// @ override 
        /// 
        /// 채팅방에 참여하는 유저를 등록하는 메소드.
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        public ChatRoom AddUser(UserBase user)
        {
            if (!userDic.ContainsValue(user))
            {
                userDic.Add(user.Name, user);
            }
            user.ChatRoom = this;

            return this;
        }

        /// <summary>
        /// @ override
        /// 
        /// 메세지를 from 유저로 부터 to 유저에게 보내는 메소드.
        /// </summary>
        /// <param name="from"></param>
        /// <param name="to"></param>
        /// <param name="message"></param>
        public void SendMessage(string from, string to, string message)
        {
            UserBase receiverUser = userDic[to];

            if (receiverUser != null)
            {
                receiverUser.ReceiveMessage(from, message);
            }
        }
    }
~~~~



### [UserBase.cs]

~~~~c#
    /// <summary>
    /// Colleague(객체) 인터페이스.
    /// </summary>
    public abstract class UserBase
    {
        //자신의 중재자가 누구인지 알아야 한다 (다른 객체와 통신이 필요할 때 중재자를 통해서 교류).
        private IChatRoom chatroom;
        private string name;

        public IChatRoom ChatRoom { get { return chatroom; } set { chatroom = value; } }
        public string Name { get { return name; } }

        public UserBase(string name)
        {
            this.name = name;
        }

        /// <summary>
        /// 중재자(chatroom)를 통해서 다른 유저(to)에게 message를 보내는 메소드.
        /// </summary>
        /// <param name="to"></param>
        /// <param name="message"></param>
        public void SendMessage(string to, string message)
        {
            chatroom.SendMessage(name, to, message);
        }

        public abstract void ReceiveMessage(string from, string message);
    }
~~~~



### [BasicUser.cs]

```c#
    /// <summary>
    /// Concrete Colleague
    /// 실제 객체를 구현하는 클래스.
    /// </summary>
    public class BasicUser : UserBase
    {
        public BasicUser(string name) : base(name)
        {
        }

        public override void ReceiveMessage(string from, string message)
        {
            Debug.Log(string.Format("[일반 유저] {0} -> {1} : {2}", from, this.Name, message));
        }
    }
```



### [RankerUser.cs]

```c#
    /// <summary>
    /// Concrete Colleague
    /// 실제 객체를 구현하는 클래스.
    /// </summary>
    public class RankerUser : UserBase
    {
        public RankerUser(string name) : base(name)
        {
        }

        public override void ReceiveMessage(string from, string message)
        {
            Debug.Log(string.Format("[랭커 유저] {0} -> {1} : {2}", from, this.Name, message));
        }
    }
```



### [MainProgram.cs]

```c#
   
    public class MainProgram : MonoBehaviour
    {
        void Main()
        {
            ChatRoom chatRoom = new ChatRoom();

            // 유저를 생성하고,
            UserBase kim = new BasicUser("김씨");
            UserBase lee = new BasicUser("이씨");
            UserBase park = new BasicUser("박씨");
            UserBase choi = new BasicUser("최씨");
            UserBase yang = new RankerUser("양씨");

            // 채팅방에 생성한 유저를 추가한다.
            chatRoom.AddUser(kim).AddUser(lee).AddUser(park).AddUser(choi).AddUser(yang);

            yang.SendMessage(choi.Name, "최씨를 만나서 반가워");
            lee.SendMessage(park.Name, "치킨먹자");
            park.SendMessage(kim.Name, "오늘 2시 어때?");
            lee.SendMessage(choi.Name, "놀러와");
            choi.SendMessage(yang.Name, "전화가능?");
        }
    }
```



---


### [실행 결과]

	[일반 유저] 양씨 -> 최씨 : 최씨를 만나서 반가워
	[일반 유저] 이씨 -> 박씨 : 치킨먹자
	[일반 유저] 박씨 -> 김씨 : 오늘 2시 어때?
	[일반 유저] 이씨 -> 최씨 : 놀러와
	[랭커 유저] 최씨 -> 양씨 : 전화가능?



## 5. 구현시 고려해야할 이슈

1. Mediator의 추상화 객체 생략

   - 관련 객체들이 하나의 Mediator 클래스와 동작한다면 Mediator를 추상화 할 필요가 없다.
   - 다른 상호작용을 정의할 새로운 Mediator 서브 클래스가 필요할 때 필요.

   ​

2. 상호 관련된 객체들은 Observer 패턴을 이용해서 Mediator 객체들과 교류

   - 이벤트가 발생하면 Colleague 객체는 Mediator 클래스와 통신을 주고 받음.
   - 중재자 클래스의 구현방법 중 하나는 Observer를 사용하는 방법이다.
   - Colleague 객체의 상태변화가 일어날때마다 중재자 클래스에 통보하면, 중재자는 다른 객체들에게 변경을 통보하여 처리하는 방법 (Observer)

   ​

   [참고]

   http://outshine90.tistory.com/entry/%EC%A4%91%EC%9E%AC%EC%9E%90-%ED%8C%A8%ED%84%B4



## 6. Mediator와 Facade 패턴의 차이

- 복잡한 관계를 인터페이스화하여 이용한다는 부분이 두 패턴의 유사점!

  ​

1. Mediator 패턴

   - 양방향성을 띄는 부분 (객체와 중재자 클래스 사이의 상호작용)

   - 아래에서부터 정책을 적용.

     ​

2. Facade 패턴

   - 단방향성을 띄는 부분 (퍼사드 클래스에서만 객체를 호출 가능)
   - 위에서부터 정책을 적용.



## 7.참고사이트

- http://www.dofactory.com/net/mediator-design-pattern