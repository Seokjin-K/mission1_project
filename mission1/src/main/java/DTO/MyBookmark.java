package DTO;

import java.sql.Timestamp;

public class MyBookmark {

	private long myBookmarkId;
	private String myBookmarkName;
	private String wifiName;
	private Timestamp registrationDate;

	public MyBookmark(long myBookmarkId, String myBookmarkName, String wifiName, Timestamp registrationDate) {
		this.myBookmarkId = myBookmarkId;
		this.myBookmarkName = myBookmarkName;
		this.wifiName = wifiName;
		this.registrationDate = registrationDate;
	}

	public long getMyBookmarkId() {
		return myBookmarkId;
	}

	public void setMyBookmarkId(long myBookmarkId) {
		this.myBookmarkId = myBookmarkId;
	}

	public String getMyBookmarkName() {
		return myBookmarkName;
	}

	public void setMyBookmarkName(String myBookmarkName) {
		this.myBookmarkName = myBookmarkName;
	}

	public String getWifiName() {
		return wifiName;
	}

	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

}
