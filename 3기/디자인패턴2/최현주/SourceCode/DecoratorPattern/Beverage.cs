using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace DecoratorPattern.ex2
{
    /// <summary>
    /// Component 추상 클래스
    /// </summary>
    public abstract class Beverage
    {
        protected string description = "설명 없음";

        public virtual string GetDescription() {
            return description;
        }

        public abstract float Cost();
    }

    /// <summary>
    /// ConcreteComponent 역할
    /// Component를 상속받아 구체적인 구현을 한다.
    /// </summary>
    public class Espresso : Beverage
    {
        //생성자부분에서 description을 설정.
        public Espresso() {
            description = "에스프레소";
        }

        //각 클래스의 기능에 맞는 가격을 설정하여 반환.
        public override float Cost() {
            return 1.99f;
        }
    }

    public class HouseBlend : Beverage
    {
        public HouseBlend() {
            description = "하우스 블렌드";
        }

        public override float Cost() {
            return .89f;
        }
    }

    public class DarkRoast : Beverage
    {
        public DarkRoast() {
            description = "다크 로스트";
        }

        public override float Cost() {
            return .99f;
        }
    }

    public class Decaf : Beverage
    {
        public Decaf() {
            description = "디카페인";
        }

        public override float Cost() {
            return 1.05f;
        }
    }

}