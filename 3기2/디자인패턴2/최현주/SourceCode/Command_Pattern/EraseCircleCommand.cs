using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// Command 객체.
    /// 원을 지우는 명령 객체.
    /// </summary>
    public class EraseCircleCommand : ICommand
    {
        private CircleReceiver receiver;

        public EraseCircleCommand(CircleReceiver receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            receiver.Erase();
        }

        public void Undo()
        {
            receiver.Draw();
        }
    }
}
