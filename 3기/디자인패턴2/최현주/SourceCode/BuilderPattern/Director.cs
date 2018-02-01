
using UnityEngine;

namespace BuilderPattern.ex1
{
    /// <summary>
    /// 객체 생성의 순서를 정함.
    /// </summary>
    public class Director 
    {
        private PizzaBuilder pizzaBuilder;

        // Builder를 받아 필요한 동작을 처리.
        public void SetPizzaBuilder(PizzaBuilder builder)
        {
            pizzaBuilder = builder;
        }

        public Pizza GetPizza()
        {
            return pizzaBuilder.GetPizza();
        }

        //조립 순서를 정함.
        public void ConstructPizza()
        {
            pizzaBuilder.CreateNewPizza();
            pizzaBuilder.BuildDough();
            pizzaBuilder.BuildSauce();
            pizzaBuilder.BuildTopping();

            Debug.Log("ConstructPizza : " + pizzaBuilder);
        }
    }
}
