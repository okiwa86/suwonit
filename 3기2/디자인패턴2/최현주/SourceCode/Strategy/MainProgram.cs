using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace StrategyPattern.ex2
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //Person 정보 등록.
            List<Person> personList = new List<Person>();

            personList.Add(new Person("yoon jungsoo", 40, 155));
            personList.Add(new Person("kang dongwon", 35, 190));
            personList.Add(new Person("suzy", 26, 170));

            Debug.Log("===== 정렬 전 =====");

            for (int i = 0; i < personList.Count; i++)
            {
                Debug.Log(string.Format("이름 : {0}, 나이 : {1}, 키 : {2}", 
                    personList[i].name, personList[i].age, personList[i].tall));
            }

            //Age.
            SortedList sortedList = new SortedList(new SortByAge());

            sortedList.Sort(personList);
            sortedList.PrintInfo(personList);

            //Name.
            sortedList = new SortedList(new SortByName());

            sortedList.Sort(personList);
            sortedList.PrintInfo(personList);

            //Tall.
            sortedList = new SortedList(new SortByTall());

            sortedList.Sort(personList);
            sortedList.PrintInfo(personList);
        }
    }
}