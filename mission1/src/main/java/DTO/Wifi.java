package DTO;

import java.sql.Timestamp;

public class Wifi {

	private long wifiId;
	private String mgrNum;
	private String ssid;
	private String address;
	private String detailAddress;
	private String installFloor;
	private String installType;
	private String installAgency;
	private String servicteType;
	private String networkType;
	private int installYear;
	private String indoorOutdoor;
	private String connectionEnv;
	private double xCoord;
	private double yCoord;
	private Timestamp wordDate;

	public long getWifiId() {
		return wifiId;
	}

	public void setWifiId(long wifiId) {
		this.wifiId = wifiId;
	}

	public String getMgrNum() {
		return mgrNum;
	}

	public void setMgrNum(String mgrNum) {
		this.mgrNum = mgrNum;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getInstallFloor() {
		return installFloor;
	}

	public void setInstallFloor(String installFloor) {
		this.installFloor = installFloor;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public String getInstallAgency() {
		return installAgency;
	}

	public void setInstallAgency(String installAgency) {
		this.installAgency = installAgency;
	}

	public String getServicteType() {
		return servicteType;
	}

	public void setServicteType(String servicteType) {
		this.servicteType = servicteType;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public int getInstallYear() {
		return installYear;
	}

	public void setInstallYear(int installYear) {
		this.installYear = installYear;
	}

	public String getIndoorOutdoor() {
		return indoorOutdoor;
	}

	public void setIndoorOutdoor(String indoorOutdoor) {
		this.indoorOutdoor = indoorOutdoor;
	}

	public String getConnectionEnv() {
		return connectionEnv;
	}

	public void setConnectionEnv(String connectionEnv) {
		this.connectionEnv = connectionEnv;
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

	public Timestamp getWordDate() {
		return wordDate;
	}

	public void setWordDate(Timestamp wordDate) {
		this.wordDate = wordDate;
	}

}
