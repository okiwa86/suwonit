using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// 연산자 Manager 클래스.
/// 모든 연산에 대한 것을 관리.
/// </summary>
public class OperatorMgr : Singleton<OperatorMgr> {

    private int resultVal = 0;

    public void AddValue(int a)
    {
        resultVal += a;
    }

    public void Print()
    {
        Debug.Log("Result : " + resultVal);
    }
}
