
namespace BuilderPattern.ex1
{
    /// <summary>
    /// 객체를 생성하는 추상 인터페이스
    /// </summary>
    public abstract class PizzaBuilder
    {
        protected Pizza pizza;

        public void CreateNewPizza() {
            pizza = new Pizza();
        }

        public Pizza GetPizza() {
            return pizza;
        }

        public abstract void BuildDough();
        public abstract void BuildSauce();
        public abstract void BuildTopping();
    }
}
