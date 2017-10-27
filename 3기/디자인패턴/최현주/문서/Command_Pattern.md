# Command Pattern (명령 패턴)  



## 1. Command Pattern 
- [행동 패턴]
- **요구 사항**을 객체로 **캡슐화**할 수 있다.
- 매개변수를 써서 여러 가지 다른 요구사항을 집어넣을 수 있다.
- 요청 내역을 큐에 저장하거나 로그로 기록할 수 있고, 작업 취소 기능도 지원 가능하다.
- `요청을 하는 객체와 그 요청을 수행하는 객체를 분리`하고 싶을 때 사용!



## 2. UML Diagram
![uml](http://blog.lukaszewski.it/wp-content/uploads/2013/07/command_diagram.png)

- **Client** 
  -  Concrete Command(커맨드 객체)를 생성하고, Receiver를 설정

  ​

- **Invoker**
  - 클라이언트의 Command 객체를 리시버에 전달한다.

  - execute()를 호출함으로써 Command 객체에게 특정 작업을 수행하라고 요구를 함.

    ​
- **Command**
  - 모든 Command 객체에서 구현해야 하는 인터페이스

  - 모든 명령은 execute() 메소드 호출로 실행

  - execute() 메소드에서는 리시버에 특정 작업을 처리하라는 지시를 전달

    ​
- **Concrete Command** 
  - 특정 행동과 리시버 사이를 연결해줌.

    ​
- **Receiver** 
  - 요구사항을 수행하기 위해 어떤 일을 처리해야하는지 알고 있는 객체
  - 요청을 수행한다.




##### [참고 예 - 식당]

1. 손님이 웨이터한테 주문을 한다.
2. 웨이터가 손님의 주문을 주문서에 적는다.
3. 웨이터는 주문서를 주방에 전달하여 주문을 요청한다.
4. 요리사는 주문서에 적힌 주문대로 음식을 만든다.

```
Client == 손님
Invoker == 웨이터
Command == 주문서
Receiver == 요리사
SetCommand() == 주문을 하다.
Execute() == 주문을 요청한다.
```




## 3. 장/단점

### [장점]


- 작업을 요청하는 객체와 수행하는 객체를 분리시키기때문에 `시스템의 결합도를 낮출 수 있다`
-  작업을 요청하는 객체와 수행하는 객체를 **독립적으로 변경**할 수 있다.
- Command 객체는 확장이 가능하며, 클라이언트는 코드 수정이 크게 필요하지 않다.




### [단점]

- 작업 요청 객체가 적을 경우에 사용하면 프로그램이 오히려 복잡해진다.

​

## 4. 코드 설명

- x, y 좌표를 가진 원과 네모를 그리거나 지우는 작업을 가정하고 샘플 코드를 구현해본다.
- 작업 내역을 Stack에 보관하고, 원한다면 **작업 취소도 가능**하다.
- Command 객체는 총 4개이다. 
  - DrawCircleCommand, EraseCircleCommand, DrawRectCommand, EraseRectCommand





### [ICommand.cs]

~~~~c#
    /// <summary>
    /// Command 인터페이스.
    /// Command 객체들이 해당 인터페이스를 상속받아 구현한다.
    /// </summary>
    public interface ICommand 
    {
        //Command 객체들이 구현할 공통 메소드.
        void Execute();

        //되돌리는 메소드.
        void Undo();
    }
~~~~



### [DrawCircleCommand.cs]

~~~~c#
    /// <summary>
    /// Command 객체.
    /// 원을 그리는 명령 객체.
    /// </summary>
    public class DrawCircleCommand : ICommand
    {
        // 리시버를 참조한다.
        // 명령 대상자가 누구인지 반드시 알아야한다.
        private CircleReceiver receiver;

        // 생성자를 통해 리시버를 전달. 
        // (리시버에게 특정 작업을 처리하라는 명령을 전달하기 위해 참조)
        public DrawCircleCommand(CircleReceiver receiver)
        {
            this.receiver = receiver;
        }

        /// <summary>
        /// 모든 명령(Command)는 Execute()메소드 호출로 실행한다.
        /// </summary>
        public void Execute()
        {
            receiver.Draw();
        }

        public void Undo()
        {
            receiver.Erase();
        }
    }
~~~~



### [EraseCircleCommand.cs]

~~~~c#
    /// <summary>
    /// Command 객체.
    /// 원을 지우는 명령 객체.
    /// </summary>
    public class EraseCircleCommand : ICommand
    {
        private CircleReceiver receiver;

        public EraseCircleCommand(CircleReceiver receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            receiver.Erase();
        }

        public void Undo()
        {
            receiver.Draw();
        }
    }
~~~~



### [DrawRectCommand.cs]

```c#
    /// <summary>
    /// Command 객체.
    /// 네모를 그리는 명령 객체.
    /// </summary>
    public class DrawRectCommand : ICommand
    {
        private RectReceiver receiver;

        public DrawRectCommand(RectReceiver receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            receiver.Draw();
        }

        public void Undo()
        {
            receiver.Erase();
        }
    }
```



### [EraseRectCommand.cs]

```c#
    /// <summary>
    /// Command 객체.
    /// 네모를 지우는 명령 객체.
    /// </summary>
    public class EraseRectCommand : ICommand
    {
        private RectReceiver receiver;

        public EraseRectCommand(RectReceiver receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            receiver.Erase();
        }

        public void Undo()
        {
            receiver.Draw();
        }
    }
```



### [DrawingInvoker.cs]

```c#
    /// <summary>
    /// Invoker.
    /// Command 객체에게 작업을 요청한다.
    /// </summary>
    public class DrawingInvoker 
    {
        //Command 객체를 관리한다.
        private Stack<ICommand> commandStack;

        public DrawingInvoker()
        {
            commandStack = new Stack<ICommand>();
        }

        //Execute를 호출하면 해당 Command를 Stack에 쌓고, Execute를 실행한다.
        public void Execute(ICommand command)
        {
            commandStack.Push(command);
            command.Execute();
        }

        public void Undo()
        {
            commandStack.Pop().Undo();
        }
    }
```



### [MainProgram.cs]

```c#
    /// <summary>
    /// 클라이언트 객체.
    /// </summary>
    public class MainProgram : MonoBehaviour
    {
        void Main()
        {
            //작업을 요청하기 위해 Invoker 생성.
            DrawingInvoker drawing = new DrawingInvoker();

            //원을 그린다. (3,5)
            CircleReceiver circleReceiver = new CircleReceiver(3, 5);
            drawing.Execute(new DrawCircleCommand(circleReceiver));

            //네모를 그린다. (123)
            RectReceiver rectReceiver = new RectReceiver(12, 3);
            drawing.Execute(new DrawRectCommand(rectReceiver));

            //네모를 지운다. (12,3)
            drawing.Execute(new EraseRectCommand(rectReceiver));

            //네모를 그린다. (12,3)
            drawing.Execute(new DrawRectCommand(rectReceiver));

            //원을 그린다. (1,1)
            drawing.Execute(new DrawCircleCommand(new CircleReceiver(1, 1)));

            Debug.Log("==========================");

            drawing.Undo(); //원을 지우고 (1,1)
            drawing.Undo(); //네모를 지우고(12,3)
            drawing.Undo(); //네모를 그리고(12,3)

            drawing.Execute(new DrawRectCommand(rectReceiver));
        }
    }
```



---


### [실행 결과]

	원을 그립니다. (3, 5)
	네모를 그립니다. (12, 3)
	네모를 지웁니다. (12, 3)
	네모를 그립니다. (12, 3)
	원을 그립니다. (1, 1)
	==========================
	원을 지웁니다. (1, 1)
	네모를 지웁니다. (12, 3)
	네모를 그립니다. (12, 3)



## 5. 참고 사이트

- http://haloper.tistory.com/15



[잘 정리된 문서 URL]

- https://github.com/KWSStudy/DesignPartterns/wiki/%EC%BB%A4%EB%A7%A8%EB%93%9C-%ED%8C%A8%ED%84%B4