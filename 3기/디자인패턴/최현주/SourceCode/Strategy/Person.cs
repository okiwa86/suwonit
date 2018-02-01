using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace StrategyPattern.ex2
{
    public class Person
    {
        public string name;
        public int age;
        public int tall;

        public Person(string name, int age, int tall)
        {
            this.name = name;
            this.age = age;
            this.tall = tall;
        }
    }
}