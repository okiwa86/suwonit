using UnityEngine;

namespace BuilderPattern.ex1
{
    public class MainProgram : MonoBehaviour
    {
        private void Start()
        {
            Director director = new Director();
            director.SetPizzaBuilder(new HawaiianPizzaBuilder());
            director.ConstructPizza();

            director.SetPizzaBuilder(new SpicyPizzaBuilder());
            director.ConstructPizza();
        }
    }
}