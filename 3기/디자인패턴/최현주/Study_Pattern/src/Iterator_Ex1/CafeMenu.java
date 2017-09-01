package Iterator_Ex1;

import java.util.Hashtable;

public class CafeMenu implements IMenu {
	Hashtable menuItems = new Hashtable();

    public CafeMenu()
    {
        AddItem("베지버거와 프라이", "상추, 토마토, 감자튀김이 첨가된 베지버거", true, 3.99);
        AddItem("오늘의 스프", "샐러드가 곁들여진 오늘의 스프", false, 3.69);
        AddItem("베리또", "푸짐한 베리또", true, 4.29);
    }

    public void AddItem(String name, String description, boolean isVegetarian, double price)
    {
        MenuItem menuItem = new MenuItem(name, description, isVegetarian, price);
        menuItems.put(menuItem.GetName(), menuItem);

    }

    public Iterator createIterator()
    {
//        return menuItems.values().iterator();
    	return null;
    }
}
