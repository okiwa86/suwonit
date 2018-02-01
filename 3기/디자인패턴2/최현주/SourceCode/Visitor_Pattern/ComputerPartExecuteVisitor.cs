using System.Collections;
using System.Collections.Generic;
using UnityEngine;


namespace VisitorPattern.ex2
{
    /// <summary>
    /// Concrete Visitor 역할
    /// Visitor 인터페이스를 구현
    /// 
    /// 각 객체들을 전달받아 클래스에서 세부 작업을 처리한다.
    /// 컴퓨터 부품들을 실행시키는 클래스.
    /// </summary>
    public class ComputerPartExecuteVisitor : IComputerPartVisitor
    {
        public void Visit(Monitor monitor)
        {
            Debug.Log("모니터를 작동한다.");
        }

        public void Visit(Keyboard keyboard)
        {
            Debug.Log("키보드를 작동한다.");
        }

        public void Visit(Mouse mouse)
        {
            Debug.Log("마우스를 작동한다.");
        }

        public void Visit(Computer computer)
        {
            Debug.Log("컴퓨터를 작동한다");
        }
    }
}