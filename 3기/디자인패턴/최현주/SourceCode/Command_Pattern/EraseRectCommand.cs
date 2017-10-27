using System.Collections;
using System.Collections.Generic;
using UnityEngine;
namespace CommandPattern.ex4
{
    /// <summary>
    /// Command 객체.
    /// 네모를 지우는 명령 객체.
    /// </summary>
    public class EraseRectCommand : ICommand
    {
        private RectReceiver receiver;

        public EraseRectCommand(RectReceiver receiver)
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