using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace ObserverPattern.ex3
{
    /// <summary>
    /// Subject(관찰 대상자)의 역할.
    /// Observer 객체를 등록/삭제하는 메소드를 가지고 있음.
    /// </summary>
    public interface ISubject
    {
        //Observer를 등록/해제.
        void RegisterObserver(IObserver observer);
        void UnRegisterObserver(IObserver observer);

        void NotifyObserver();

        string GetArticle();
    }
}