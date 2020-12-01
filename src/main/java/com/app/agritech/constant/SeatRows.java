package com.app.agritech.constant;

public enum SeatRows {

	A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"), I("I"), J("J"), K("K"), L("L");

	public String getValue() {
		return value;
	}

	SeatRows(String value) {
		this.value = value;
	}

	String value;

}
