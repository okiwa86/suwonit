using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MediatorPattern.ex2
{
    public class MainProgram : MonoBehaviour
    {
        void Main()
        {
            ChatRoom chatRoom = new ChatRoom();

            // 유저를 생성하고,
            UserBase kim = new BasicUser("김씨");
            UserBase lee = new BasicUser("이씨");
            UserBase park = new BasicUser("박씨");
            UserBase choi = new BasicUser("최씨");
            UserBase yang = new RankerUser("양씨");

            // 채팅방에 생성한 유저를 추가한다.
            chatRoom.AddUser(kim).AddUser(lee).AddUser(park).AddUser(choi).AddUser(yang);

            yang.SendMessage(choi.Name, "최씨를 만나서 반가워");
            lee.SendMessage(park.Name, "치킨먹자");
            park.SendMessage(kim.Name, "오늘 2시 어때?");
            lee.SendMessage(choi.Name, "놀러와");
            choi.SendMessage(yang.Name, "전화가능?");
        }
    }
}
