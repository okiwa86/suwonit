# Strategy Pattern (전략 패턴)



## 1. Strategy Pattern 
- **`알고리즘의 캡슐화!!!`**
- 알고리즘을 인터페이스로 정의해놓고, 구현 클래스별로 캡슐화하여 **`교체 사용`**이 가능
- 같은 문제를 해결하는 방법이 여러가지일 경우 사용



## 2. UML Diagram
![uml](http://www.dofactory.com/images/diagrams/net/strategy.gif)


## 3. 장/단점

### [장점]
- 새로운 알고리즘을 추가하기가 쉽다. (확장성 있음)
- 수정작업이 필요할경우 클라이언트와는 독립적으로 해당 알고리즘만 수정하면 된다.




## 4. 코드 설명

### [Person.cs]

- Custom Data 클래스

~~~~c#
    public class Person
    {
        public string name;
        public int age;
        public int tall;

        public Person(string name, int age, int tall)
        {
            this.name = name;
            this.age = age;
            this.tall = tall;
        }
    }
~~~~


### [SortStrategy.cs]

~~~~c#
    /// <summary>
    /// Strategy 추상화 클래스 (알고리즘의 추상 객체)
    /// </summary>
    public abstract class SortStrategy
    {
        //공통 알고리즘 메서드를 정의만 해놓는다.
        public abstract void Sort(List<Person> list);
    }
~~~~


### [SortByAge.cs]

~~~~c#
    /// <summary>
    /// ConcreteStrage 클래스, 각각 알고리즘을 구현한다.
    /// </summary>
    public class SortByAge : SortStrategy
    {
        public override void Sort(List<Person> list)
        {
            Debug.Log("===== 나이순으로 정렬 =====");

            list.Sort((x, y) => x.age.CompareTo(y.age));
        }
    }
~~~~


### [SortByName.cs]

```c#
    public class SortByName : SortStrategy
    {
        public override void Sort(List<Person> list)
        {
            Debug.Log("===== 이름순으로 정렬 =====");

            list.Sort((x, y) => x.name.CompareTo(y.name));
        }
    }
```



### [SortByTall.cs]

```c#
     public class SortByTall : SortStrategy
    {
        public override void Sort(List<Person> list)
        {
            Debug.Log("===== 키순으로 정렬 =====");

            list.Sort((x, y) => x.tall.CompareTo(y.tall));
        }
    }
```



### [SortedList.cs]

```c#
    /// <summary>
    /// Context 클래스
    /// Strategy의 구상객체를 소유, 해당 객체의 메소드를 실행시킨다.
    /// </summary>
    public class SortedList
    {
        private SortStrategy strategy;

        public SortedList(SortStrategy strategy)
        {
            this.strategy = strategy;
        }

        public void Sort(List<Person> list)
        {
            strategy.Sort(list); 
        }

        public void PrintInfo(List<Person> list)
        {
            for(int i=0; i<list.Count; i++)
            {
                Debug.Log(string.Format("이름 : {0}, 나이 : {1}, 키 : {2}", 
                                        list[i].name, list[i].age, list[i].tall));
            }
        }
    }
```



### [MainProgram.cs]

~~~~c#
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //Person 정보 등록.
            List<Person> personList = new List<Person>();

            personList.Add(new Person("yoon jungsoo", 40, 155));
            personList.Add(new Person("kang dongwon", 35, 190));
            personList.Add(new Person("suzy", 26, 170));

            Debug.Log("===== 정렬 전 =====");

            for (int i = 0; i < personList.Count; i++)
            {
                Debug.Log(string.Format("이름 : {0}, 나이 : {1}, 키 : {2}", 
                    personList[i].name, personList[i].age, personList[i].tall));
            }

            //Age.
            SortedList sortedList = new SortedList(new SortByAge());

            sortedList.Sort(personList);
            sortedList.PrintInfo(personList);

            //Name.
            sortedList = new SortedList(new SortByName());

            sortedList.Sort(personList);
            sortedList.PrintInfo(personList);

            //Tall.
            sortedList = new SortedList(new SortByTall());

            sortedList.Sort(personList);
            sortedList.PrintInfo(personList);
        }
~~~~




### [실행 결과]

	===== 정렬 전 =====
	이름 : yoon jungsoo, 나이 : 40, 키 : 163
	이름 : kang dongwon, 나이 : 35, 키 : 190
	이름 : suzy, 나이 : 26, 키 : 170
	
	===== 나이순으로 정렬 =====
	이름 : suzy, 나이 : 26, 키 : 170
	이름 : kang dongwon, 나이 : 35, 키 : 190
	이름 : yoon jungsoo, 나이 : 40, 키 : 163
	
	===== 이름순으로 정렬 =====
	이름 : kang dongwon, 나이 : 35, 키 : 190
	이름 : suzy, 나이 : 26, 키 : 170
	이름 : yoon jungsoo, 나이 : 40, 키 : 163
	
	===== 키순으로 정렬 =====
	이름 : yoon jungsoo, 나이 : 40, 키 : 163
	이름 : suzy, 나이 : 26, 키 : 170
	이름 : kang dongwon, 나이 : 35, 키 : 190

---



## 5. Strategy와 Bridge 패턴의 차이

- Strategy 패턴 : 같은 결과이지만 다른 알고리즘으로 수행할 때 사용  
  - ex. 정렬 방법을 선택할때 (퀵, 버블, 선택 등...)
- Bridge 패턴 : 하나의 동작을 추상화하여 서로 다른 구조를 수행할 때 사용
  - ex. 무기를 사용함을 추상화로 정의해놓고, 각 클래스별 세부 구현.



<참고>

http://devnote2.tistory.com/entry/Strategy-pattern-VS-Brigde-Pattern