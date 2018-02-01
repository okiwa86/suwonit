using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace DecoratorPattern.ex2
{
    /// <summary>
    /// 첨가물에 들어가는 Decorator 클래스들의 추상클래스
    /// </summary>
    public abstract class CondimentDecorator : Beverage
    {
        //Beverage의 GetDescription()을 override.
        public abstract override string GetDescription();
    }

    /// <summary>
    /// ConcreteDecorator - 구체적인 Decorator
    /// </summary>
    public class Mocha : CondimentDecorator
    {
        //자신이 장식할 대상을 알아야하기때문에 Beverage를 가지고 있음.
        Beverage beverage;

        public Mocha(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 모카";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.20f);
        }
    }

    public class Soy : CondimentDecorator
    {
        Beverage beverage;

        public Soy(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 두유";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.15f);
        }
    }


    public class Whip : CondimentDecorator
    {
        Beverage beverage;

        public Whip(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 휘핑크림";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.10f);
        }
    }

    public class SteamMilk : CondimentDecorator
    {
        Beverage beverage;

        public SteamMilk(Beverage beverage) {
            this.beverage = beverage;
        }

        public override string GetDescription() {
            return beverage.GetDescription() + ", 스팀 밀크";
        }

        public override float Cost() {
            return (beverage.Cost() + 0.10f);
        }
    }
}
