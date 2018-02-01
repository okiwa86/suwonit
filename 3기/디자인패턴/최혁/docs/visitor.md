# Visitor 패턴

![UML](https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Visitor_design_pattern.svg/500px-Visitor_design_pattern.svg.png)

## 왜쓸까

Visitor 패턴의 목적은 처리를 데이터 구조에서 분리하는 일입니다.

데이터 구조는 정해져있고, Visitor의 확장은 용이합니다.

예제의 File/Directory 처럼 한번 정해진 스펙이 크게 변경되지 않지만,

접근해서 구현해야할 모양이나 방식이 제각각일때 사용하면 좋을 듯 합니다.

Composite 패턴 업그레이드 & chain of respoonsibility 패턴과 반대되는 패턴으로 이해.

## 코드로 보자

### Visitor

visit 다형성(오버로드) 파라미터를 달리해서 동일하게 사용합니다.

```java
    public abstract class Visitor {
        public abstract void visit(File file);
        public abstract void visit(Directory directory);
    }
```

### Element

Visitor를 받아들이도록 Interface 작성

```java
    public interface Element {
        public abstract void accept(Visitor v);
    }
```

### Entry

File과 Directory가 상속할 추상클래스

Template 패턴을 이용해 toString() 구현 하도록함.

getSize()가 서로 다르게 동작 하지만, method를 같이 써서 한 그릇에 담을수 있다.

Compoite 패턴과 다른점

1. Element Interface를 구현한다는 점
1. printList 를 작성하지 않고 Vistior가 직접 Print하도록 구현.

```java
    public abstract class Entry implements Element {

        // add Directory에만
        public Entry add(Entry entry) throws FileTreatmentException {
            throw new FileTreatmentException();
        }
        // Iterator는 Directory에만
        public Iterator<?> iterator() throws FileTreatmentException {
            throw new FileTreatmentException();
        }

        // Composite 패턴에서는 Entry에서 PrintList 구현하도록 추상화함.
        // public void printList() {
        //     printList("");
        // }

        // protected abstract void printList(String prefix);

        /**
        * getName , getSize 는 추상클래스 이지만,
        * 하위클래스에서 이 두 메소드를 구현하여
        * toString에서 호출하고있습니다.(Template Pattern)
        */
        public String toString() {
            return getName() + " (" + getSize() + ")";
        }
        public abstract String getName();
        public abstract int getSize();
    }
```

### File

Composite패턴에서는 printList를 File내부에 구현해 자기 위치를 Print하도록 구현.

Visitor에서는 File은 name하고 size만 가지고 있고,

Vistor가 방문하도록 accept(Visitor v){ v.visit(this); } 문을 열어주면된다.

자세한내용은 Visitor가 visit(File file) {} 이부분에서 구현해주면 된다.

```java
    public class File extends Entry {

        private String name;
        private int size;

        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }

        //  @Override
        //  protected void printList(String prefix) {
        //    System.out.println(prefix + "/" + this); // this == this.toString()
        //  }
    }

```

### Directory

File과 마찬가지로, Composite패턴에서는

printList를 구현해 Directory 내부를 재귀적으로 호출하도록 구현했지만,

Visitor에서는 구조와 처리를 분리한다.

Visitor에서는 Directory는 name하고 EntryList를 가지고 있고,

(Entry ArrayList는 Directory.add(new File())로 받을때 가지고 있다가 size구할때 loop를 돌며 구한다.)

Vistor가 방문하도록 accept(Visitor v){ v.visit(this); } 문을 열어주면된다.

자세한내용은 Visitor가 visit(Directory dir) {} 이부분에서 구현해주면 된다.

