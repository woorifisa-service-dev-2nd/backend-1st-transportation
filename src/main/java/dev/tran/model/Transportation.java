package dev.tran.model;

import java.time.LocalDateTime;

public class Transportation {
	private LocalDateTime boardingTime; // 탑승시간
	private String transportationMode; // 교통수단
	private LocalDateTime alightingTime; // 하차시간
	
	public Transportation(LocalDateTime boardingTime, String transportationMode, LocalDateTime alightingTime) {
		super();
		this.boardingTime = boardingTime;
		this.transportationMode = transportationMode;
		this.alightingTime = alightingTime;
	}
	public LocalDateTime getBoardingTime() {
		return boardingTime;
	}
	public void setBoardingTime(LocalDateTime boardingTime) {
		this.boardingTime = boardingTime;
	}
	public String getTransportationMode() {
		return transportationMode;
	}
	public void setTransportationMode(String transportationMode) {
		this.transportationMode = transportationMode;
	}
	public LocalDateTime getAlightingTime() {
		return alightingTime;
	}
	public void setAlightingTime(LocalDateTime alightingTime) {
		this.alightingTime = alightingTime;
	}
	
	

	
	
}
