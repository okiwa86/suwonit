using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace VisitorPattern.ex2
{
    /// <summary>
    /// Concrete Element 역할 
    /// IComputerPart의 구현 클래스
    /// </summary>
    public class Mouse : IComputerPart
    {
        public void Accept(IComputerPartVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}