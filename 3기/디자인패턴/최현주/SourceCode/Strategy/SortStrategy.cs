using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace StrategyPattern.ex2
{
    /// <summary>
    /// Strategy 추상화 클래스 (알고리즘의 추상 객체)
    /// </summary>
    public abstract class SortStrategy
    {
        //공통 알고리즘 메서드를 정의만 해놓는다.
        public abstract void Sort(List<Person> list);
    }

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

    public class SortByName : SortStrategy
    {
        public override void Sort(List<Person> list)
        {
            Debug.Log("===== 이름순으로 정렬 =====");

            list.Sort((x, y) => x.name.CompareTo(y.name));
        }
    }

    public class SortByTall : SortStrategy
    {
        public override void Sort(List<Person> list)
        {
            Debug.Log("===== 키순으로 정렬 =====");

            list.Sort((x, y) => x.tall.CompareTo(y.tall));
        }
    }
}