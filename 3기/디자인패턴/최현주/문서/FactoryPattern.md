# [생성 패턴] Factory Pattern 

<br />

## 1. Factory Pattern 정의
- 객체를 생성하기 위한 인터페이스를 정의, 어떤 클래스의 인스턴스를 만들지는 서브 클래스에서 결정
- 동일한 인터페이스를 준수하는 클래스들을 생성

<br />

## 2. Factory Pattern의 사용 이유

- 객체를 생성할 때 매번 new를 사용하여 생성하지않고, 유연하게 사용 가능

<br />

## 3. 장/단점

#### [장점]
- 객체를 생성하기 위한 별도의 객체 생성 클래스가 불필요
- 객체 생성을 캡슐화할 수 있다.

#### [단점]
- 생성될 객체들의 클래스들이 모두 생성하는 메소드 Create()를 구현해야한다.

<br />

## 4. 코드 설명

- 객체를 생성하기 위한 추상화 클래스(Enemy)를 정의해놓고, 이를 상속받은 서브 클래스(Slime, Boss)에서 각자 처리한다,





### [Enemy.cs] 

- **객체를 생성**하기 위한 Abstract 클래스 (공통 속성을 정의)

~~~~
 namespace FactoryMethodPattern
{
    public enum EnemyType
    {
        Slime,
        Boss,
    }

    public abstract class Enemy
    {
        protected string name;

        protected EnemyType type;

        protected int hp;
        protected int exp;

        protected void Attack()
        {
            Debug.Log("공격한다.");
        }

        protected string GetLog()
        {
            return string.Format("{0} 등장 !! ", name);
        }
    }
}
~~~~
<br />

### [Boss.cs] 

- Enemy를 상속받아 보스 몬스터의 **생성 부분**을 구현한다.

~~~~
namespace FactoryMethodPattern
{
    public class Boss : Enemy
    {
        public Boss()
        {
            type = EnemyType.Boss;

            name = "보스 몬스터";
            hp = 1000;
            exp = 85;

            Debug.Log(GetLog());
        }
    }
}
~~~~
<br />

### [Slime.cs]

- Enemy를 상속받아 슬라임의 **생성 부분**을 구현한다.

~~~~
   namespace FactoryMethodPattern
{
    public class Slime : Enemy
    {
        public Slime()
        {
            type = EnemyType.Slime;

            name = "슬라임";
            hp = 200;
            exp = 15;

            Debug.Log(GetLog());
        }
    }
}

~~~~
<br />

### [EnemyGenerator.cs]

- 객체를 생성하기위한 Abstract 클래스

~~~~
   
namespace FactoryMethodPattern
{
    public abstract class EnemyGenerator
    {
        protected List<Enemy> enemyList = new List<Enemy>();

        // Factory Method.
        public abstract void CreateEnemys();
    }
}
~~~~

<br />

### [EasyMapGenerator.cs]

- Generator를 상속받아 객체를 생성하는 서브 클래스, 원하는 필요한 객체를 생성한다.

~~~~

namespace FactoryMethodPattern
{
    public class EasyMapGenerator : EnemyGenerator
    {
        public override void CreateEnemys()
        {
            enemyList.Add(new Slime());
            enemyList.Add(new Slime());
            enemyList.Add(new Slime());
            enemyList.Add(new Boss());
        }
    }
}

~~~~

<br />

### [HardMapGenerator.cs]

~~~~

namespace FactoryMethodPattern
{
    public class HardMapGenerator : EnemyGenerator
    {
        public override void CreateEnemys()
        {
            enemyList.Add(new Boss());
            enemyList.Add(new Boss());
            enemyList.Add(new Boss());
            enemyList.Add(new Slime());
        }
    }
}

~~~~~

<br />

### [MainProgram.cs]

~~~~

namespace FactoryMethodPattern
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            Debug.Log("---------- Easy Map 몬스터 생성 ----------");
            EnemyGenerator easyGenerator = new EasyMapGenerator();
            easyGenerator.CreateEnemys();

            Debug.Log("---------- Hard Map 몬스터 생성 ----------");
            EnemyGenerator hardGenerator = new HardMapGenerator();
            hardGenerator.CreateEnemys();
        }
    }
}

~~~~~

<br />

### [실행 결과]

	---------- Easy Map 몬스터 생성 ----------
	슬라임 등장 !! 
	슬라임 등장 !! 
	슬라임 등장 !! 
	보스 몬스터 등장 !!


	---------- Hard Map 몬스터 생성 ----------
	보스 몬스터 등장 !!
	보스 몬스터 등장 !!
	보스 몬스터 등장 !!
	슬라임 등장 !! 

<br />

## 5. 참고 사이트
- <http://codepump.tistory.com/26>