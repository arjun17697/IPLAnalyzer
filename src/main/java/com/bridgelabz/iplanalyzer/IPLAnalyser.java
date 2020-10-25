package com.bridgelabz.iplanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.bridgelabz.principleofdesign.service.CSVBuilderFactory;
import com.opencsv.exceptions.CsvException;

public class IPLAnalyser {
	private List<IPLBatting> iplBattingList;

	public int loadStateCSVData(String csvFilePath) throws AnalyserException {
		return 0;
	}

	public List<IPLBatting> loadBattingStats(String csvFilePath) throws  CsvException, AnalyserException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new AnalyserException("Wrong File Type", AnalyserException.ExceptionType.INVALID_FILE_PATH);
			}
			iplBattingList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatting.class);
			return iplBattingList;
		} catch (IOException e) {
			throw new AnalyserException("Wrong CSV File Path", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
	}
	public List<IPLBatting> sortByBattingAvgDesc(String csvFilePath) throws AnalyserException, CsvException{
		loadBattingStats(csvFilePath);
		if(iplBattingList==null||iplBattingList.size()==0)
			throw new AnalyserException("No Census Data Found", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		iplBattingList=iplBattingList.stream().map(batsman->{
			try {
				Double.parseDouble(batsman.average);
			}catch(NumberFormatException e) {
				batsman.average="0";
			}
			return batsman;
		}).collect(Collectors.toList());
		Collections.sort(iplBattingList,Comparator.comparing(batting-> Double.parseDouble(batting.average)));
		Collections.reverse(iplBattingList);
		return iplBattingList;
	}

}
