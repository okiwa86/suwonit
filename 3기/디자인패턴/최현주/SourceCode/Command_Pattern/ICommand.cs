using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// Command 인터페이스.
    /// Command 객체들이 해당 인터페이스를 상속받아 구현한다.
    /// </summary>
    public interface ICommand 
    {
        //Command 객체들이 구현할 공통 메소드.
        void Execute();

        //되돌리는 메소드.
        void Undo();
    }
}
