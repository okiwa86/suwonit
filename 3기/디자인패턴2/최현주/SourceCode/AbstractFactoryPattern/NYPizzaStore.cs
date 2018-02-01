using System.Collections;
using System.Collections.Generic;
using UnityEngine;
namespace AbstractFactoryPattern.ex2
{
    public class NYPizzaStore : PizzaStore
    {
        public override Pizza CreatePizza(PizzaType type)
        {
            Pizza pizza = null;
            IPizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

            if (type == PizzaType.Cheese) {
                pizza = new CheesePizza(ingredientFactory);
                pizza.SetName("뉴욕 스타일의 치즈 피자");
            }

            return pizza;
        }
    }
}