package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.tran.model.Transportation;

public class CSVParser {
	final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// 메서드
	// 1. 데이터에서 한 줄만 파싱하는 기능+
	// 이 클래스 내에서만 사용할 메서드
	private Transportation parseFromCSV(final String line) {
		
		String[] columns = line.split(","); // tsv(Tab separated value), tab으로 구분된 파일
		
		LocalDateTime boardingTime = LocalDateTime.parse(columns[0], DATE_PATTERN); // 문자열 -> 날짜 타입으로 파싱
		
//		String dateString = boardingTime.format(DATE_PATTERN);
//		System.out.println(dateString + "datestring");
		
		String transportationMode = columns[1]; // 거래처 데이터 파싱
		LocalDateTime alightingTime = LocalDateTime.parse(columns[2], DATE_PATTERN);

		// 파싱된 개별 타입의 값들을 BankTransaction이라는 클래스로 묶어줌(Wrapping)
		Transportation transportation = new Transportation(boardingTime, transportationMode, alightingTime);

		return transportation;
	}

	// 2. 한줄씩 파싱된 데이터를 리스트에 추가하는 기능
	public List<Transportation> parseLinesFromCSV(List<String> lines) {
		
		
		List<Transportation> transportationTransactions = new ArrayList<Transportation>();
		
		
		for (String line : lines) {
			Transportation transportationTransaction = parseFromCSV(line);
			transportationTransactions.add(transportationTransaction); // 배열의 요소로 추가
		}

		return transportationTransactions;
	}
}
