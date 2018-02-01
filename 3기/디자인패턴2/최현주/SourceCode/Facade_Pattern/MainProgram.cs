using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FacadePattern.ex2
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //퍼사드 클래스를 생성하고, 필요한 메소드(복잡한 인터페이스들을 작동) 호출한다.
            Computer facade = new Computer();
            facade.Booting();
        }
    }
}
