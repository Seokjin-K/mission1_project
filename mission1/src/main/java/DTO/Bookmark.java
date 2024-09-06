package DTO;

import java.sql.Timestamp;
import java.util.Date;

public class Bookmark {

	private long bookmarkId;
	private String bookmarkName;
	private long sequence;
	private Timestamp registrationDate;
	private Timestamp modifyDate;
	
	public Bookmark(long bookmarkId, String bookmarkName, long sequence, Timestamp registrationDate, Timestamp modifyDate) {
		this.bookmarkId = bookmarkId;
		this.bookmarkName = bookmarkName;
		this.sequence = sequence;
		this.registrationDate = registrationDate;
		this.modifyDate = modifyDate;
	}

	public long getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(long bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

	public String getBookmarkName() {
		return bookmarkName;
	}

	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

}
