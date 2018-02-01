using System;

namespace MementoPattern.ex2
{
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
}