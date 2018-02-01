using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace InterpreterPattern.ex2
{
    /// <summary>
    /// NonTerminal Expreesion 객체.
    /// 터미널 기호가 아닌 모든 기호에 대해서 Interpret 연산을 수행.
    /// </summary>
    public class AddExpression : IExpression
    {
        //left Expression과 right Expression을 참조.
        private IExpression leftExpression;
        private IExpression rightExpression;

        public AddExpression(IExpression left, IExpression right)
        {
            leftExpression = left;
            rightExpression = right;
        }

        /// <summary>
        /// AddExpression에서는 해석시 left + right의 결과값으로 해석하여 리턴해준다.
        /// </summary>
        /// <returns>결과값</returns>
        public int Interpret()
        {
            int result = leftExpression.Interpret() + rightExpression.Interpret();
            return result;
        }
    }
}