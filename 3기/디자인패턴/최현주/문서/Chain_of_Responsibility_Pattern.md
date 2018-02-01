# Chain of Responsibility Pattern (책임 연쇄) 



## 1. Chain of Responsibility Pattern정의 
- [행동 패턴]

- `요청을 하는 객체`(송신)와 요청을 받아 `처리(수신)하는 객체` 사이의 **결합을 줄이는 패턴**

- 요청을 받는 객체들을 연쇄적으로 묶는다 => 맨 첫 객체부터 처리할 객체에 도달할때까지 연쇄적으로 요청을 넘긴다 

- ex) 기획자가 프로그래머에게 파티클 버그를 수정해달라고 요청 -> 프로그래머가 이펙트 팀에게 버그를 전달함 -> 이펙트 팀이 해당 버그 수정.

  ​



## 2. UML Diagram
![uml](http://www.dofactory.com/images/diagrams/net/chain.gif)



## 3. 장/단점

### [장점]


- 시스템의 결합도를 낮춘다.
- 객체들을 연쇄적으로 묶는 것을 동적으로 처리 가능.




### [단점]

- 요청 결과가 제대로 처리될지 알 수 없다. 

  - 연쇄적으로 요청을 전달하였으나 처리할 객체가 존재하지 않을 경우 요청이 처리되지 X

  ​


## 4. 코드 설명

- 대출 시스템을 예로 샘플 코드를 작성
- 희망 대출 금액에 따라서 승인을 허용해주는 담당자의 직급이 다르다.
- 대출금액에 따라 대출 요청을 하면 (사원->팀장->점장) 연쇄적으로 요청이 진행된다.





### [LoanRequest.cs]

~~~~c#
    /// <summary>
    /// 대출 요청시 필요한 데이터 정의
    /// </summary>
    public struct LoanRequest
    {
        public string Customer { get; set; }
        public int Amount { get; set; }
    }
~~~~



### [RequestHandler.cs]

~~~~c#
    /// <summary>
    /// Handler 추상화 클래스
    /// </summary>
    public abstract class RequestHandler
    {
        protected RequestHandler successor { get; set; }

        public string Name { get; set; }

        /// <summary>
        /// 다음 링크(객체)를 구현한다. (선택적 메소드)
        /// </summary>
        /// <param name="successor"></param>
        public void SetSuccessor(RequestHandler successor)
        {
            this.successor = successor;
        }

        /// <summary>
        /// 각 클래스별 요청을 처리하는 세부 기능 구현
        /// 조건에 해당하지 않으면 다음 객체에 요청을 전달하는 기능.
        /// </summary>
        /// <param name="request"></param>
        public abstract void HandlerRequest(LoanRequest request);
    }
~~~~



### [Cashier.cs]

~~~~c#
    /// <summary>
    /// Concrete Handler
    /// </summary>
    public class Cashier : RequestHandler
    {
        public override void HandlerRequest(LoanRequest request)
        {
            Debug.Log(string.Format("[대출 요청] 고객 이름:{0}, 금액:${1}", request.Customer, request.Amount));

            //조건에 만족하면 해당 클래스에서 아래 작업을 처리.
            if (request.Amount < 1000)
            {
                Debug.Log(string.Format("[승인 완료] {0}에게 대출 완료 - 담당자 {1}", request.Customer, this.Name));
            }
            else if (successor != null) //만족하지 못하면 다음 객체에 요청사항을 전달.
            {
                Debug.Log(string.Format("[승인 거부] 담당자 변경 - {1}", request.Customer, this.Name));

                successor.HandlerRequest(request);
            }
        }
    }
~~~~



### [TeamLeader.cs]

```c#
    /// <summary>
    /// Concrete Handler
    /// </summary>
    public class TeamLeader : RequestHandler
    {
        public override void HandlerRequest(LoanRequest request)
        {
            //조건에 만족하면 해당 클래스에서 아래 작업을 처리.
            if (request.Amount < 3000)
            {
                Debug.Log(string.Format("[승인 완료] {0}에게 대출 완료 - 담당자 {1}", request.Customer, this.Name));
            }
            else if (successor != null) //만족하지 못하면 다음 객체에 요청사항을 전달.
            {
                Debug.Log(string.Format("[승인 거부] 담당자 변경 - {1}", request.Customer, this.Name));

                successor.HandlerRequest(request);
            }
        }
    }
```



### [Manager.cs]

```c#
    public class Manager : RequestHandler
    {
        public override void HandlerRequest(LoanRequest request)
        {
            //조건에 만족하면 해당 클래스에서 아래 작업을 처리.
            if (request.Amount < 10000)
            {
                Debug.Log(string.Format("[승인 완료] {0}에게 대출 완료 - 담당자 {1}", request.Customer, this.Name));
            }
            else if (successor != null) //만족하지 못하면 다음 객체에 요청사항을 전달.
            {
                Debug.Log(string.Format("[승인 거부] 담당자 변경 - {1}", request.Customer, this.Name));

                successor.HandlerRequest(request);
            }
        }
    }
```

### 

### [MainProgram.cs]

~~~~c#
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //요청 사항을 생성.
            List<LoanRequest> requestList = new List<LoanRequest>();
            requestList.Add(new LoanRequest() { Amount = 800, Customer = "지니" });
            requestList.Add(new LoanRequest() { Amount = 2500, Customer = "헤르미온느" });
            requestList.Add(new LoanRequest() { Amount = 9600, Customer = "말포이" });
            requestList.Add(new LoanRequest() { Amount = 200000, Customer = "해리포터" });

            //Chain 생성
            //요청사항을 처리할 Handler 객체들을 생성한다. 
            RequestHandler cashier = new Cashier() { Name = "김사원, Cachier"};
            RequestHandler teamLeader = new TeamLeader() { Name = "박팀장, TeamLeader" };
            RequestHandler manager = new Manager() { Name = "하점장, Manager" };

            //Handler 객체들의 고리 순서 설정(연쇄)한다.
            //클라이언트 입장에서 Chain는 자유자재로 변경이 가능.
            cashier.SetSuccessor(teamLeader);
            teamLeader.SetSuccessor(manager);

            for(int i=0; i<requestList.Count; i++) {
                cashier.HandlerRequest(requestList[i]);
            }
        }
    }
~~~~



---



### [실행 결과]

	[대출 요청] 고객 이름:지니, 금액:$800
	[승인 완료] 지니에게 대출 완료 - 담당자 김사원, Cachier
	
	[대출 요청] 고객 이름:헤르미온느, 금액:$2500
	[승인 거부] 담당자 변경 - 김사원, Cachier
	[승인 완료] 헤르미온느에게 대출 완료 - 담당자 박팀장, TeamLeader
	
	[대출 요청] 고객 이름:말포이, 금액:$9600
	[승인 거부] 담당자 변경 - 김사원, Cachier
	[승인 거부] 담당자 변경 - 박팀장, TeamLeader
	[승인 완료] 말포이에게 대출 완료 - 담당자 하점장, Manager
	
	[대출 요청] 고객 이름:해리포터, 금액:$200000
	[승인 거부] 담당자 변경 - 김사원, Cachier
	[승인 거부] 담당자 변경 - 박팀장, TeamLeader
