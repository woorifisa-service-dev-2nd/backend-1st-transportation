package dev.tran.service;

import java.util.List;

import dev.tran.model.GaungyukBus;
import dev.tran.model.MauelBus;
import dev.tran.model.SineBus;
import dev.tran.model.Subway;
import dev.tran.model.Transportation;

public class TransportationStatementProcessor {
	private List<Transportation> tpTransactions;
	
	GaungyukBus gb = new GaungyukBus();
	MauelBus mb = new MauelBus();
	SineBus sb = new SineBus();
	Subway subway = new Subway();
	
	public TransportationStatementProcessor(List<Transportation> tpTransactions) {
		this.tpTransactions = tpTransactions;
	}

	public int calculateTotalFare(List<Transportation> transaportationTransactions, MauelBus mauelBus,
			GaungyukBus gaungyukBus, SineBus sineBus, Subway subway) {

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
		return totalCharge;
	}
	
	public int[] calculateCountTransportation(List<Transportation> transaportationTransactions,
			MauelBus mauelBus, GaungyukBus gaungyukBus, SineBus sineBus, Subway subway) {
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

		return val;
	}
	
	public int[] calculateIncomeByTransport(List<Transportation> transaportationTransactions, MauelBus mauelBus,
			GaungyukBus gaungyukBus, SineBus sineBus, Subway subway) {
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
		
		return totalCharge;
	}

}
