package DTO;

import java.sql.Timestamp;

public class History {

	private long historyId;
	private double xCoord;
	private double yCoord;
	private Timestamp checkDate;
	
	public History(long historyId, double xCoord, double yCoord, Timestamp checkDate) {
		this.historyId = historyId;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.checkDate = checkDate;
	}

	public long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}

	public double getxCoord() {
		return xCoord;
	}

	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}

	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}

	public Timestamp getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Timestamp checkDate) {
		this.checkDate = checkDate;
	}

}
