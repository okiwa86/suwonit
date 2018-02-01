using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace AbstractFactoryPattern.ex2
{
    public class MainProgram : MonoBehaviour {

        void Start () {
            PizzaStore store = new NYPizzaStore();
            Pizza pizza = store.OrderPizza(PizzaType.Cheese);

            PizzaStore store2 = new ChicagoPizzaStore();
            Pizza pizza2 = store2.OrderPizza(PizzaType.Cheese);
        }
    }
}