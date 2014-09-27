package de.czempin.nicnac16.assembler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

	private Map<String, Label> map = new HashMap<String, Label>();

	public Integer get(String symbol) {
		Label label = map.get(symbol);
		if (label == null) {
			return null;
		}
		return label.getValue();
	}

	public void put(String symbol, int currentAddress) {
		Label label = map.get(symbol);
		if (label == null) {
			label = new Label(symbol);
			label.setValue(currentAddress);
			this.map.put(symbol, label);
		} else {
			Integer value = label.getValue();
			if (value != null) {
				System.out.println("Warning: " + symbol + " tried to redefine: " + currentAddress);
			}else {
				label.setValue(currentAddress);
			}
		}
	}

	public String handle(String symbol, int currentAddress) {
		Label label = map.get(symbol);
		String retVal;
		if (label == null) {
			label = new Label(symbol);
			label.addReference(currentAddress);
			this.map.put(symbol, label);
			retVal = "000"; // to be filled in later
		} else {
			retVal = String.format("%03x", label.getValue());
		}
		return retVal;
	}

	public void print() {
		Set<String> keys = this.map.keySet();
		for (String symbol : keys) {
			Label label = this.map.get(symbol);
			Integer value = label.getValue();
			Collection<Integer> references = label.getReferences();
			System.out.println(symbol + ": " + value);
			for (Integer ref : references) {
				System.out.println("R:"+ref);
			}
		}
	}

}
