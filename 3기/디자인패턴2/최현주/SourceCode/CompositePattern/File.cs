using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CompositePattern.ex2
{
    /// <summary>
    /// Leaf 역할 (단일 객체)
    /// Entry(Component)를 구현하는 클래스.
    /// </summary>
    public class File : Entry
    {
        private string name;
        private int size;

        public File(string name, int size)
        {
            this.name = name;
            this.size = size;
        }

        public override string GetName()
        {
            return name;
        }

        public override int GetSize()
        {
            return size;
        }

        public override void PrintList(string prefix)
        {
            Debug.Log(prefix + "/" + this.name);
        }
    }
}