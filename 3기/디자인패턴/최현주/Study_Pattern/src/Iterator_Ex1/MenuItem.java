package Iterator_Ex1;

public class MenuItem {
	String name;
	String description;
	boolean isVegetarian;
	double price;

	public MenuItem(String name, String description, boolean isVegetarian, double price)
	{
		this.name = name;
		this.description = description;
		this.isVegetarian = isVegetarian;
		this.price = price;
	}

	public String GetName()
	{
		return name;
	}

	public String GetDescription()
	{
		return description;
	}

	public double GetPrice()
	{
		return price;
	}

	public boolean IsVegetarian()
	{
		return isVegetarian;
	}

}