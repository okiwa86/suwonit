using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace AbstractFactoryPattern.ex2
{
    public class ChicagoPizzaIngredientFactory : IPizzaIngredientFactory
    {
        //시카고  버전으로 각 재료를 구현.
        public IDough CreateDought() {
            return new ThinkCrustDough();
        }

        public ISauce CreateSauce() {
            return new TomatoSauce();
        }

        public ICheese CreateCheese() {
            return new MozzarellaCheese();
        }
    }
}