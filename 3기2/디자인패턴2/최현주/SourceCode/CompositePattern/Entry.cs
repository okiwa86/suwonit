using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CompositePattern.ex2
{
    /// <summary>
    /// Component 역할
    /// Composite(복합 객체)를 위한 추상화 클래스
    /// </summary>
    public abstract class Entry
    {
        //공통적으로 파일 or 디렉토리의 이름,사이즈.
        public abstract string GetName();
        public abstract int GetSize();

        /// <summary>
        /// 복합 개체(Directory)에서만 필요한 메서드.
        /// </summary>
        /// <param name="entry"></param>
        public virtual void Add(Entry entry)
        {
        }

        //공통으로 사용되는 출력 method.
        public void Print()
        {
            PrintList("");
        }

        public abstract void PrintList(string prefix);

        public string ToString()
        {
            return string.Format("{0} ({1})", GetName(), GetSize());
        }
    }
}