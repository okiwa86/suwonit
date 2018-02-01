using System.Collections.Generic;
using UnityEngine;

namespace StrategyPattern.ex2
{
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
                Debug.Log(string.Format("이름 : {0}, 나이 : {1}, 키 : {2}", list[i].name, list[i].age, list[i].tall));
            }
        }
    }
}