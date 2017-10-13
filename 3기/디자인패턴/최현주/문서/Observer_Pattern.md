# Observer Pattern (감시자 패턴) 



## 1. Observer Pattern 
- [행위패턴]

- 한 객체의 상태가 변경되면 그 객체에 **의존하는 다른 객체들한테 자동으로 통지**되고, 이에 따라 **객체들의 데이터가 자동으로 갱신**된다.

- 일대다 의존성

  ​

## 2. UML Diagram
![uml](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Observer.svg/500px-Observer.svg.png)



## 3. 장/단점

### [장점]


- 서로 상호작용하는 Subject 객체와 Observer 객체가 `느슨한 결합`이다.
- Observer가 추가되어도 Subject 변경은 필요없다.




### [단점]

- 메모리 누수가 발생할 수 있다. 

  - 사용하지 않는 Observer는 제거해주지않으면 불필요한 비용이 증가한다.

- 어떤 객체가 통보를 받는지가 명확히 알 수 없다.

  ​

## 4. 코드 설명

- 블로그(Subject)를 구독하는 유저(Observer)들이 있다.
- 블로그에 새로운 글이 게시되면 해당 블로그를 구독하는 유저에게 등록된 글에 대한 업데이트 알람 전송한다.




### [IObserver.cs]

~~~~c#
    /// <summary>
    /// Observer(관찰자) 인터페이스.
    /// Subject 객체로부터 상태변화를 통보를 받는다.
    /// </summary>
    public interface IObserver
    {
        //Subject 객체가 상태 변화를 통보하면 
        //Observer 객체들은 변화된 데이터에 맞게 자신의 데이터를 Update시킨다.
        void Update();
    }
~~~~



### [User.cs]

~~~~c#
    /// <summary>
    /// Concrete Observer (구체적인 관찰자)
    /// </summary>
    public class User : IObserver
    {
        private ISubject blog;

        private string name;

        //생성자를 통해, 관찰당하는(User)에게 관찰 대상자(Blog)가 누군지 알려준다.
        //해당 유저가 구독하는 블로그가 무엇인지 초기화.
        public User(string name, ISubject blog)
        {
            this.name = name;
            this.blog = blog;
        }

        /// <summary>
        /// Subject 객체로부터 상태변화를 통보를 받으면, 각자 서브클래스에서 세부 기능을 구현.
        /// </summary>
        public void Update()
        {
            Debug.Log(string.Format("{0}님, 구독하는 블로그가 업데이트 되었습니다.", name));
            Debug.Log("[새 글] " + blog.GetArticle());
        }
    }
~~~~



### [ISubject.cs]

~~~~c#
    /// <summary>
    /// Subject(관찰 대상자)의 역할.
    /// Observer 객체를 등록/삭제하는 메소드를 가지고 있음.
    /// </summary>
    public interface ISubject
    {
        //Observer를 등록/해제.
        void RegisterObserver(IObserver observer);
        void UnRegisterObserver(IObserver observer);

        void NotifyObserver();

        string GetArticle();
    }
~~~~



### [Blog.cs]

```c#
    /// <summary>
    /// Concrete Subject (구체적인 관찰 대상자)
    /// Subject(Blog)는 Observer를 관리하며 등록/해지 메소드를 가지고 있다.
    /// </summary>
    public class Blog : ISubject
    {
        private List<IObserver> observerList;

        private string article;

        public Blog()
        {
            observerList = new List<IObserver>();
        }

        public void RegisterObserver(IObserver observer)
        {
            observerList.Add(observer);
        }

        public void UnRegisterObserver(IObserver observer)
        {
            observerList.Remove(observer);
        }

        /// <summary>
        /// 등록된 옵저버리스트에게 업데이트 사실을 전달.
        /// 자신의(Blog) 변경된 내용을 전달하기위해서 this형태로 전달한다.
        /// </summary>
        public void NotifyObserver()
        {
            for (int i = 0; i < observerList.Count; i++)
            {
                observerList[i].Update();
            }
        }

        /// <summary>
        /// 새로운 글을 등록하고, 해당 블로그를 구독하는 유저에게 업데이트를 알린다.
        /// </summary>
        /// <param name="article"></param>
        public void PostNewAriticle(string article)
        {
            this.article = article;

            Debug.Log("[새로운 글 등록] : " + article);
            Debug.Log("=============================");

            NotifyObserver();
        }

        public string GetArticle()
        {
            return article;
        }
    }
```



### [MainProgram.cs]

```c#
    public class MainProgram : MonoBehaviour
    {
        void Main()
        {
            Blog blog = new Blog();

            User user1 = new User("김가네", blog);
            User user2 = new User("이철", blog);
            User user3 = new User("박철", blog);

            blog.RegisterObserver(user1);
            blog.RegisterObserver(user2);
            blog.RegisterObserver(user3);

            blog.PostNewAriticle("전체 공지! 휴가 사용시 유의사항!");
        }
    }
```



---


### [실행 결과]

	[새로운 글 등록] : 전체 공지! 휴가 사용시 유의사항!
	=============================
	김가네님, 구독하는 블로그가 업데이트 되었습니다.
	[새 글] 전체 공지! 휴가 사용시 유의사항!
	
	이철님, 구독하는 블로그가 업데이트 되었습니다.
	[새 글] 전체 공지! 휴가 사용시 유의사항!
	
	박철님, 구독하는 블로그가 업데이트 되었습니다.
	[새 글] 전체 공지! 휴가 사용시 유의사항!
