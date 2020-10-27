package com.bridgelabz.iplanalyzer;

public class IPLAllRounder {

	private String player;
	private String battingAverage;
	private String bowlingAverage;
	private int runs;
	private int wickets;

	public IPLAllRounder(String player, String battingAverage, String bowlingAverage, int runs, int wickets) {
		super();
		this.player = player;
		this.battingAverage = battingAverage;
		this.bowlingAverage = bowlingAverage;
		this.runs = runs;
		this.wickets = wickets;
	}

	public String getPlayer() {
		return player;
	}

	public double getBattingAverage() {
		return Double.parseDouble(battingAverage);
	}

	public double getBowlingAverage() {
		return Double.parseDouble(bowlingAverage);
	}

	public int getRuns() {
		return runs;
	}

	public int getWickets() {
		return wickets;
	}
	
	public double getPerformanceByAverage() {
		return getBattingAverage()-getBowlingAverage();
	}
	
	public double getPerformanceByRunsAndWickets() {
		return (this.runs*this.wickets);
	}
	@Override
	public String toString() {
		return "IPLAllRounder [player=" + player + ", battingAverage=" + battingAverage + ", bowlingAverage="
				+ bowlingAverage + ", runs=" + runs + ", wickets=" + wickets + "]";
	}

}
