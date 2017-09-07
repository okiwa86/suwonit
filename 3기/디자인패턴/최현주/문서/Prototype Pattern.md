# [생성패턴] Prototype Pattern (원형 패턴)

<br />

## 1. Prototype Pattern 정의
- 미리 만들어진 개체를 복사하여 개체를 생성하는 패턴
- 미리 만들어진 개체를 원형 개체라고 부름.

<br />

## 2. UML Diagram
![uml](http://www.dofactory.com/images/diagrams/net/prototype.gif)
![uml](http://www.dotnettricks.com/img/designpatterns/prototype1.png)

<br />
## 3. Prototype 의 사용 이유

- 종류가 너무 많아서 각 클래스로 정리할 수 없는 경우  

<br />

## 4. 장/단점

### [장점]
- 객체를 생성하기 위한 별도의 객체 생성 클래스가 불필요
- 서브 클래스의 수를 줄일 수 있다.

### [단점]
- 생성될 객체들의 클래스들이 모두 Clone() 메소드를 구현해야한다.

<br />
## 5. 코드 설명

### [IEmployee.cs]

~~~~
 public interface IEmployee
    {
        IEmployee Clone();
        string GetDetailInfo();
    }
~~~~
<br />

### [Developer.cs]

~~~~
    public class Developer : IEmployee
    {
        public string Name { get; set; }
        public string Role { get; set; }
        public string langauge { get; set; }

        public IEmployee Clone()
        {
            return (IEmployee)MemberwiseClone();
        }

        public string GetDetailInfo()
        {
            return string.Format("Name : {0}, Role : {1}, langauge : {2}", Name, Role, langauge);
        }
    }
~~~~
<br />

### [Designer.cs]

~~~~
    public class Designer : IEmployee
    {
        public string Name { get; set; }
        public string Role { get; set; }
        public string Tool { get; set; }

        public IEmployee Clone()
        {
            return (IEmployee)MemberwiseClone();
        }

        public string GetDetailInfo()
        {
            return string.Format("Name : {0}, Role : {1}, Tool : {2}", Name, Role, Tool);
        }
    }
~~~~
<br />

### [MainProgram.cs]

~~~~
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            Developer dev = new Developer();
            dev.Name = "Sam";
            dev.Role = "Team Leader";
            dev.langauge = "C++";

            Developer copyDev = dev.Clone() as Developer;
            copyDev.Name = "Jenny";
            copyDev.Role = "Programmer";

            Debug.Log(dev.GetDetailInfo());
            Debug.Log(copyDev.GetDetailInfo());

            Designer designer = new Designer();
            designer.Name = "Tom";
            designer.Role = "Designer ";
            designer.Tool = "PhotoShop";

            Designer copyDesigner = designer.Clone() as Designer;
            copyDesigner.Tool = "Grace";
            copyDesigner.Tool = "illust";

            Debug.Log(designer.GetDetailInfo());
            Debug.Log(copyDesigner.GetDetailInfo());
        }
    }
~~~~


<br />

### [실행 결과]

	Name : Sam, Role : Team Leader, langauge : C++
	Name : Jenny, Role : Team Leader, langauge : C++
	Name : Tom, Role : Designer , Tool : PhotoShop
	Name : Tom, Role : Designer , Tool : illust

<br />

## 6. 참고 사이트
- <http://www.dotnettricks.com/learn/designpatterns/prototype-design-pattern-dotnet>
