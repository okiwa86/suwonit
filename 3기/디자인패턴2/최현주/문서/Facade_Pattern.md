# Facade Pattern 



## 1. Facade Pattern 이란 
- `단순화된 인터페이스`를 통해 **서브시스템을 쉽게** 사용 가능
- 사용하기 복잡한 인터페이스들을 하나의 메소드로 묶어서 사용자가 호출하기 쉽게 **단순화된 인터페이스**를 제공한다.
- ex) 외출을 하려면 **(씻는다 - 머리를 감는다 - 화장을 한다 - 옷을 입는다 - 액세서리를 장착한다 - 거울보고 점검한다)**의 행동을 해야한다.
  이 행동을 각각 객체에 접근해 실행시키는 것이 아니라 퍼사드 클래스에서 세부 구현한 외출한다() 메소드를 호출한다.





## 2. UML Diagram
![uml](https://www.packtpub.com/sites/default/files/Article-Images/B05180_01.png)



## 3. 장/단점

### [장점]


- 클라이언트와 서브 시스템간의 결합도가 낮아진다.
- 클라이언트 입장에서 다뤄야할 객체의 수가 줄어든다. (퍼사드 클래스를 통해 호출하면 되니까)




### [단점]

- 복잡한 시스템이 많아지면, 퍼사드 클래스가 그만큼 많이 늘어남.

- 시스템이 복잡해질 수도 있다!

  ​


## 4.코드설명

- 컴퓨터 클래스에 각 부품들을 가지고 있고, 부팅시 해당 부품들을 작동시키는 예제

  ​

### [IComputerPart.cs]

~~~~c#
    /// <summary>
    /// 각 부품별 공통 메소드를 정의한 인터페이스
    /// </summary>
    public interface IComputerPart
    {
        void Operation();
    }
~~~~



### [Bios.cs]

~~~~c#
    /// <summary>
    /// SubSystem 클래스
    /// 기능 구현
    /// </summary>
    public class Bios : IComputerPart
    {
        public void Operation()
        {
            Debug.Log("바이오스 구동중...");
        }
    }
~~~~



### [CPU.cs]

~~~~c#
    public class CPU : IComputerPart
    {
        public void Operation()
        {
            Debug.Log("CPU 처리중...");
        }
    }
~~~~



### [Memory.cs]

```c#
    public class Memory : IComputerPart
    {
        public void Operation()
        {
            Debug.Log("메모리 로딩중...");
        }
    }
```



### [HDD.cs]

```c#
    public class HDD : IComputerPart
    {
        public void Operation()
        {
            Debug.Log("하드 디스크 로딩중...");
        }
    }
```



### [Computer.cs]

```c#
    /// <summary>
    /// Facade 클래스 역할
    /// 각 부품들을 작동시키기 위해 필요한 부품들을 가지고 있는다.
    /// </summary>
    public class Computer
    {
        private Bios bios;
        private CPU cpu;
        private Memory memory;
        private HDD hdd;

        public Computer()
        {
            bios = new Bios();
            cpu = new CPU();
            memory = new Memory();
            hdd = new HDD();
        }

        //필요한 여러 부품들을 작동시킨다.
        public void Booting()
        {
            Debug.Log("== 컴퓨터 부팅 시작 ==");

            bios.Operation();
            cpu.Operation();
            memory.Operation();
            hdd.Operation();
        }
    }
```



### [MainProgram.cs]

~~~~c#
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //퍼사드 클래스를 생성하고, 필요한 메소드(복잡한 인터페이스들을 작동) 호출한다.
            Computer facade = new Computer();
            facade.Booting();
        }
    }
~~~~



---



### [실행 결과]

	== 컴퓨터 부팅 시작 ==
	바이오스 구동중...
	CPU 처리중...
	메모리 로딩중...
	하드 디스크 로딩중...
