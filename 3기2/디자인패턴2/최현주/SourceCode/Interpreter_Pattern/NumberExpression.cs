using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace InterpreterPattern.ex2
{
    /// <summary>
    /// Terminal Expression 객체.
    /// 문법에 정의한 기호와 관련된 해석 방법을 구현.
    /// </summary>
    public class NumberExpression : IExpression
    {
        private int number;

        public NumberExpression(int number)
        {
            this.number = number;
        }

        //override.
        public int Interpret()
        {
            return number;
        }
    }
}
