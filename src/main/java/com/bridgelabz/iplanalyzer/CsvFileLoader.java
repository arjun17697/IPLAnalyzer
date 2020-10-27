package com.bridgelabz.iplanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.bridgelabz.principleofdesign.service.CSVBuilderFactory;
import com.bridgelabz.iplanalyzer.IPLAllRounder;
import com.opencsv.exceptions.CsvException;

public class CsvFileLoader implements IDataLoaders{
	
	private List<IPLAllRounder> iplAllRounderList= new ArrayList<IPLAllRounder>();
	public List<IPLBatting> loadBattingStats(String csvFilePath) throws CsvException, AnalyserException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new AnalyserException("Wrong File Type", AnalyserException.ExceptionType.INVALID_FILE_PATH);
			}
			List<IPLBatting> iplBattingList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader,
					IPLBatting.class);
			return iplBattingList;
		} catch (IOException e) {
			throw new AnalyserException("Wrong CSV File Path", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		} catch (RuntimeException e) {
			throw new AnalyserException(e.getCause().getMessage(), AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
	}

	public List<IPLBowling> loadBowlingStats(String csvFilePath) throws AnalyserException, CsvException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new AnalyserException("Wrong File type", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
			}
			List<IPLBowling> iplBowlingList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader,
					IPLBowling.class);
			if (iplBowlingList == null || iplBowlingList.size() == 0)
				throw new AnalyserException("No Census data found", AnalyserException.ExceptionType.INVALID_FILE_PATH);

			return iplBowlingList;
		} catch (IOException e) {
			throw new AnalyserException("Incorrect csv file path", AnalyserException.ExceptionType.INVALID_FILE_PATH);
		} catch (RuntimeException e) {
			throw new AnalyserException(e.getCause().getMessage(), AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
	}
	
	@Override
	public <E> List<E> loadStats(String csvFilePath, Class<E> csvClass) throws AnalyserException, CsvException{
		String[] file=csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new AnalyserException("Wrong File Type", AnalyserException.ExceptionType.INVALID_FILE_PATH);
			}
			List<E> csvList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader,
					csvClass);
			return csvList;
		} catch (IOException e) {
			throw new AnalyserException("Wrong CSV File Path", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		} catch (RuntimeException e) {
			throw new AnalyserException(e.getCause().getMessage(), AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
	}
	
	
	public List<IPLAllRounder> loadStats(String batsmanFilePath, String bowlerFilePath) throws AnalyserException, CsvException{
		List<IPLBatting> iplBatsmanList=loadStats(batsmanFilePath,IPLBatting.class);
		List<IPLBowling> iplBowlerList= loadStats(bowlerFilePath,IPLBowling.class); 
		
		iplBatsmanList.stream().forEach(batsman->{
			IPLBowling bowlers = iplBowlerList.stream()
					.filter(bowler-> bowler.getPlayer().equalsIgnoreCase(batsman.getPlayer())).findFirst()
					.orElse(null);
			if (bowlers!=null) {
				iplAllRounderList.add(new IPLAllRounder(batsman.getPlayer(),batsman.getAverage(),
						bowlers.getAverage(), batsman.getRuns(),bowlers.getWickets()));
			}
		});
		return iplAllRounderList;
		}
	}


