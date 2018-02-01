# Proxy 패턴



## 왜쓸까

프록시는 대리인이라는 의미입니다.

대리인이란 일을 해야할 본인을 대리인이 대신 해주는 것.

Lazy다 Lazy!

## 예제

| 역할          | 예제                   | 설명                                      |
| ----------- | -------------------- | --------------------------------------- |
| Subject     | Printable(Interface) | Proxy와 RealSubject가 동일시 되도록 Interface작성 |
| Proxy       | PrinterProxy         | 대리인의 역할                                 |
| RealSubject | Printer              | 실제 주체의 역할                               |
| Client      | Main                 | GoF책에서는 없는 역할입니다.                       |

### Printer.java (RealSubject)

실제 주체는 생성할때 오래걸린다.

```java
    public class Printer implements Printable {
        private String name;

        public Printer() {
            heavyJob("Printer 인스턴스를 생성 중");
        }

        public Printer(String name) {
            this.name = name;
            heavyJob("Printer 인스턴스(" + name + ")를 생성 중");
        }
        
        private void heavyJob(String msg) {
            System.out.println(msg);
            // 1초마다 . 하나 씩 찍기...
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(".");
            }
            System.out.println("완료.");
        }


        @Override
        public void setPrinterName(String name) {
            this.name = name;
        }

        @Override
        public String getPrinterName() {
            return name;
        }

        @Override
        public void print(String string) {
            System.out.println("=== " + name + " ===");
            System.out.println(string);

        }

    }
```

### Printable.java (Subject)

실체에서 Proxy와 공유할 메소드를 interface에 정의함.

```java
    public interface Printable {
        public abstract void setPrinterName(String name);
        public abstract String getPrinterName();
        public abstract void print(String string);
    }
```

### PrintProxy.java (Proxy)

```java
    public class PrinterProxy implements Printable {

        private String name; // 이름
        private Printer real; // 본인

        public PrinterProxy(String name) {
            this.name = name;
        }

        @Override
        public synchronized void setPrinterName(String name) { // 이름의 설정
            if (real != null) {
                real.setPrinterName(name); // 본인에게도 설정한다.
            }
            this.name = name;
        }

        @Override
        public String getPrinterName() { // 이름의 설정
            return name;
        }

        @Override
        public void print(String string) { // 표시
            realize();
            real.print(string);
        }

        private synchronized void realize() { // 본인을 생성
            if (real == null) {
                real = new Printer(name);
            }
        }

    }
```