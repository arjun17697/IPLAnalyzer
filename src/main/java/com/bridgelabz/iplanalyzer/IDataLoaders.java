package com.bridgelabz.iplanalyzer;

import java.util.List;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.opencsv.exceptions.CsvException;

public interface IDataLoaders {
	public <E> List<E> loadStats(String csvFilePath, Class<E> csvClass) throws AnalyserException, CsvException;

}
