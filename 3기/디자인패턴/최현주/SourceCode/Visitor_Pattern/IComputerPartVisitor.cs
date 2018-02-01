using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace VisitorPattern.ex2
{
    /// <summary>
    /// Visitor 인터페이스
    /// 객체들을 자신을 던져주는 Visit 메소드를 정의 
    /// (객체추가시 메소드도 추가되야된다)
    /// 
    /// 모든 객체들은 방문자를 통해 세부 구현을 해야하니까, 각 객체들의 방문자들을 총집합시키는 인터페이스?
    /// </summary>
    public interface IComputerPartVisitor
    {
        void Visit(Monitor monitor);
        void Visit(Keyboard keyboard);
        void Visit(Mouse mouse);
        void Visit(Computer computer);
    }
}