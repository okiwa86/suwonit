using System.Collections;
using System.Collections.Generic;
using UnityEngine;
namespace AbstractFactoryPattern.ex2
{
    public class ChicagoPizzaStore : PizzaStore
    {
        public override Pizza CreatePizza(PizzaType type)
        {
            Pizza pizza = null;
            IPizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

            if (type == PizzaType.Cheese) {
                pizza = new CheesePizza(ingredientFactory);
                pizza.SetName("시카고 스타일의 치즈 피자");
            }

            return pizza;
        }
    }
}