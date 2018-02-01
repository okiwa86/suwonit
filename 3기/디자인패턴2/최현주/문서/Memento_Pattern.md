# Memento Pattern (메멘토 패턴) 



## 1. Memento Pattern 
- [행위패턴]
- 객체의 상태 **정보를 저장 및 복원**하는 패턴
- 캡슐화에 위배되지 않는 패턴
- ex) 바둑/장기 물리기 기능, 게임에서 죽었을 때 해당 캐릭터의 레벨부터 이어서 시작하는 등...



## 2. UML Diagram



![uml](https://1.bp.blogspot.com/-a0bb9PEls1E/VzRIkn9_keI/AAAAAAAAByU/ZzG88OLKAjI0cvi1AEKnnGjcndsoZJAJQCLcB/s640/Memento_design_pattern%2B%25281%2529.png)



- Originator : 우리가 저장하고자 하는 *`상태를 갖는 객체`*
- Memento : Originator의 상태를 **저장하는 또 다른 객체**
- Caretaker(관리인) : Memento를 관리한다. (Originator 객체를 저장 및 복원을 시켜주는 객체)



## 3. 장/단점

### [장점]


- 복잡한 계산없이 원하는 시점의 객체 상태로 복원이 가능하다.
- 클라이언트는 Originator 객체의 내부상태를 직접 접근하여 저장/관리하는 것을 방지해준다.




### [단점]

- 저장할 데이터가 많을 경우에는 비용이 커진다.
  - 내부 상태를 모두 저장해야하기 때문에, 내부 정보가 많은 경우에는 부적절!
- 클라이언트는 Memento 객체에 얼마나 많은 정보가 저장되어있는지 알 수 없다.



## 4. 코드 설명

- 플레이어 객체(Player)의 데이터를 Memento 클래스(PlayerMemento)를 통해서 저장하고,
- 데이터를 관리하는 클래스(PlayerCareTaker)에서 데이터를 복원한다.





### [Player.cs]

~~~~c#
    /// <summary>
    /// Originator 클래스
    /// 저장하고자 하는 데이터 객체 클래스.
    /// </summary>
    public class Player
    {
        private int level;
        private int hp;
        private DateTime playTime;

        #region get,set method
        public int Level
        {
            set
            {
                level = value;
                Debug.Log("Level : " + level);
            }
            get
            {
                return level;
            }
        }

        public int HP
        {
            set
            {
                hp = value;
                Debug.Log("HP : " + hp);
            }
            get
            {
                return hp;
            }
        }

        public DateTime PlayTime
        {
            set
            {
                playTime = value;
                Debug.Log("PlayTime : " + playTime.ToString("yyyy-MM-dd"));
            }
            get
            {
                return playTime;
            }
        }
        #endregion

        /// <summary>
        /// Memento 클래스(PlayerMemento)를 이용하여 Originator 객체(Player)의 정보를 저장한다.
        /// </summary>
        /// <returns>Memento에 데이터를 저장하고, Memento를 반환</returns>
        public PlayerMemento SaveDataMemento()
        {
            Debug.Log("======== 여기까지 데이터 저장 ========");
            return new PlayerMemento(level, hp, playTime);
        }

        /// <summary>
        /// Memento 클래스로부터 데이터를 복원한다.
        /// Originator 데이터에 Memento에 저장한 데이터를 넣음.
        /// </summary>
        /// <param name="memento"></param>
        public void RestoreDataMemento(PlayerMemento memento)
        {
            Level = memento.Level;
            HP = memento.Hp;
            PlayTime = memento.PlayTime;

            Debug.Log("======== 데이터 복원 완료! ========");
        }
    }
~~~~



### [PlayerMemento.cs]

~~~~c#
    /// <summary>
    /// Memento 클래스.
    /// Originator 객체(Player)를 저장하기 위한 클래스.
    /// </summary>
    public class PlayerMemento
    {
        public int Level { get; set; }
        public int Hp { get; set; }
        public DateTime PlayTime { get; set; }

        //생성자를 이용해 저장할 객체의 데이터를 저장.
        public PlayerMemento(int level, int hp, DateTime time)
        {
            Level = level;
            Hp = hp;
            PlayTime = time;
        }
    }
~~~~



### [PlayerCareTaker.cs]

~~~~c#
    /// <summary>
    /// CareTaker 클래스
    /// Memento를 관리한다.
    /// Originator 객체를 저장/복원 시켜주는 역할.
    /// </summary>
    public class PlayerCareTaker
    {
        public List<PlayerMemento> mementoList = new List<PlayerMemento>();

        //메멘토 객체를 저장한다.
        public void AddMemento(PlayerMemento memento) {
            mementoList.Add(memento);
        }

        //메멘토 객체를 반환해준다.
        public PlayerMemento GetMementoData(int index)
        {
            if (index < mementoList.Count)
                return mementoList[index];

            return null;
        }

        public PlayerMemento GetLatestData()
        {
            return mementoList[mementoList.Count - 1];
        }
    }
~~~~



### [MainProgram.cs]

```c#
    public class MainProgram : MonoBehaviour {

        void Main()
        {
            //Originator 객체(Player) 생성.
            Player player = new Player();

            player.Level = 10;
            player.HP = 500;
            player.PlayTime = DateTime.Parse("2017-08-07");

            //CareTaker를 통해서 메멘토 객체를 저장한다.
            PlayerCareTaker careTaker = new PlayerCareTaker();
            careTaker.AddMemento(player.SaveDataMemento());

            //캐릭터가 성장
            player.Level = 35;
            player.HP = 2100;
            player.PlayTime = DateTime.Parse("2017-09-15");

            careTaker.AddMemento(player.SaveDataMemento());

            //캐릭터가 성장
            player.Level = 50;
            player.HP = 3400;
            player.PlayTime = DateTime.Now;

            //CareTaker를 통해서 Player의 데이터를 저장된 메멘토 복원시킨다. 
            player.RestoreDataMemento(careTaker.GetLatestData());
        }
    }
```



---



### [실행 결과]

	Level : 10
	HP : 500
	PlayTime : 2017-08-07
	
	======== 여기까지 데이터 저장 ========
	
	Level : 35
	HP : 2100
	PlayTime : 2017-09-15
	
	======== 여기까지 데이터 저장 ========
	
	Level : 50
	HP : 3400
	PlayTime : 2017-10-12
	
	======== 데이터 복원 ========
	
	Level : 35
	HP : 2100
	PlayTime : 2017-09-15
