
namespace BuilderPattern.ex1
{
    /// <summary>
    /// 빌더의 구현 클래스. 
    /// 객체를 생성할 수 있도록 하는 클래스.(객체를 만들기 위해 부품을 조립)
    /// </summary>
    public class HawaiianPizzaBuilder : PizzaBuilder
    {
        public override void BuildDough() {
            pizza.SetDough("cross");
        }

        public override void BuildSauce() {
            pizza.SetSauce("mild");
        }

        public override void BuildTopping() {
            pizza.SetTopping("ham + pineapples");
        }
    }
}
