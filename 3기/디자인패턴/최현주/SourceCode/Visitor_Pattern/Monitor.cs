using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace VisitorPattern.ex2
{
    /// <summary>
    /// Concrete Element 역할 
    /// IComputerPart의 구현 클래스
    /// </summary>
    public class Monitor : IComputerPart
    {
        // Visitor에 자기 자신을 전달 (Visitor가 해당 객체의 세부작업을 처리할 수 있도록)
        public void Accept(IComputerPartVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}