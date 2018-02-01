using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FacadePattern.ex2
{
    /// <summary>
    /// 각 부품별 공통 메소드를 정의한 인터페이스
    /// </summary>
    public interface IComputerPart
    {
        void Operation();
    }
}