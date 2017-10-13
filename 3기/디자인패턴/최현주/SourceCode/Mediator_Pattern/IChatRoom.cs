using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MediatorPattern.ex2
{
    /// <summary>
    /// Mediator(중재자) 인터페이스.
    /// Colleague 객체들과 의사소통을 하기위한 인터페이스를 정의.
    /// </summary>
    public interface IChatRoom
    {
        //채팅방에 참가하는 유저를 등록하는 메서드.
        ChatRoom AddUser(UserBase user);
        void SendMessage(string from, string to, string message);
    }
}