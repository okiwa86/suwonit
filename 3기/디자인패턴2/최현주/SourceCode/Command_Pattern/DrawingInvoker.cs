using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// Invoker.
    /// Command 객체에게 작업을 요청한다.
    /// </summary>
    public class DrawingInvoker 
    {
        //Command 객체를 관리한다.
        private Stack<ICommand> commandStack;

        public DrawingInvoker()
        {
            commandStack = new Stack<ICommand>();
        }

        //Execute를 호출하면 해당 Command를 Stack에 쌓고, Execute를 실행한다.
        public void Execute(ICommand command)
        {
            commandStack.Push(command);
            command.Execute();
        }

        public void Undo()
        {
            commandStack.Pop().Undo();
        }
    }
}