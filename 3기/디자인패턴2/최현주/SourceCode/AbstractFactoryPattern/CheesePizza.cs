using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace AbstractFactoryPattern.ex2
{
    public class CheesePizza : Pizza
    {
        IPizzaIngredientFactory ingredientFactory;

        //피자를 만드는 Factory를 받아서 준비.
        public CheesePizza(IPizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }

        public override void Prepare()
        {
            Debug.Log(string.Format(" * *** {0} 피자 준비중 *****", name));

            //해당 Factory에서 정의한 재료들로 준비한다.
            dough = ingredientFactory.CreateDought();
            sauce = ingredientFactory.CreateSauce();
            cheese = ingredientFactory.CreateCheese();

            Debug.Log(dough.ToString());
            Debug.Log(sauce.ToString()); 
            Debug.Log(cheese.ToString()); 
        }
    }
}