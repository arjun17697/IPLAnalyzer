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
public class IPLAnalyserTest {
	private static final String IPL_BATTING = "C:/Users/HP LAP/Desktop/BridgeLabz/IPLAnalyzer/iplanalyzer/src/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_BOWLING = "C:/Users/HP LAP/Desktop/BridgeLabz/IPLAnalyzer/iplanalyzer/src/resources/IPL2019FactsheetMostWkts.csv";
	IPLAnalyser iplAnalyser;
	List<IPLAllRounder> sortedAllrounderList;

	@Before
	public void initialzier() {
		iplAnalyser = new IPLAnalyser();
	}

	@Test
	public void givenIPLDataShouldReturnMaxAvg() throws AnalyserException, CsvException {
		List<IPLBatting> highestBattingAvg = iplAnalyser.sortByBattingAvgDesc(IPL_BATTING);
		assertEquals("83.2", highestBattingAvg.get(0).getAverage());
	}

	@Test
	public void givenIPLDataShouldReturnMaxSR() throws AnalyserException, CsvException {
		List<IPLBatting> sortedBySRList = iplAnalyser.sortByStrikeRateDesc(IPL_BATTING);
		assertEquals(333.33, sortedBySRList.get(0).getStrikeRate(), 0);
	}

	@Test
	public void givenIPLDataShouldReturnMaxSixHitter() throws AnalyserException, CsvException {
		List<IPLBatting> sortedBySixes = iplAnalyser.sortBySixesDesc(IPL_BATTING);
		assertEquals("Andre Russell", sortedBySixes.get(0).getPlayer());
	}

	@Test
	public void givenIPLDataShouldReturnMaxFourHitter() throws AnalyserException, CsvException {
		List<IPLBatting> sortedBySixes = iplAnalyser.sortByFoursDesc(IPL_BATTING);
		assertEquals("Shikhar Dhawan", sortedBySixes.get(0).getPlayer());
	}

	@Test
	public void givenIPLDataShouldReturnMaxSixFourWithBestSR() throws AnalyserException, CsvException {
		List<IPLBatting> sortedListByBoundary = iplAnalyser.getBestSRwithSixesFours(IPL_BATTING);
		assertEquals("Andre Russell", sortedListByBoundary.get(0).getPlayer());
	}

	@Test
	public void givenIPLDataShouldReturnBestAvgwithBestSR() throws AnalyserException, CsvException {
		List<IPLBatting> sortedListByBoundary = iplAnalyser.getBestAvgWithBestSR(IPL_BATTING);
		assertEquals("MS Dhoni", sortedListByBoundary.get(0).getPlayer());
	}

	@Test
	public void givenIPLDataShouldReturnCricketerWithMaxRunsandBestAvg() throws AnalyserException, CsvException {
		List<IPLBatting> sortedListByAvg = iplAnalyser.getBestAvgWithMaxRuns(IPL_BATTING);
		assertEquals("David Warner ", sortedListByAvg.get(0).getPlayer());
	}

	@Test
	public void givenIPLBolwingDataShouldReturnMaxAvg() throws AnalyserException, CsvException {
		List<IPLBowling> highestBowlingAvg = iplAnalyser.sortByBowlingAvgDesc(IPL_BOWLING);
		assertEquals("11", highestBowlingAvg.get(0).getAverage());
	}

	@Test
	public void givenIPLBolwingDataShouldReturnMaxSR() throws AnalyserException, CsvException {
		List<IPLBowling> highestBowlingSR = iplAnalyser.sortByBowlingSRDesc(IPL_BOWLING);
		assertEquals("10.75", highestBowlingSR.get(0).getStrikeRate());
	}

	@Test
	public void givenIPLBolwingDataShouldReturnPlayerHavingBestEconomy() throws AnalyserException, CsvException {
		List<IPLBowling> highestBowlingEconomy = iplAnalyser.sortByBowlingEconomyDesc(IPL_BOWLING);
		assertEquals("Shivam Dube", highestBowlingEconomy.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBolwingDataShouldReturnPlayerHavingBestSRWith5w4w() throws AnalyserException, CsvException {
		List<IPLBowling> bestSRwith4w5w = iplAnalyser.sortByBestSRWith4w5w(IPL_BOWLING);
		assertEquals("Kagiso Rabada", bestSRwith4w5w.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBolwingDataShouldReturnPlayerHavingBestSRWithGreatAvg() throws AnalyserException, CsvException {
		List<IPLBowling> bestSRwithGreatAvg = iplAnalyser.sortByBestSRWithGreatAvg(IPL_BOWLING);
		assertEquals("Alzarri Joseph", bestSRwithGreatAvg.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBolwingDataShouldReturnPlayerWithGreatAvgWhotookMaxWickets() throws AnalyserException, CsvException {
		List<IPLBowling> maxWicketswithGreatAvg = iplAnalyser.sortByMaxWktsWithGreatAvg(IPL_BOWLING);
		assertEquals("Imran Tahir", maxWicketswithGreatAvg.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLDataShouldReturnAllRounder() throws AnalyserException, CsvException{
		sortedAllrounderList=iplAnalyser.sortAllrounderData(IPL_BATTING,IPL_BOWLING);
		assertEquals("Andre Russell",sortedAllrounderList.get(0).getPlayer());
	}
}