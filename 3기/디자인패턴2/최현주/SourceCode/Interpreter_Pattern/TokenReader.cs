using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

namespace InterpreterPattern.ex2
{
    /// <summary>
    /// 문법 규칙을 해석해주는 클래스.
    /// </summary>
    public class TokenReader 
    {
        public IExpression ReadToken(List<string> tokenList)
        {
            return ReadNextToken(tokenList);
        }

        private IExpression ReadNextToken(List<string> tokenList)
        {
            int num = 0;

            //해당 문자열의 첫문자가 숫자인지, 기호인지 판단하여 Expression을 리턴.
            if (int.TryParse(tokenList.First(), out num))
            {
                tokenList.RemoveAt(0);
                return new NumberExpression(num);
            }
            else
            {
                return ReadNonTerminal(tokenList);
            }
        }

        /// <summary>
        /// NonTermianl 객체를 읽어 반환하는 메소드.
        /// </summary>
        /// <param name="tokenList"></param>
        /// <returns></returns>
        private IExpression ReadNonTerminal(List<string> tokenList)
        {
            string token = tokenList.First();
            tokenList.RemoveAt(0);

            IExpression left = ReadNextToken(tokenList);
            IExpression right = ReadNextToken(tokenList);

            if(token.Equals("+"))
            {
                return new AddExpression(left, right);
            }
            else if(token.Equals("-"))
            {
                return new SubstractExpression(left, right);
            }

            return null;
        }
    }
}