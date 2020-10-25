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
		csvFileLoader=new CsvFileLoader();
	}

	
	public List<IPLBatting> sortByBattingAvgDesc(String csvFilePath) throws AnalyserException, CsvException{
		iplBattingList=csvFileLoader.loadBattingStats(csvFilePath);
		return iplBattingList.
				stream().sorted(Comparator
						.comparing(batting->Double.parseDouble(((IPLBatting)batting).getAverage())).
				reversed()).collect(Collectors.toList());
	}


	public List<IPLBatting> sortByStrikeRateDesc(String csvFilePath) throws CsvException, AnalyserException {
		iplBattingList=csvFileLoader.loadBattingStats(csvFilePath);
		return iplBattingList.
				stream().sorted(Comparator
						.comparing(IPLBatting::getStrikeRate).
				reversed()).collect(Collectors.toList());
	}

}