```java
    public class Directory extends Entry {
        private String name;
        private ArrayList<Entry> dir = new ArrayList<Entry>(); // 안에 담길 Entry List

        public Directory(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getSize() {
            int size = 0;
            Iterator<Entry> it = dir.iterator();
            while (it.hasNext()) {
                Entry entry = it.next();
                size += entry.getSize();
            }
            return size;
        }

        public Entry add(Entry entry) {
            dir.add(entry);
            return this;
        }

        public Iterator<Entry> iterator() {
            return dir.iterator();
        }

        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }

    // Compoiste 패턴에서는 직접 printList로 호출
    //  @Override
    //  protected void printList(String prefix) {
    //    // this == this.toString() { getName() + " (" + getSize() + ")"; }
    //    System.out.println(prefix + "/" + this);
    //    Iterator<Entry> it = directory.iterator();
    //    while (it.hasNext()) {
    //        Entry entry = (Entry) it.next();
    //        entry.printList(prefix + "/" + name);
    //    }
    //  }

    }
```

### ListVisitor

File과 Directory에 접근할때, 구현을 작성해준다.

필요하면 다른 Visitor 를 만들어서 다른 방식으로 표현할 수 있도록 확장이 용이하다.

(ex: full path 조회용 , size(byte -> MB), size(byte -> GB) 등등)

```java
    public class ListVisitor extends Visitor {
        private String currentDir = "";

        @Override
        public void visit(File file) {
            System.out.println(currentDir + "/" + file); // file == file.toString();
        }

        @Override
        public void visit(Directory directory) {
            System.out.println(currentDir + "/" + directory); // directory == directory.toString();
            String saveDir = currentDir;
            currentDir = currentDir + "/" + directory.getName();
            Iterator<Entry> it = directory.iterator();
            while (it.hasNext()) {
                Entry entry = it.next();
                entry.accept(this);
            }
            currentDir = saveDir;
        }
    }
```

### Exception

그냥 구현용..;

```java
    public class FileTreatmentException extends RuntimeException {

        private static final long serialVersionUID = -5703043221169701552L;

        public FileTreatmentException() {
        }

        public FileTreatmentException(String msg) {
            super(msg);
        }
    }
```

### Main

폴더 Tree

- root
  - bin
    - vi(file)
    - latex (file)
  - tmp
  - usr
    - Kim
      - diary.html
      - Composite.java
    - Lee
      - memo.txt
    - Park
      - game.doc
      - junk.mail

```java
    public class Main {

        public static void main(String[] args) {
            System.out.println("Making root entries...");
            Directory rootDir = new Directory("root");
            Directory binDir = new Directory("bin");
            Directory tmpDir = new Directory("tmp");
            Directory usrDir = new Directory("usr");

            rootDir.add(binDir);
            rootDir.add(tmpDir);
            rootDir.add(usrDir);
            binDir.add(new File("vi", 10000));
            binDir.add(new File("latex", 20000));
            // 방문 시작!
            rootDir.accept(new ListVisitor());

            System.out.println("");
            System.out.println("Making user entires...");
            Directory Kim = new Directory("Kim");
            Directory Lee = new Directory("Lee");
            Directory Park = new Directory("Park");

            usrDir.add(Kim);
            usrDir.add(Lee);
            usrDir.add(Park);

            Kim.add(new File("diary.html", 100));
            Kim.add(new File("Composite.java", 200));

            Lee.add(new File("memo.txt", 300));

            Park.add(new File("game.doc", 400));
            Park.add(new File("junk.mail", 500));
            // 방문 시작!
            rootDir.accept(new ListVisitor());
        }

    }

```

### 결과

```결과
Making root entries...
/root (30000)
/root/bin (30000)
/root/bin/vi (10000)
/root/bin/latex (20000)
/root/tmp (0)
/root/usr (0)

Making user entires...
/root (31500)
/root/bin (30000)
/root/bin/vi (10000)
/root/bin/latex (20000)
/root/tmp (0)
/root/usr (1500)
/root/usr/Kim (300)
/root/usr/Kim/diary.html (100)
/root/usr/Kim/Composite.java (200)
/root/usr/Lee (300)
/root/usr/Lee/memo.txt (300)
/root/usr/Park (900)
/root/usr/Park/game.doc (400)
/root/usr/Park/junk.mail (500)
```

