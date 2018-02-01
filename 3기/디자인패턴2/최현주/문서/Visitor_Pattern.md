# Visitor Pattern (방문자 패턴) 



## 1. Visitor Pattern 
- 객체의 **`구조와 기능을 분리`**

- 구조는 그대로 두고, 알고리즘 객체만 추가 (확장성)

- 복잡한 구조체 클래스를 정의해놓고, 이 구조체를 사용하고자 할때!!!!!  **Visitor** 가 구조체 내부를 돌아다니면서 특정 작업을 하는 경우에 사용한다.

  ​



## 2. UML Diagram
![uml](http://www.oop-trainer.de/Themen/Grafiken/visitor.png)



## 3. 장/단점

### [장점]


- (클라이언트 입장에서) 알고리즘 구현방식을 기존 코드 변경없이 바꿀 수 있다.




### [단점]

- 데이터를 추가 확장하는게 힘들다
  - Visitor 인터페이스 및 클래스에 새 데이터 객체에 대한 visit 메서드를 추가해줘야하기 때문에

  - 따라서, 데이터의 변경이 적고 알고리즘이 주로 변경되는 부분에서 사용하는 것이 적절.

    ​


## 4. 코드 설명

- 각 컴퓨터 부품(Monitor, Mouse, Keyboard)을 정의하고, 이 부품들을 사용하는 Computer 클래스를 정의
- Computer 클래스에서는 각 부품들을 인터페이스로 관리하여, 인터페이스에 정의된 Accept(Visitor) 메서드를 호출
- 각 Visitor 클래스에서 객체들을 전달받아 객체들에 대한 세부 작업을 구현하는 구현 클래스 역할을 함.





### [IComputerPart.cs]

~~~~c#
    /// <summary>
    /// Element 인터페이스 역할 (데이터의 인터페이스)
    /// 
    /// 각 객체는 Visitor 객체가 세부 작업을 처리할 수 있도록 
    /// Visitor 인터페이스를 받아야한다.
    /// </summary>
    public interface IComputerPart
    {
        void Accept(IComputerPartVisitor visitor);
    }
~~~~


### [Monitor.cs]

~~~~c#
    /// <summary>
    /// Concrete Element 역할 
    /// IComputerPart의 구현 클래스
    /// </summary>
    public class Monitor : IComputerPart
    {
        // Visitor에 자기 자신을 전달 (Visitor가 해당 객체의 세부작업을 처리할 수 있도록)
        public void Accept(IComputerPartVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
~~~~


### [Keyboard.cs]

~~~~c#
    /// <summary>
    /// Concrete Element 역할 
    /// IComputerPart의 구현 클래스
    /// </summary>
    public class Keyboard : IComputerPart
    {
        public void Accept(IComputerPartVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
~~~~


### [Mouse.cs]

```c#
    /// <summary>
    /// Concrete Element 역할 
    /// IComputerPart의 구현 클래스
    /// </summary>
    public class Mouse : IComputerPart
    {
        public void Accept(IComputerPartVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
```



### [Computer.cs]

```c#
    /// <summary>
    /// Concrete Element 역할
    /// IComputerPart의 구현 클래스
    /// 
    /// 복잡한 구조체 (부품들을 가지고 있음) - Composite 패턴과 유사.
    /// 이 구조체를 사용하고 싶을 때 Visitor를 통해서 내부 로직에 접근한다.
    /// </summary>
    public class Computer : IComputerPart
    {
        private IComputerPart[] parts;

        // 부품을 생성한다.
        public Computer()
        {
            parts = new IComputerPart[] { new Monitor(), new Keyboard(), new Mouse() };
        }

        public void Accept(IComputerPartVisitor visitor)
        {
            for(int i=0; i<parts.Length; i++) {
                parts[i].Accept(visitor);
            }
            visitor.Visit(this);
        }
    }
```



### [IComputerPartVisitor.cs]

```c#
    /// <summary>
    /// Visitor 인터페이스
    /// 객체들을 자신을 던져주는 Visit 메소드를 정의 
    /// (객체추가시 메소드도 추가되야된다)
    /// 
    /// 모든 객체들은 방문자를 통해 세부 구현을 해야하니까, 각 객체들의 방문자들을 총집합시키는 인터페이스?
    /// </summary>
    public interface IComputerPartVisitor
    {
        void Visit(Monitor monitor);
        void Visit(Keyboard keyboard);
        void Visit(Mouse mouse);
        void Visit(Computer computer);
    }
```

### [ComputerPartDisplayVisitor.cs]

```c#
    /// <summary>
    /// Concrete Visitor 역할
    /// Visitor 인터페이스를 구현
    /// 
    /// 각 객체들을 전달받아 클래스에서 세부 작업을 처리한다.
    /// 컴퓨터 부품들의 상태를 확인하는 클래스.
    /// </summary>
    public class ComputerPartDisplayVisitor : IComputerPartVisitor
    {
        public void Visit(Monitor monitor)
        {
            Debug.Log("모니터 상태를 보여준다.");
        }

        public void Visit(Keyboard keyboard)
        {
            Debug.Log("키보드 상태를  보여준다.");
        }

        public void Visit(Mouse mouse)
        {
            Debug.Log("마우스 상태를 보여준다.");
        }

        public void Visit(Computer computer)
        {
            Debug.Log("컴퓨터 상태를 보여준다.");
        }
    }
```

### [ComputerPartExecuteVisitor.cs]

```c#
    /// <summary>
    /// Concrete Visitor 역할
    /// Visitor 인터페이스를 구현
    /// 
    /// 각 객체들을 전달받아 클래스에서 세부 작업을 처리한다.
    /// 컴퓨터 부품들을 실행시키는 클래스.
    /// </summary>
    public class ComputerPartExecuteVisitor : IComputerPartVisitor
    {
        public void Visit(Monitor monitor)
        {
            Debug.Log("모니터를 작동한다.");
        }

        public void Visit(Keyboard keyboard)
        {
            Debug.Log("키보드를 작동한다.");
        }

        public void Visit(Mouse mouse)
        {
            Debug.Log("마우스를 작동한다.");
        }

        public void Visit(Computer computer)
        {
            Debug.Log("컴퓨터를 작동한다");
        }
    }
```



### [MainProgram.cs]

~~~~c#
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //복잡한 구조체를 정의해놓고,
            IComputerPart computer = new Computer();

            //Visitor를 전달하여 세부 기능을 실행.
            // Visitor 전달 -> Computer -> Visitor에 전달.
            computer.Accept(new ComputerPartDisplayVisitor());

            computer.Accept(new ComputerPartExecuteVisitor());
        }
    }
~~~~

---


### [실행 결과]

	모니터 상태를 보여준다.
	키보드 상태를  보여준다.
	마우스 상태를 보여준다.
	컴퓨터 상태를 보여준다.
	
	모니터를 작동한다.
	키보드를 작동한다.
	마우스를 작동한다.
	컴퓨터를 작동한다.
