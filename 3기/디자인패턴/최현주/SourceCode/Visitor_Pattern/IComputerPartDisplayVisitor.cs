using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace VisitorPattern.ex2
{
    /// <summary>
    /// Concrete Visitor 역할
    /// Element 인터페이스 역할 (데이터의 인터페이스)
    /// 
    /// 각 객체는 Visitor 객체가 세부 작업을 처리할 수 있도록 
    /// Visitor 인터페이스를 받아야한다.
    /// </summary>
    public interface IComputerPart
    {
        void Accept(IComputerPartVisitor visitor);
    }


}