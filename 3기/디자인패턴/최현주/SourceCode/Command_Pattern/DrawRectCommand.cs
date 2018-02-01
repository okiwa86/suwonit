using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// Command 객체.
    /// 네모를 그리는 명령 객체.
    /// </summary>
    public class DrawRectCommand : ICommand
    {
        private RectReceiver receiver;

        public DrawRectCommand(RectReceiver receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            receiver.Draw();
        }

        public void Undo()
        {
            receiver.Erase();
        }
    }
}