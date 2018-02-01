using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace ObserverPattern.ex3
{
    public class MainProgram : MonoBehaviour
    {
        void Main()
        {
            Blog blog = new Blog();

            User user1 = new User("김가네", blog);
            User user2 = new User("이철", blog);
            User user3 = new User("박철", blog);

            blog.RegisterObserver(user1);
            blog.RegisterObserver(user2);
            blog.RegisterObserver(user3);

            blog.PostNewAriticle("전체 공지! 휴가 사용시 유의사항!");
        }
    }
}