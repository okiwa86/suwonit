using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace InterpreterPattern.ex2
{
    public class MainProgram : MonoBehaviour {

        void Main()
        {
            string strToken = "+ - 10 2 3";
            List<string> tokenList = new List<string>(strToken.Split(' '));

            IExpression expression = new TokenReader().ReadToken(tokenList);
            Debug.Log(expression.Interpret()); // (10-2) + 3 =11

            strToken = "- + 10 5 - 8 2";
            tokenList = new List<string>(strToken.Split(' '));

            expression = new TokenReader().ReadToken(tokenList);
            Debug.Log(expression.Interpret()); // (10+5)-(8-2)= 9
        }
    }
}