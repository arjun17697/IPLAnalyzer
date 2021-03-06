package com.bridgelabz.iplanalyzer;
import com.opencsv.bean.CsvBindByName;

public class IPLBatting {
	@CsvBindByName(column = "POS")
	int position;
	@CsvBindByName(column = "PLAYER")
	String player;
	@CsvBindByName(column = "MAT")
	int match;
	@CsvBindByName(column = "Inns")
	int innings;
	@CsvBindByName(column = "NO")
	int notOut;
	@CsvBindByName(column = "Runs")
	int runs;
	@CsvBindByName(column = "HS")
	String highestScore;
	@CsvBindByName(column = "Avg")
	String average;
	@CsvBindByName(column = "BF")
	int ballFaced;
	@CsvBindByName(column = "SR")
	double strikeRate;
	@CsvBindByName(column = "100")
	int century;
	@CsvBindByName(column = "50")
	int halfCentury;
	@CsvBindByName(column = "4s")
	int fours;
	@CsvBindByName(column = "6s")
	int sixes;

	@Override
	public String toString() {
		return "IPLBatting [position=" + position + ", player=" + player + ", match=" + match + ", innings=" + innings
				+ ", notOut=" + notOut + ", runs=" + runs + ", highestScore=" + highestScore + ", average=" + average
				+ ", ballFaced=" + ballFaced + ", strikeRate=" + strikeRate + ", century=" + century + ", halfCentury="
				+ halfCentury + ", fours=" + fours + ", sixes=" + sixes + "]";
	}
	
	public String getAverage() {
		try {
			Double.parseDouble(average);
			return average;
		} catch (NumberFormatException e) {
			average="0";
		}
		return average;
	}

	public double getStrikeRate() {
		return strikeRate;
	}

	public int getSixes() {
		return sixes;
	}

	public int getFours() {
		return fours;
	}

	public String getPlayer() {
		return player;
	}
	
	public int getRuns() {
		return runs;
	}
	
	public int getCentury() {
		return century;
	}
	
	public int getHalfCentury() {
		return halfCentury;
	}
}
