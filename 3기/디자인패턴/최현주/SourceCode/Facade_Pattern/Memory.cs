using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FacadePattern.ex2
{
    /// <summary>
    /// SubSystem 클래스
    /// 기능 구현
    /// </summary>
    public class Memory : IComputerPart
    {
        public void Operation()
        {
            Debug.Log("메모리 로딩중...");
        }
    }
}