## Visitor패턴 등장인물

### Visitor(방문자)

Visitor는 데이터 구조의 요소(ConcreteElement: File/Directory) 마다

visit 메소드를 선언합니다.

예제 : Visitor클래스

### ConcreteVisitor(구체적 방문자)

Visitor의 메소드를 구현합니다.

currentDir 필드가 변화했듯이, visit을 처리중에 내부상태가 변하기도 합니다.

예제 : ListVisitor 클래스

### Element(요소)

Visitor가 방문할 곳을 나타내는 역할.

accept(Visitor v)를 선언합니다.

예제 : Element

### ConcreteElement(구체적 요소)

예제 : File/Directory

### ObjectStructure(오브젝트 구조)

Element역할의 집합을 취급하는 역할을합니다.
ConcreteVisitor가 각각 Element에 접근할 수 있도록 구비하는 역할

예제에선 Directory클래스가 iterator를 준비해서 이 역할을 같이 맡고 있습니다.

## 알아두면 쓸만한 잡다한 지식사전

### 더블 디스패치(이중분리)

```java
Element.accept(Visitor v)
Visitor.visit(Element e)
```

서로 호출함으로써 재귀처럼 사용.

### The Open-Closed Principle(OCP)

Bertrand Meyer가 제시한 것으로 Robert C.Martin이 C++ Report(Jan. 1996)에 쓴 Engineering Notebook 이라는 컬럼에 정리되어 있습니다.

<https://www.cs.duke.edu/courses/fall07/cps108/papers/ocp.pdf>

<http://stg-tud.github.io/sedc/Lecture/ss15/3.3-OCP.pdf>

<https://ko.wikipedia.org/wiki/%EA%B0%9C%EB%B0%A9-%ED%8F%90%EC%87%84_%EC%9B%90%EC%B9%99>

기존의 클래스를 수정하지 않고 확장할 수 있도록 하는 것이 원칙!

확장은 열려 있고, 수정은 닫혀있다!
클래스를 설계할때 특별한이유가 없는 한 확장을 허용해야 합니다.
이유없이 확장을 금지해서는 안되며, 이것이 '확장에 대해서는 열려있다' 는 의미입니다.

그러나 확장을 할 때마다 기존의 클래스를 수정해야 하는 것도 곤란합니다.
확장을 해도 기존의 클래스는 수정할 필요가 없는 것이 '수정에 대해서는 닫혀있다'라는 의미입니다.

### ConcreteVisitor 역할의 추가는 간단하다

ListVisitor를 수정해서, 추가하면 쉽게 추가가 가능합니다.

(ex: full path 조회용 , size(byte -> MB), size(byte -> GB) 등등)

### ConcreteElement 역할의 추가는 곤란하다

ConcreteVisitor역할의 추가는 간단하지만,

Entry에 새로운 a 클래스가 생기면 Visitor에 visit(A a); 를 추가하고

각 ConcreteVisitor마다 구현을 작성해야 합니다.

### Visitor가 처리하기 위해서 무엇이 필요한가

데이터 구조의 요소에 대한 처리를 따로 분리해서 Visitor역할에게 맡깁니다.

이렇게 해서 데이터구조와 요소에 대한 처리를 분리할 수 있습니다.

이것은 그럴듯한 말이지만 Element 역할은 Visitor 역할에 대해서 충분한 정보를 공개할 필요가 있습니다.

예제에선 iterator 메소드를 제공할 필요가 있었습니다.

방문자는 데이터구조에 서 필요한 정보를 취득해서 동작합니다.

필요한 정보를 얻을 수 없으면 방문자는 제대로 일할 수 없습니다.

반면에 공개할 필요 없는 정보까지 공개하면, 미래의 데이터 구조를 개량하기 어렵게 됩니다.
