package com.hyuky.framework;

// Creator
public abstract class Factory {
	// Product make and some other function Like Template pattern!!
	public final Product create(String owner) {
		Product product = createProduct(owner); // step1
		registerProduct(product); // step2
		return product;
	}
	// Product create do only! create
	// tip: protected = 동일 패키지에 속하는 클래스와 하위 클래스 관계의 클래스에 의해 접근이 가능하다. 
	protected abstract Product createProduct(String owner);
	// Some other function #1
	protected abstract void registerProduct(Product product);
}
