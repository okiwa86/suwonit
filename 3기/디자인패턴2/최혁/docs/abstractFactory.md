# Abstract Factory 패턴

## 왜쓸까

1. 구현클래스를 정의하지 않고 인터페이스만 미리 설계해 작성이 가능하다.

1. 캡슐화하는 계층 구조를 가질 수 있다. 다양한 플랫폼 / 제품 구조로 가능 (사용자는 생성과정에 대해 독립적이다.)

1. new 연산자는 위험한것으로 간주. (안에 내용을 사용자는 모르지만 싱글턴으로 호출해도 될 듯)

## 예제 소스

```java
// class CPU
abstract class CPU {}
// class EmberCPU
class EmberCPU extends CPU {}
// class EnginolaCPU
class EnginolaCPU extends CPU {}

// class MMU
abstract class MMU {}
// class EmberMMU
class EmberMMU extends MMU {}
// class EnginolaMMU
class EnginolaMMU extends MMU {}

// class EmberFactory
class EmberToolkit extends AbstractFactory {
    @Override
    public CPU createCPU() {
        return new EmberCPU();
    }

    @Override
    public MMU createMMU() {
        return new EmberMMU();
    }
}

// class EnginolaFactory
class EnginolaToolkit extends AbstractFactory {
    @Override
    public CPU createCPU() {
        return new EnginolaCPU();
    }

    @Override
    public MMU createMMU() {
        return new EnginolaMMU();
    }
}

enum Architecture {
    ENGINOLA, EMBER
}

abstract class AbstractFactory {
    private static final EmberToolkit EMBER_TOOLKIT = new EmberToolkit();
    private static final EnginolaToolkit ENGINOLA_TOOLKIT = new EnginolaToolkit();

    // Returns a concrete factory object that is an instance of the
    // concrete factory class appropriate for the given architecture.
    static AbstractFactory getFactory(Architecture architecture) {
        AbstractFactory factory = null;
        switch (architecture) {
            case ENGINOLA:
                factory = ENGINOLA_TOOLKIT;
            case EMBER:
                factory = EMBER_TOOLKIT;
        }
        return factory;
    }

    public abstract CPU createCPU();

    public abstract MMU createMMU();
}

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory(Architecture.EMBER);
        CPU cpu = factory.createCPU();
        MMU mmu = factory.createMMU();
    }
}
```