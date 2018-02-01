package TemplteMethod_Ex1;

public abstract class CaffeineBeverageWithHook {

    void PrepareRecipe()
    {
        BoilWater();
        Brew();
        PourInCup();

        if(customerWantsCondiments())
            AddCondiments();
    }

    public abstract void Brew();

    public abstract void AddCondiments();

    void BoilWater()
    {
        System.out.println("물 끓이는 중");
    }

    void PourInCup()
    {
        System.out.println("컵에 따르는 중");
    }

    boolean customerWantsCondiments()
    {
        return true;
    }
}
