using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace InterpreterPattern.ex2
{
    /// <summary>
    /// Expression interface.
    /// 구문 트리 노드의 공통의 인터페이스를 정하는 역할.
    /// </summary>
    public interface IExpression
    {
        int Interpret();
    }
}