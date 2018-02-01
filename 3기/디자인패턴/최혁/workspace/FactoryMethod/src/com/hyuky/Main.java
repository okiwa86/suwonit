package com.hyuky;

import com.hyuky.framework.Factory;
import com.hyuky.framework.Product;
import com.hyuky.idcard.IDCardFactory;

public class Main {

	public static void main(String[] args) {
		Factory factory = new IDCardFactory(); // Creator IDCard
		Product card1 = factory.create("Ann");
		Product card2 = factory.create("Mike");
		Product card3 = factory.create("Brown");

		card1.use();
		card2.use();
		card3.use();

		System.out.println(((IDCardFactory) factory).getOwnerList());
	}
}
