package framework;

import java.util.HashMap;
import java.util.Map;

public class Manager {
	private Map<String, Product> registerMap = new HashMap<String, Product>();

	public void register(String name, Product proto) {
		registerMap.put(name, proto);
	}

	public Product cloneFromRegister(String protoname) {
		// create by Clone()
		Product product = registerMap.get(protoname).createClone();
		return product;
	}
}
