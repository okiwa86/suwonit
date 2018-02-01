using System.Collections;
using System.Collections.Generic;
using UnityEngine;


namespace ObserverPattern.ex3
{
    /// <summary>
    /// Concrete Subject (구체적인 관찰 대상자)
    /// Subject(Blog)는 Observer를 관리하며 등록/해지 메소드를 가지고 있다.
    /// </summary>
    public class Blog : ISubject
    {
        private List<IObserver> observerList;

        private string article;

        public Blog()
        {
            observerList = new List<IObserver>();
        }

        public void RegisterObserver(IObserver observer)
        {
            observerList.Add(observer);
        }

        public void UnRegisterObserver(IObserver observer)
        {
            observerList.Remove(observer);
        }

        /// <summary>
        /// 등록된 옵저버리스트에게 업데이트 사실을 전달.
        /// 자신의(Blog) 변경된 내용을 전달하기위해서 this형태로 전달한다.
        /// </summary>
        public void NotifyObserver()
        {
            for (int i = 0; i < observerList.Count; i++)
            {
                observerList[i].Update();
            }
        }

        /// <summary>
        /// 새로운 글을 등록하고, 해당 블로그를 구독하는 유저에게 업데이트를 알린다.
        /// </summary>
        /// <param name="article"></param>
        public void PostNewAriticle(string article)
        {
            this.article = article;

            Debug.Log("[새로운 글 등록] : " + article);
            Debug.Log("=============================");

            NotifyObserver();
        }

        public string GetArticle()
        {
            return article;
        }
    }
}
