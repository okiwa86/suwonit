# Composite Pattern 



## 1. Composite Pattern 
- 객체들의 관계를 `트리 구조`로 구성하여 표현 (부분 - 계층)
- 사용자는 단일 객체, 복합 객체 모두 `동일하게 처리`한다.





## 2. UML Diagram
![uml](https://www.codeproject.com/KB/architecture/1197606/compositeGof.gif)



## 3. 장/단점

### [장점]
- 단일 / 복합 객체에 대해 일관성있게 코드 작성이 가능하다.
- 새로운 요소를 쉽게 추가할 수 있다.


### [단점]

- 시스템을 지나치게 일반화한다.

- 복합체에 구성요소의 제약을 가하기 힘들다.

  ​

## 4. 코드 설명

- 간단한 파일 시스템을 예시로 든다.
- Directory 안에 다른 Directory 혹은 File이 있을 수 있다. (Leaf-File, Composite-Directory)



### [Entry.cs]

~~~~c#
    /// <summary>
    /// Component 역할
    /// Composite(복합 객체)를 위한 추상화 클래스
    /// </summary>
    public abstract class Entry
    {
        //공통적으로 파일 or 디렉토리의 이름,사이즈.
        public abstract string GetName();
        public abstract int GetSize();

        /// <summary>
        /// 복합 개체(Directory)에서만 필요한 메서드.
        /// </summary>
        /// <param name="entry"></param>
        public virtual void Add(Entry entry)
        {
        }

        //공통으로 사용되는 출력 method.
        public void Print()
        {
            PrintList("");
        }

        public abstract void PrintList(string prefix);

        public string ToString()
        {
            return string.Format("{0} ({1})", GetName(), GetSize());
        }
    }
~~~~


### [File.cs]

~~~~c#
    /// <summary>
    /// Leaf 역할 (단일 객체)
    /// Entry(Component)를 구현하는 클래스.
    /// </summary>
    public class File : Entry
    {
        private string name;
        private int size;

        public File(string name, int size)
        {
            this.name = name;
            this.size = size;
        }

        public override string GetName()
        {
            return name;
        }

        public override int GetSize()
        {
            return size;
        }

        public override void PrintList(string prefix)
        {
            Debug.Log(prefix + "/" + this.name);
        }
    }
~~~~


### [Directory.cs]

~~~~c#
    /// <summary>
    /// Composite 역할
    /// Entry(Component)를 저장, Component를 관리하기위한 메소드를 가지고있다.
    /// </summary>
    public class Directory : Entry
    {
        private string name;
        private List<Entry> entryList = new List<Entry>();

        public Directory(string name)
        {
            this.name = name;
        }

        public override string GetName()
        {
            return name;
        }

        //단일 객체들의 크기를 더하여 사이즈를 계산.
        public override int GetSize()
        {
            int totalSize = 0;

            for (int i = 0; i < entryList.Count; i++)
            {
                totalSize += entryList[i].GetSize();
            }

            return totalSize;
        }

        public override void Add(Entry entry)
        {
            entryList.Add(entry);
        }

        public override void PrintList(string prefix)
        {
            Debug.Log(prefix + "/" + this.name);

            for (int i = 0; i < entryList.Count; i++)
            {
                Entry entry = entryList[i];
                entry.PrintList(prefix + "/" + name);
            }
        }
    }
~~~~



---

### [MainProgram.cs]

- 최상위의 Root 디렉토리를 생성
  - Root 디렉토리 내에 bin, temp, user 디렉토리를 생성
    - bin 디렉토리 내에 폴더를 만들고, 각 폴더 안에 파일을 만드는 트리구조를 표현.

~~~~c#
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            Directory rootDir = new Directory("root");

            Directory binDir = new Directory("bin");
            Directory tempDir = new Directory("temp");
            Directory userDir = new Directory("user");

            rootDir.Add(binDir);
            rootDir.Add(tempDir);
            rootDir.Add(userDir);

            binDir.Add(new File("bin_file_1", 100));
            binDir.Add(new File("bil_file_2", 200));

            Directory KimDir = new Directory("Kim");
            Directory ChoiDir = new Directory("Choi");
            Directory ShinDir = new Directory("Shin");

            userDir.Add(KimDir);
            userDir.Add(ChoiDir);
            userDir.Add(ShinDir);

            KimDir.Add(new File("data.xml", 50));
            KimDir.Add(new File("composite.cs", 100));

            ChoiDir.Add(new File("r&d.doc", 200));
            ChoiDir.Add(new File("bae_platform.pptx", 150));

            ShinDir.Add(new File("clip.mat", 400));

            rootDir.Print();
        }
    }
~~~~




### [실행 결과]

	/root
	/root/bin
	/root/bin/bin_file_1
	/root/bin/bil_file_2
	/root/temp
	/root/user
	/root/user/Kim
	/root/user/Kim/data.xml
	/root/user/Kim/composite.cs
	/root/user/Choi
	/root/user/Choi/r&d.doc
	/root/user/Choi/bae_platform.pptx
	/root/user/Shin
	/root/user/Shin/clip.mat



## 5. 참고 사이트

- http://jellyms.kr/596