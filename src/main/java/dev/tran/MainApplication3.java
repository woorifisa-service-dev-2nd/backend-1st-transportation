package dev.tran;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import dev.bank.MainApplication;
import dev.tran.model.GaungyukBus;
import dev.tran.model.MauelBus;
import dev.tran.model.SineBus;
import dev.tran.model.Subway;
import dev.tran.model.Transportation;
import dev.tran.service.TransportationStatementProcessor;
import parser.CSVParser;

public class MainApplication3 {

//	private static final String RESOURCES = "src/main/resources/";

	// jar 실행용
	private static final String RESOURCES = "resources/";
	
	// 대중교통별 총 금액
	static int totalMauelBus = 0;
	static int totalSineBus = 0;
	static int totalGaungyukBus = 0;
	static int totalSubway = 0;

	public static void main(String[] args) {
//		List<Path> paths = new ArrayList<Path>(); // csv 파일 리스트
		List<String> paths = new ArrayList<String>(); // csv 파일 리스트
		final Map<String, Integer> map = new HashMap<String, Integer>(); // 사람별 총 요금 

		String[] name = { "song", "na", "cho", "kim" }; // 사용자 배열
		
		// 2번 코드
		// 키값(사용자)이 중복될 수 있기 때문에 2차원 배열로 생성
		String[][] maxArr = new String[name.length][2]; // 각 교통수단별 가장 많이 탑승한 사용자
		int[][] maxCountForTransportations = new int[name.length][4]; // 각 교통수단별 가장 많이 탑승한 사용자 최대값 구하기 위한 2차원 배열
		
		// 3번 코드
		int[] totalCharge = new int[4]; // 각 교통수단별 수입 계산

		// 각각 파일 분리
		for (int i = 0; i < name.length; i++) {
			
			try (InputStream inputStream = MainApplication3.class.getClassLoader().getResourceAsStream( name[i] + ".csv");
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					paths.add(line);
//					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
//			Path path = Paths.get(RESOURCES + name[i] + ".csv");
//			paths.add(path);
		}
		
//		for(String a : paths) {
//			System.out.println(a);
//		}

		try {
			MauelBus mauelBus = new MauelBus();
			GaungyukBus gaungyukBus = new GaungyukBus();
			SineBus sineBus = new SineBus();
			Subway subway = new Subway();
			
			System.out.println(paths);
			
			for (int i = 0; i < paths.size(); i++) {
//				List<String> lines = Files.readAllLines(paths.get(i));
				CSVParser parser = new CSVParser();
				List<Transportation> transaportationTransactions = parser.parseLinesFromCSV(paths);
				TransportationStatementProcessor tpProcessor = new TransportationStatementProcessor(transaportationTransactions);
				
				// 1번. 각각의 총 교통비가 많이 나온 사용자 순서
				map.put(name[i],
						tpProcessor.calculateTotalFare(transaportationTransactions, mauelBus, gaungyukBus, sineBus, subway));

				// 2번. 대중교통별 최대 횟수, 이용자 이름 출력
				maxCountForTransportations[i] = tpProcessor.calculateCountTransportation(transaportationTransactions, mauelBus,
						gaungyukBus, sineBus, subway);
				
				// 3번. 대중교통 전체 총액 출력
				int[] charge = new int[4];
				
				charge = tpProcessor.calculateIncomeByTransport(transaportationTransactions, mauelBus, gaungyukBus, sineBus, subway);
			
				// 대중교통 최종 금액 누적
				for(int j = 0 ; j < charge.length ; j++) {
					totalCharge[i] += charge[i];
				}
				
			}
			
			// 각 교통수단별 가장 많이 탑승한 사용자 최대값 구하기 위한 2차원 배열
			for (int j = 0; j < maxCountForTransportations.length; j++) {
				int max = 0;
				int idx = 0;
				for (int k = 0; k < maxCountForTransportations[0].length; k++) {
					if(max < maxCountForTransportations[k][j]) {
						max = maxCountForTransportations[k][j];
						idx = k;
					}
				}
				maxArr[j][0] = name[idx];
				maxArr[j][1] = Integer.toString(max);
			}

			List<String> keySet = new ArrayList<>(map.keySet());

			keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
			

			System.out.println("1. 폴더에 있는 csv파일들을 읽어서 각각의 총 교통비가 많이 나온 사용자 순서");
			
			for (String key : keySet) {
				System.out.print("이름 : " + key);
				System.out.println(", 요금 : " + map.get(key));
			}

			System.out.println("=====================================");
			System.out.println("2. 각 교통수단별 가장 많이 탑승한 사용자");
			System.out.println("마을 버스 : " + maxArr[0][0] + " " + maxArr[0][1] + "회 입니다.");
			System.out.println("광역 버스 : " + maxArr[1][0] + " " + maxArr[1][1] + "회 입니다.");
			System.out.println("시내 버스 : " + maxArr[2][0] + " " + maxArr[2][1] + "회 입니다.");
			System.out.println("지하철 : " + maxArr[3][0] + " " + maxArr[3][1] + "회 입니다.");

			System.out.println("=====================================");
			System.out.println("3. 각 교통수단별 수입 계산");
			System.out.println("마을 버스 : " + totalCharge[0] + "원입니다.");
			System.out.println("광역 버스 : " + totalCharge[1] + "원입니다.");
			System.out.println("시내 버스 : " + totalCharge[2] + "원입니다.");
			System.out.println("지하철 : " + totalCharge[3] + "원입니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
