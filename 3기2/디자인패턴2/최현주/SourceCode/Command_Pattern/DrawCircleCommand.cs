using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// Command 객체.
    /// 원을 그리는 명령 객체.
    /// </summary>
    public class DrawCircleCommand : ICommand
    {
        // 리시버를 참조한다.
        // 명령 대상자가 누구인지 반드시 알아야한다.
        private CircleReceiver receiver;

        // 생성자를 통해 리시버를 전달. 
        // (리시버에게 특정 작업을 처리하라는 명령을 전달하기 위해 참조)
        public DrawCircleCommand(CircleReceiver receiver)
        {
            this.receiver = receiver;
        }

        /// <summary>
        /// 모든 명령(Command)는 Execute()메소드 호출로 실행한다.
        /// </summary>
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
