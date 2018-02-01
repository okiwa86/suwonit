using System.Collections;
using System.Collections.Generic;
using UnityEngine;


namespace DecoratorPattern.ex2
{
    public class StarbuzzCoffee : MonoBehaviour
    {
        void Start()
        {
            // 첨가를 하지 않음.
            Beverage beverage = new Espresso();

            Debug.Log(beverage.GetDescription() + " - $" + beverage.Cost());

            // 기본 음료에 더블모카, 휘핑크림을 첨가.
            Beverage beverage2 = new DarkRoast();
            beverage2 = new Mocha(beverage2);
            beverage2 = new Mocha(beverage2);
            beverage2 = new Whip(beverage2);

            Debug.Log(beverage2.GetDescription() + " - $" + beverage2.Cost());

            // 기본 음료에 두유, 모카, 휘핑크림을 첨가.
            Beverage beverage3 = new HouseBlend();
            beverage3 = new Soy(beverage3);
            beverage3 = new Mocha(beverage3);
            beverage3 = new Whip(beverage3);

            Debug.Log(beverage3.GetDescription() + " - $" + beverage3.Cost());
        }
    }
}
