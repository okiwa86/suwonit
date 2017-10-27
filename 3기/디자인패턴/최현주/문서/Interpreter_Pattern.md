# Interpreter Pattern (해석자 패턴) 



## 1. Interpreter Pattern 
- [행위패턴]
- **문법 규칙을 클래스**로 표현한 구조.
- 일련의 규칙으로 정의된 언어를 해석하는 패턴.
- 언어에서 문장을 **해석할 때 주로 사용**하는 패턴.
- (SQL 같은 DB쿼리 언어에서 상용 통신 프로토콜을 설명하는데 자주 사용됨)

  ​



## 2. UML Diagram
![uml](http://upload.wikimedia.org/wikipedia/en/0/03/Interpreter_UML_class_diagram.jpg)



- Abstract Expression
  - 노드에 해당하는 모든 클래스들이 공통적으로 가져야할 Interpret() 메소드를 정의.
- TerminalExpression
  - 문법에 정의한 기호와 관련된 해석 방법을 구현.
  - 문장을 구성하는 모든 기호에 대해서 해당 클래스를 만들어야함.
- NonterminalExpression
  - 터미널 기호가 아닌 모든 기호에 대해서 Interpret 연산을 수행.
- Context
  - 인터프리터가 구문해석을 수행하도록 정보를 제공.





## 3. 장/단점

### [장점]


- 문법이 클래스에 의해 표현되기 때문에 언어를 쉽게 변경하거나 확장할 수 있다.

  - IExpression을 상속받아 새로운 문법을 얼마든지 추가할 수 있다.
- 클래스 구조에 메소드만 추가하면 프로그램을 해석하는 기본 기능 외에 예쁘게 출력하는 기능이라던지, 더 나은 프로그램 확인 기능 등 새로운 기능을 추가할 수 있다.




### [단점]

- 문법 규칙의 개수가 많아지면 아주 복잡해진다.
  - 이런 경우에는 파서/컴파일러 생성기를 쓰는 것이 낫다.

​

## 4. 코드 설명

- 역 폴란드어 표기법(Reverse Polish Notation)으로 표현된 문자열이 입력되면, 해당 입력을 해석하고 정답을 알려준다.
- 숫자는 NumberExpression에서, 부호는 AddExpression, SubstractExpression에서 해석한다.




### [IExpression.cs]

~~~~c#
    /// <summary>
    /// Expression interface.
    /// 구문 트리 노드의 공통의 인터페이스를 정하는 역할.
    /// </summary>
    public interface IExpression
    {
        int Interpret();
    }
~~~~



### [NumberExpression.cs]

~~~~c#
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
~~~~



### [AddExpression.cs]

~~~~c#
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
~~~~



### [SubstractExpression.cs]

```c#
    /// <summary>
    /// NonTerminal Expreesion 객체.
    /// 터미널 기호가 아닌 모든 기호에 대해서 Interpret 연산을 수행.
    /// </summary>
    public class SubstractExpression : IExpression
    {
        //left Expression과 right Expression을 참조.
        private IExpression leftExpression;
        private IExpression rightExpression;

        public SubstractExpression(IExpression left, IExpression right)
        {
            leftExpression = left;
            rightExpression = right;
        }

        /// <summary>
        /// SubstractExpression 해석시 left - right의 결과값으로 해석하여 리턴해준다.
        /// </summary>
        /// <returns>결과값</returns>
        public int Interpret()
        {
            int result = leftExpression.Interpret() - rightExpression.Interpret();
            return result;
        }
    }
```



### [TokenReader.cs]

```c#
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
```



### [MainProgram.cs]

```c#
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
```



---


### [실행 결과]

	11
	9




## 5. 참고사이트

- https://www.codeproject.com/Articles/186183/Interpreter-Design-Pattern