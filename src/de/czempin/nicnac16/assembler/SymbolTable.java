package de.czempin.nicnac16.assembler;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

	private Map<String, Label> map = new HashMap<String, Label>();

	public Integer get(String symbol) {
		Label label =  map.get(symbol);
		if (label==null){
			return null;
		}
		return label.getValue();
	}

	public void put(String symbol, int currentAddress) {
		Label label =  map.get(symbol);
		if (label == null){
			label = new Label(symbol);
			label.setValue(currentAddress);
		}
	}

	public String handle(String symbol) {
		Label label = map.get(symbol);
		String retVal;
		if (label == null) {
			retVal = "000"; // to be filled in later
		} else {
			retVal = String.format("%03x", label.getValue());
		}
		return retVal;
	}

}
