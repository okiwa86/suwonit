using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

namespace MementoPattern.ex2
{
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
            Debug.Log("======== 데이터 복원 ========");

            Level = memento.Level;
            HP = memento.Hp;
            PlayTime = memento.PlayTime;
        }
    }
}
