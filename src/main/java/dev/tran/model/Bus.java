package dev.tran.model;

public class Bus {
	String type = "";
	int charge = 0;

	public Bus(String type, int charge) {
		this.type = type;
		this.charge = charge;
	}
	
	public String getTransportationMode() {
		return type;
	}

	public int getCharge() {
		return charge;
	}
}
