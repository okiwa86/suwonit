using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MementoPattern.ex2
{
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
}