package com.hyuky.idcard;

import java.util.ArrayList;
import java.util.List;
import com.hyuky.framework.*;

// Creator Factory
public class IDCardFactory extends Factory {
	private List<String> ownerList = new ArrayList<String>();
	
	// Create only
	@Override
	protected Product createProduct(String owner) {
		return new IDCard(owner);
	}

	// Some other function #1
	@Override
	protected void registerProduct(Product product) {
		ownerList.add(((IDCard) product).getOwner());
	}

	public List<String> getOwnerList() {
		return ownerList;
	}
	

}
