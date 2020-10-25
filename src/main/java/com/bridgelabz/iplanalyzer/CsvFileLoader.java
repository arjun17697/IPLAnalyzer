package com.bridgelabz.iplanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.bridgelabz.principleofdesign.service.CSVBuilderFactory;
import com.opencsv.exceptions.CsvException;

public class CsvFileLoader {
	public List<IPLBatting> loadBattingStats(String csvFilePath) throws CsvException, AnalyserException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new AnalyserException("Wrong File Type", AnalyserException.ExceptionType.INVALID_FILE_PATH);
			}
			List<IPLBatting> iplBattingList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatting.class);
			return iplBattingList;
		} catch (IOException e) {
			throw new AnalyserException("Wrong CSV File Path", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}catch (RuntimeException e) {
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

}
