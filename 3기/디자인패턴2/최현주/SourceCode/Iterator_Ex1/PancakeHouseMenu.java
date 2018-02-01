package Iterator_Ex1;

import java.util.ArrayList;

public class PancakeHouseMenu implements IMenu {
	ArrayList menuItems;

	public PancakeHouseMenu()
	{
		menuItems = new ArrayList();

		AddItem("k&b pancake set", "스크램블 에그와 토스트가 곁들여진 팬케이크", true, 2.99);
		AddItem("regular pancake set", "달걀 후라이와 소시지가 곁들여진 팬케이크", false, 2.99);
		AddItem("블루베리 pancake", "블루베리와 블루베리 시럽으로 만든 팬케이크", true, 3.49);
		AddItem("와플", "와플, 취향에 따라 블루베리나 딸기를 얹을 수 있습니다.", true, 3.59);
	}

	public void AddItem(String name, String description, boolean isVegetarian, double price)
	{
		MenuItem menuItem = new MenuItem(name, description, isVegetarian, price);
		menuItems.add(menuItem);
	}
	
	//Iterator Interface를 리턴한다.
	public Iterator createIterator()
	{
//        return menuItems.iterator();
		return new PancakeHouseIterator(menuItems);
    }
}
