using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace ObserverPattern.ex3
{
    /// <summary>
    /// Concrete Observer (구체적인 관찰자)
    /// </summary>
    public class User : IObserver
    {
        private ISubject blog;

        private string name;

        //생성자를 통해, 관찰당하는(User)에게 관찰 대상자(Blog)가 누군지 알려준다.
        //해당 유저가 구독하는 블로그가 무엇인지 초기화.
        public User(string name, ISubject blog)
        {
            this.name = name;
            this.blog = blog;
        }

        /// <summary>
        /// Subject 객체로부터 상태변화를 통보를 받으면, 각자 서브클래스에서 세부 기능을 구현.
        /// </summary>
        public void Update()
        {
            Debug.Log(string.Format("{0}님, 구독하는 블로그가 업데이트 되었습니다.", name));
            Debug.Log("[새 글] " + blog.GetArticle());
        }
    }
}