package dev.tran.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;

import dev.tran.model.Bus;
import dev.tran.model.Subway;
import dev.tran.model.Transportation;

public class TransportationStatementProcessor {
	private static final Logger logger = Logger.getLogger(TransportationStatementProcessor.class.getName());
	
	private List<Transportation> transaportationTransactions;
	
	Bus mb = new Bus("마을 버스", 1200);
	Bus gb = new Bus("광역 버스", 3000);
	Bus sb = new Bus("시내 버스", 1500);
	Subway subway = new Subway();
	
	public TransportationStatementProcessor(List<Transportation> tpTransactions) {
		this.transaportationTransactions = tpTransactions;
	}

	public int calculateTotalFare(Bus mauelBus,
			Bus gaungyukBus, Bus sineBus, Subway subway) {

		int totalCharge = 0;

		for (int i = 0; i < transaportationTransactions.size(); i++) {
			String name = transaportationTransactions.get(i).getTransportationMode();

			if (name.equals(mb.getTransportationMode())) {
				totalCharge += mauelBus.getCharge();
			} else if (name.equals(gb.getTransportationMode())) {
				totalCharge += gaungyukBus.getCharge();
			} else if (name.equals(sb.getTransportationMode())) {
				totalCharge += sineBus.getCharge();
			} else if (name.substring(0, 3).equals(subway.getTransportationMode())) {
				totalCharge += subway.getCharge();
			}
		}
		
		
		try {
			final String fileName = "myfile1.log";
			Handler fileHandler = new FileHandler(fileName);
			
			Formatter formatter = new MyFormatter();
			fileHandler.setFormatter(formatter);  
			
			logger.addHandler(fileHandler);
			
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		logger.info("각각의 총 교통비가 많이 나온 사용자 순으로 출력");
		
		
		return totalCharge;
	}
	
	public int[] calculateCountTransportation(Bus mauelBus, Bus gaungyukBus, Bus sineBus, Subway subway) {
		int[] val = new int[4];

		// 대중교통별 총 횟수
		int totalMauelBusCount = 0;
		int totalSineBusCount = 0;
		int totalGaungyukBusCount = 0;
		int totalSubwayCount = 0;

		for (int i = 0; i < transaportationTransactions.size(); i++) {
			String name = transaportationTransactions.get(i).getTransportationMode();

			if (name.equals(mb.getTransportationMode())) {
				totalMauelBusCount++;
			} else if (name.equals(gb.getTransportationMode())) {
				totalGaungyukBusCount++;
			} else if (name.equals(sb.getTransportationMode())) {
				totalSineBusCount++;
			} else if (name.substring(0, 3).equals(subway.getTransportationMode())) {
				totalSubwayCount++;
			}
		}

		val[0] = totalMauelBusCount;
		val[1] = totalGaungyukBusCount;
		val[2] = totalSineBusCount;
		val[3] = totalSubwayCount;
		
		
		try {
			final String fileName = "myfile2.log";
			Handler fileHandler = new FileHandler(fileName);
			
			Formatter formatter = new MyFormatter();
			fileHandler.setFormatter(formatter);  
			
			logger.addHandler(fileHandler);
			
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		logger.info("각 교통수단별 가장 많이 탑승한 사용자 출력");
		

		return val;
	}
	
	public int[] calculateIncomeByTransport(Bus mauelBus,
			Bus gaungyukBus, Bus sineBus, Subway subway) {
		int[] totalCharge = new int[4];
		int totalMauelBus = 0;
		int totalGaungyukBus = 0;
		int totalSineBus = 0;
		int totalSubway = 0;
		
		for (int i = 0; i < transaportationTransactions.size(); i++) {
			String name = transaportationTransactions.get(i).getTransportationMode();

			if (name.equals(mb.getTransportationMode())) {
				totalMauelBus += mauelBus.getCharge();
			} else if (name.equals(gb.getTransportationMode())) {
				totalGaungyukBus += gaungyukBus.getCharge();
			} else if (name.equals(sb.getTransportationMode())) {
				totalSineBus += sineBus.getCharge();
			} else if (name.substring(0, 3).equals(subway.getTransportationMode())) {
				totalSubway += subway.getCharge();
			}
		}
		
		totalCharge[0] = totalMauelBus;
		totalCharge[1] = totalGaungyukBus;
		totalCharge[2] = totalSineBus;
		totalCharge[3] = totalSubway;
		
		
		try {
			final String fileName = "myfile3.log";
			Handler fileHandler = new FileHandler(fileName);
			
			Formatter formatter = new MyFormatter();
			fileHandler.setFormatter(formatter);  
			
			logger.addHandler(fileHandler);
			
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		logger.info("각 교통수단별 수입 계산");
		
		
		return totalCharge;
	}

}
