using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace AbstractFactoryPattern.ex2
{
    // 여러 객체를 생성하기 위한 추상화 인터페이스
    public abstract class Pizza
    {
        protected string name;

        protected IDough dough;
        protected ISauce sauce;
        protected ICheese cheese;

        //피자를 만드는데 필요한 재료들을 정돈.
        public abstract void Prepare();

        // 모든 피자를 만드는데 공통적으로 필요한 부분.
        public void Bake() {
            Debug.Log("피자를 25분동안 굽는다.");
        }

        public void Cut() {
            Debug.Log("피자를 8등분한다.");
        }

        public void Box() {
            Debug.Log("피자를 포장한다.");
        }

        public void SetName(string name) {
            this.name = name;
        }

        public string GetName() {
            return name;
        }
    }
}
