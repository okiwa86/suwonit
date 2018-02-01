using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace ObserverPattern.ex3
{
    /// <summary>
    /// Observer(관찰자) 인터페이스.
    /// Subject 객체로부터 상태변화를 통보를 받는다.
    /// </summary>
    public interface IObserver
    {
        //Subject 객체가 상태 변화를 통보하면 
        //Observer 객체들은 변화된 데이터에 맞게 자신의 데이터를 Update시킨다.
        void Update();
    }
}