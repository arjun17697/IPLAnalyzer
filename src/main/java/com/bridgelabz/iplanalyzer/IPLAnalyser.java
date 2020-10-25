package com.bridgelabz.iplanalyzer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.opencsv.exceptions.CsvException;

public class IPLAnalyser {
	private List<IPLBatting> iplBattingList;
	private CsvFileLoader csvFileLoader;

	public IPLAnalyser() {
		csvFileLoader = new CsvFileLoader();
	}

	public List<IPLBatting> sortByBattingAvgDesc(String csvFilePath) throws AnalyserException, CsvException {
		iplBattingList = csvFileLoader.loadBattingStats(csvFilePath);
		return iplBattingList
				.stream().sorted(Comparator
						.comparing(batting -> Double.parseDouble(((IPLBatting) batting).getAverage())).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> sortByStrikeRateDesc(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = csvFileLoader.loadBattingStats(csvFilePath);
		return iplBattingList.stream().sorted(Comparator.comparing(IPLBatting::getStrikeRate).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> sortBySixesDesc(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = csvFileLoader.loadBattingStats(csvFilePath);
		return iplBattingList.stream().sorted(Comparator.comparing(IPLBatting::getSixes).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> sortByFoursDesc(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = csvFileLoader.loadBattingStats(csvFilePath);
		return iplBattingList.stream().sorted(Comparator.comparing(IPLBatting::getFours).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> getBestSRwithSixesFours(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = sortByStrikeRateDesc(csvFilePath);	
		return iplBattingList.stream().sorted(Comparator.comparing(b -> 
		((IPLBatting) b).getSixes()*6 + ((IPLBatting) b).getFours()*4).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> getBestAvgWithBestSR(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = sortByStrikeRateDesc(csvFilePath);	
		return iplBattingList.stream().sorted(Comparator.comparing(b -> 
		Double.parseDouble(((IPLBatting) b).getAverage())).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> getBestAvgWithMaxRuns(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = sortByBattingAvgDesc(csvFilePath);	
		Comparator<IPLBatting> a =Comparator.comparing(IPLBatting::getRuns)
				.thenComparing(Comparator.comparing(IPLBatting::getAverage)).reversed();
		return iplBattingList.stream().sorted(a)
				.collect(Collectors.toList());
	}

}
