using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace AbstractFactoryPattern.ex2
{
    public class NYPizzaIngredientFactory : IPizzaIngredientFactory
    {
        // 뉴욕 버전으로 각 재료를 구현.
        public IDough CreateDought() {
            return new ThinCrustDough();
        }

        public ISauce CreateSauce() {
            return new MarinaraSauce();
        }

        public ICheese CreateCheese() {
            return new CheddarCheese();
        }
    }
}