package dev.tran.model;

public interface TransportationInterface {
	public String transportationMode = "";
	public int charge = 0;
	
	int getCharge();
	String getTransportationMode();
}
