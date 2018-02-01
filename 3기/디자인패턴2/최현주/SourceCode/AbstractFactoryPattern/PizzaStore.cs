using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// Factory Method
/// 객체 생성을 처리하며, 팩토리 메소드를 이용하면 객체를 생성하는 작업을 
/// 서브 클래스에 캡슐화시킬 수 있다.
/// </summary>
namespace AbstractFactoryPattern.ex2
{
    // 생산자 클래스.
    public abstract class PizzaStore
    {
        public Pizza OrderPizza(PizzaType type)
        {
            Pizza pizza = CreatePizza(type);

            pizza.Prepare();

            pizza.Bake();
            pizza.Cut();
            pizza.Box();

            return pizza;
        }

        public abstract Pizza CreatePizza(PizzaType type);
    }

    public enum PizzaType
    {
        Cheese,
    }
}
