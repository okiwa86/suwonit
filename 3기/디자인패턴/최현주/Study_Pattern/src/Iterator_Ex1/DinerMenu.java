package Iterator_Ex1;

public class DinerMenu implements IMenu {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	MenuItem[] menuItems;

	public DinerMenu()
	{
		menuItems = new MenuItem[MAX_ITEMS];

		AddItem("채식주의자용 BLT", "통밀 위에 (식물성)베이컨, 상추, 토마토를 얹은 메뉴", true, 2.99);
		AddItem("BLT", "통밀 위에 베이컨, 상추, 토마토를 얹은 메뉴", false, 2.99);
		AddItem("오늘의 스프", "감자 샐러드를 곁들인 오늘의 스프", false, 3.29);
		AddItem("핫도그", "사워크라우트, 갖은 양념, 양파, 치즈가 곁들어진 핫도그", false, 3.05);
	}

	public void AddItem(String name, String description, boolean isVegetarian, double price)
	{
		MenuItem menuItem = new MenuItem(name, description, isVegetarian, price);
		if(numberOfItems >= MAX_ITEMS) {
			System.out.println("죄송합니다. 메뉴가 꽉참. 더 이상 추가할 수 없습니다.");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1;
		}
	}

	//Iterator Interface를 리턴한다.
	public Iterator createIterator()
	{
		return new DinerMenuIterator(menuItems);
	}
}
