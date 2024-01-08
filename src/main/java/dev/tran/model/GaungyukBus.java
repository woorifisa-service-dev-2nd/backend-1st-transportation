package dev.tran.model;

public class GaungyukBus implements TransportationInterface {
	String transportationMode = "광역 버스";
	int charge = 3000;

	public String getTransportationMode() {
		return transportationMode;
	}

	public int getCharge() {
		return charge;
	}
}
