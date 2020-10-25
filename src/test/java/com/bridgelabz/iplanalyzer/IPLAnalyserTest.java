package com.bridgelabz.iplanalyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.opencsv.exceptions.CsvException;

/**
 * Unit test for simple App.
 */
public class IPLAnalyserTest 
{
	private static final String IPL_BATTING = "C:/Users/HP LAP/Desktop/BridgeLabz/IPLAnalyzer/iplanalyzer/src/resources/IPL2019FactsheetMostRuns.csv";
	IPLAnalyser iplAnalyser;
	
	@Before
	public void initialzier() {
		iplAnalyser=new IPLAnalyser();
	}
	
    @Test
    public void givenIPLDataShouldReturnMaxAvg() throws AnalyserException, CsvException
    {
    	List<IPLBatting> highestBattingAvg=iplAnalyser.sortByBattingAvgDesc(IPL_BATTING);
    	assertEquals("83.2",highestBattingAvg.get(0).getAverage());
    }
    
    @Test
    public void givenIPLDataShouldReturnMaxSR() throws AnalyserException, CsvException
    {
    	List<IPLBatting> sortedBySRList=iplAnalyser.sortByStrikeRateDesc(IPL_BATTING);
    	assertEquals(333.33,sortedBySRList.get(0).getStrikeRate(),0);
    }
}
