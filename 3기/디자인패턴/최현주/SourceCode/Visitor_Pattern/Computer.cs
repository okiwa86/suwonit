using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace VisitorPattern.ex2
{
    /// <summary>
    /// Concrete Element 역할
    /// IComputerPart의 구현 클래스
    /// 
    /// 복잡한 구조체 (부품들을 가지고 있음) - Composite 패턴과 유사.
    /// 이 구조체를 사용하고 싶을 때 Visitor를 통해서 내부 로직에 접근한다.
    /// </summary>
    public class Computer : IComputerPart
    {
        private IComputerPart[] parts;

        // 부품을 생성한다.
        public Computer()
        {
            parts = new IComputerPart[] { new Monitor(), new Keyboard(), new Mouse() };
        }

        public void Accept(IComputerPartVisitor visitor)
        {
            for (int i = 0; i < parts.Length; i++)
            {
                parts[i].Accept(visitor);
            }
            visitor.Visit(this);
        }
    }
}