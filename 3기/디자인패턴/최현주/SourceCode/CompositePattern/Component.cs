using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CompositePattern.ex2
{
    public abstract class Component
    {
        public abstract void Add(Component component);

        public abstract string GetName();
        public abstract int GetSize();

        public abstract void Print(string strPrefix);

        public string ToString()
        {
            return string.Format("{0}({1})", GetName(), GetSize());
        }
        
    }
}