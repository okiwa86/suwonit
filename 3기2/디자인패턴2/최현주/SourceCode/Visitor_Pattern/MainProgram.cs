using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace VisitorPattern.ex2
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //복잡한 구조체를 정의해놓고,
            IComputerPart computer = new Computer();

            //Visitor를 전달하여 세부 기능을 실행.
            // Visitor 전달 -> Computer -> Visitor에 전달.
            computer.Accept(new ComputerPartDisplayVisitor());

            computer.Accept(new ComputerPartExecuteVisitor());
        }
    }
}