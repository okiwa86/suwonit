using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MediatorPattern.ex2
{
    /// <summary>
    /// Concrete Mediator
    /// 
    /// Mediator 인터페이스를 구현.
    /// Colleague 객체들간의 의사소통을 위한 클래스!!!
    public class ChatRoom : IChatRoom
    {
        //Colleague 객체들을 관리하는 Dictionay.
        private Dictionary<string, UserBase> userDic = new Dictionary<string, UserBase>();

        /// <summary>
        /// @ override 
        /// 
        /// 채팅방에 참여하는 유저를 등록하는 메소드.
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        public ChatRoom AddUser(UserBase user)
        {
            if (!userDic.ContainsValue(user))
            {
                userDic.Add(user.Name, user);
            }
            user.ChatRoom = this;

            return this;
        }

        /// <summary>
        /// @ override
        /// 
        /// 메세지를 from 유저로 부터 to 유저에게 보내는 메소드.
        /// </summary>
        /// <param name="from"></param>
        /// <param name="to"></param>
        /// <param name="message"></param>
        public void SendMessage(string from, string to, string message)
        {
            UserBase receiverUser = userDic[to];

            if (receiverUser != null)
            {
                receiverUser.ReceiveMessage(from, message);
            }
        }
    }
}