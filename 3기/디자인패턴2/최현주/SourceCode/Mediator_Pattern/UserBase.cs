using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MediatorPattern.ex2
{
    /// <summary>
    /// Colleague(객체) 인터페이스.
    /// </summary>
    public abstract class UserBase
    {
        //자신의 중재자가 누구인지 알아야 한다 (다른 객체와 통신이 필요할 때 중재자를 통해서 교류).
        private IChatRoom chatroom;
        private string name;

        public IChatRoom ChatRoom { get { return chatroom; } set { chatroom = value; } }
        public string Name { get { return name; } }

        public UserBase(string name)
        {
            this.name = name;
        }

        /// <summary>
        /// 중재자(chatroom)를 통해서 다른 유저(to)에게 message를 보내는 메소드.
        /// </summary>
        /// <param name="to"></param>
        /// <param name="message"></param>
        public void SendMessage(string to, string message)
        {
            chatroom.SendMessage(name, to, message);
        }

        public abstract void ReceiveMessage(string from, string message);
    }
}