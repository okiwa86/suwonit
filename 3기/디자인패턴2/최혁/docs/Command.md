# Command 패턴

## 왜쓸까

method를 실행하면, 메소드의 return 값은 남지만, 일의 이력은 남지 않는다.

명령을 하나의 Object로 관리하여,
명령의 집합을 저장해두면, 다양하게 활용이 가능하다.

ex: 재실행, 명령 이력 보기, 매크로만들기 등등

## 역할

### Command(명령)의 역할

명령의 인터페이스(API)를 정의 합니다.

### ConcreteCommand(구체적 명령)의 역할

Command 역할의 인터페이스를 실제로 구현하고 있는 역할입니다.

### Receiver(수신자)의 역할

Command 역할이 명령을 실행할 때, 대상이 되는 역할 입니다.

명령을 받아들이는 수신자라고 불러도 될 것 입니다.

### Client(의뢰자)의 역할

ConcreteCommand 역할을 생성하고, 그 사이에 Receiver 역할을 할당합니다.

### Invoker(기동자)의 역할

명령의 행동을 개시하는 역할입니다.

Command역할에서 정의 되는 인터페이스를 호출하는 역할이 됩니다.

### 예제

```java
    import java.util.List;
    import java.util.ArrayList;
    import java.util.Iterator;

    /** The Command interface */
    interface Command {
        void execute();
    }

    /** The Invoker class */
    class Switch {
        private List<Command> history = new ArrayList<Command>();

        public void storeAndExecute(final Command cmd) {
            this.history.add(cmd); // optional
            cmd.execute();
        }

        public void reTryAll() {
            System.out.println("---reTryAll---");
            Iterator<Command> it = history.iterator();
            while (it.hasNext()) {
                it.next().execute();
            }
        }

    }

    /** The Receiver class */
    class Light {

        public void turnOn() {
            System.out.println("The light is on");
        }

        public void turnOff() {
            System.out.println("The light is off");
        }
    }

    /** The Command for turning on the light - ConcreteCommand #1 */
    class FlipUpCommand implements Command {
        private Light theLight;

        public FlipUpCommand(final Light light) {
            this.theLight = light;
        }

        @Override // Command
        public void execute() {
            theLight.turnOn();
        }
    }

    /** The Command for turning off the light - ConcreteCommand #2 */
    class FlipDownCommand implements Command {
        private Light theLight;

        public FlipDownCommand(final Light light) {
            this.theLight = light;
        }

        @Override // Command
        public void execute() {
            theLight.turnOff();
        }
    }

    /* The test class or client */
    public class PressSwitch {
        public static void main(final String[] arguments) {

            // Receiver
            final Light lamp = new Light();

            // Command
            final Command switchUp = new FlipUpCommand(lamp);
            final Command switchDown = new FlipDownCommand(lamp);

            // Invoker
            final Switch mySwitch = new Switch();

            mySwitch.storeAndExecute(switchUp);
            mySwitch.storeAndExecute(switchUp);
            mySwitch.storeAndExecute(switchDown);
            mySwitch.storeAndExecute(switchDown);
            mySwitch.storeAndExecute(switchUp);

            mySwitch.reTryAll();

        }
    }
```

### 실행결과

```
The light is on
The light is on
The light is off
The light is off
The light is on
---reTryAll---
The light is on
The light is on
The light is off
The light is off
The light is on
```