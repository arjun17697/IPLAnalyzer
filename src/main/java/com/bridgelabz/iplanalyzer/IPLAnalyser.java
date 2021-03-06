package com.bridgelabz.iplanalyzer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.opencsv.exceptions.CsvException;

public class IPLAnalyser {
	private List<IPLBatting> iplBattingList;
	private List<IPLBowling> iplBowlingList;
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
		return iplBattingList
				.stream().sorted(Comparator
						.comparing(b -> ((IPLBatting) b).getSixes() * 6 + ((IPLBatting) b).getFours() * 4).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> getBestAvgWithBestSR(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = sortByStrikeRateDesc(csvFilePath);
		return iplBattingList.stream()
				.sorted(Comparator.comparing(b -> Double.parseDouble(((IPLBatting) b).getAverage())).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> getBestAvgWithMaxRuns(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = sortByBattingAvgDesc(csvFilePath);
		Comparator<IPLBatting> a = Comparator.comparing(IPLBatting::getRuns)
				.thenComparing(Comparator.comparing(IPLBatting::getAverage)).reversed();
		return iplBattingList.stream().sorted(a).collect(Collectors.toList());
	}

	public List<IPLBowling> sortByBowlingAvgDesc(String csvFilePath) throws AnalyserException, CsvException {
		iplBowlingList = csvFileLoader.loadBowlingStats(csvFilePath);
		return iplBowlingList.stream()
				.sorted(Comparator.comparing(bowling -> Double.parseDouble(((IPLBowling) bowling).getAverage())))
				.collect(Collectors.toList());
	}

	public List<IPLBowling> sortByBowlingSRDesc(String csvFilePath) throws AnalyserException, CsvException {
		iplBowlingList = csvFileLoader.loadBowlingStats(csvFilePath);
		return iplBowlingList.stream().sorted(Comparator.comparing(IPLBowling::getStrikeRate))
				.collect(Collectors.toList());

	}

	public List<IPLBowling> sortByBowlingEconomyDesc(String csvFilePath) throws AnalyserException, CsvException {
		iplBowlingList = csvFileLoader.loadBowlingStats(csvFilePath);
		return iplBowlingList.stream().sorted(Comparator.comparing(IPLBowling::getEconomy))
				.collect(Collectors.toList());
	}

	public List<IPLBowling> sortByBestSRWith4w5w(String csvFilePath) throws AnalyserException, CsvException {
		iplBowlingList = sortByBowlingSRDesc(csvFilePath);
		Comparator<IPLBowling> a = Comparator.comparing(IPLBowling::getFourandFiveWickets).reversed()
				.thenComparing(Comparator.comparing(IPLBowling::getStrikeRate));
		return iplBowlingList.stream().sorted(a).collect(Collectors.toList());
	}

	public List<IPLBowling> sortByBestSRWithGreatAvg(String csvFilePath) throws AnalyserException, CsvException {
		iplBowlingList = sortByBowlingAvgDesc(csvFilePath);
		Comparator<IPLBowling> a = Comparator.comparing(IPLBowling::getStrikeRate).reversed()
				.thenComparing(Comparator.comparing(IPLBowling::getAverage));
		return iplBowlingList.stream().sorted(a).collect(Collectors.toList());
	}

	public List<IPLBowling> sortByMaxWktsWithGreatAvg(String csvFilePath) throws AnalyserException, CsvException {
		iplBowlingList = sortByBowlingAvgDesc(csvFilePath);
		Comparator<IPLBowling> a = Comparator.comparing(IPLBowling::getWickets).reversed()
				.thenComparing(Comparator.comparing(IPLBowling::getAverage));
		return iplBowlingList.stream().sorted(a).collect(Collectors.toList());
	}

	public List<IPLAllRounder> sortAllrounderData(String batsmanFilePath, String bowlerFilePath)
			throws AnalyserException, CsvException {
		List<IPLAllRounder> iplAllRounderList = csvFileLoader.loadStats(batsmanFilePath, bowlerFilePath);
		return iplAllRounderList.stream()
				.sorted(Comparator.comparing(IPLAllRounder::getPerformanceByAverage).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLAllRounder> sortBestAllrounderData(String batsmanFilePath, String bowlerFilePath)
			throws AnalyserException, CsvException {
		List<IPLAllRounder> iplAllRounderList = csvFileLoader.loadStats(batsmanFilePath, bowlerFilePath);
		return iplAllRounderList.stream()
				.sorted(Comparator.comparing(IPLAllRounder::getPerformanceByRunsAndWickets).reversed())
				.collect(Collectors.toList());
	}

	public List<IPLBatting> getBestAvgWithMax100s(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = sortByBattingAvgDesc(csvFilePath);
		Comparator<IPLBatting> a = Comparator.comparing(IPLBatting::getCentury)
				.thenComparing(Comparator.comparing(IPLBatting::getAverage)).reversed();
		return iplBattingList.stream().sorted(a).collect(Collectors.toList());
	}
	
	public List<IPLBatting> getBestAvgWithNoCenturyOrHalfCentuty(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList = csvFileLoader.loadBattingStats(csvFilePath);
		final Comparator<Object> a=Comparator.comparing(b -> {
			if (((IPLBatting) b).getCentury() + ((IPLBatting) b).getHalfCentury() == 0)
				return Double.parseDouble(((IPLBatting) b).getAverage());
			else
				return 0.0;
		}).reversed();
		return iplBattingList
			.stream().sorted(a).collect(Collectors.toList());
	}
	
}
