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
}
