
namespace AbstractFactoryPattern.ex2
{
    /// <summary>
    /// 각 재료별 생성 메소드를 정의.
    /// </summary>
    public interface IPizzaIngredientFactory
    {
        IDough CreateDought();
        ISauce CreateSauce();
        ICheese CreateCheese();
    }
}