# Bridge Pattern

1. 추상클래스를 구현클래스에서 분리해서 서로 독립적일 수 있도록 한다.

(이말인 즉슨, 추상클래스끼리 상속이나 extedns/implements 를 하고, 구현클래스 끼리 extedns/implements를 한다.)

서로 독립적일때, 확장/수정 등에 용이하다.

```java
/** "Implementor" */
interface DrawingAPI {
    public void drawCircle(final double x, final double y, final double radius);
}

/** "ConcreteImplementor"  1/2 */
class DrawingAPI1 implements DrawingAPI {
    public void drawCircle(final double x, final double y, final double radius) {
        System.out.printf("API1.circle at %f:%f radius %f\n", x, y, radius);
    }
}

/** "ConcreteImplementor" 2/2 */
class DrawingAPI2 implements DrawingAPI {
    public void drawCircle(final double x, final double y, final double radius) {
        System.out.printf("API2.circle at %f:%f radius %f\n", x, y, radius);
    }
}

/** "Abstraction" */
abstract class Shape {
    //Abstraction에 Implementor를 필드로 가지고 작업한다!(다리 역할)
    protected DrawingAPI drawingAPI;

    protected Shape(final DrawingAPI drawingAPI){
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw();                                 // low-level
    public abstract void resizeByPercentage(final double pct);   // high-level
}

/** "Refined Abstraction" */
class CircleShape extends Shape {
    private double x, y, radius;
    public CircleShape(final double x, final double y, final double radius, final DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;  this.y = y;  this.radius = radius;
    }

    // low-level i.e. Implementation specific
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }
    // high-level i.e. Abstraction specific
    public void resizeByPercentage(final double pct) {
        //drawingAPI(구현클래스 쪽)에 접근하지 않고 추상클래스에서만의 작업
        //생성할때, 받은 radius만으로 작업
        //구현 클래스에 상속될 내용이 아니라서, Abstraction 쪽에만 수정해 주면 된다.
        radius *= (1.0 + pct/100.0);
    }
}

/** "Client" */
class BridgePattern {
    public static void main(final String[] args) {
        Shape[] shapes = new Shape[] {
            new CircleShape(1, 2, 3, new DrawingAPI1()),
            new CircleShape(5, 7, 11, new DrawingAPI2())
        };

        for (Shape shape : shapes) {
            shape.resizeByPercentage(2.5);
            shape.draw();
        }
    }
}
```