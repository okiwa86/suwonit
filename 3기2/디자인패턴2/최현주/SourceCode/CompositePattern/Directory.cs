using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CompositePattern.ex2
{
    /// <summary>
    /// Composite 역할
    /// Entry(Component)를 저장, Component를 관리하기위한 메소드를 가지고있다.
    /// </summary>
    public class Directory : Entry
    {
        private string name;
        private List<Entry> entryList = new List<Entry>();

        public Directory(string name)
        {
            this.name = name;
        }

        public override string GetName()
        {
            return name;
        }

        //단일 객체들의 크기를 더하여 사이즈를 계산.
        public override int GetSize()
        {
            int totalSize = 0;

            for (int i = 0; i < entryList.Count; i++)
            {
                totalSize += entryList[i].GetSize();
            }

            return totalSize;
        }

        public override void Add(Entry entry)
        {
            entryList.Add(entry);
        }

        public override void PrintList(string prefix)
        {
            Debug.Log(prefix + "/" + this.name);

            for (int i = 0; i < entryList.Count; i++)
            {
                Entry entry = entryList[i];
                entry.PrintList(prefix + "/" + name);
            }
        }
    }
}
