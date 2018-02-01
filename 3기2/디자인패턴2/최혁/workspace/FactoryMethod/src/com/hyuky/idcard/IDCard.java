package com.hyuky.idcard;

import com.hyuky.framework.Product;

public class IDCard extends Product {
	private String owner;

	//Constructor
	IDCard(String owner) {
		System.out.println(owner + "'s card make");
		this.owner = owner;
	}

	@Override
	public void use() {
		System.out.println(owner + "'s use card");
	}

	public String getOwner() {
		return owner;
	}
}
