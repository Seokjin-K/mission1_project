package Utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	public static Timestamp getCurrentTimestamp() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ss");
		return Timestamp.valueOf(currentDateTime);
	}

}
