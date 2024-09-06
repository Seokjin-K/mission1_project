package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Bookmark;
import Utils.DBUtils;

public class DBServiceBookmark {

	public List<Bookmark> select() {

		List<Bookmark> bookmarkList = new ArrayList<>();
		final String SQL = "select * from bookmark order by sequence";

		// 리소스가 자동으로 닫히기 위해 Try-with-resources 사용
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL);
				ResultSet rs = preparedStatement.executeQuery()) {

			while (rs.next()) {
				Bookmark bookmark = new Bookmark(rs.getLong("bookmark_id"), rs.getString("bookmark_name"),
						rs.getLong("sequence"), rs.getTimestamp("registration_date"), rs.getTimestamp("modify_date"));

				bookmarkList.add(bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookmarkList;
	}

	public int insert(Bookmark bookmark) throws SQLException {

		final String SQL = "insert into bookmark (bookmark_name, sequence, registration_date, modify_date) values (?, ?, ?, ?);";
		int affected = 0;

		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SQL);

		preparedStatement.setString(1, bookmark.getBookmarkName());
		preparedStatement.setLong(2, bookmark.getSequence());
		preparedStatement.setTimestamp(3, bookmark.getRegistrationDate());
		preparedStatement.setTimestamp(4, bookmark.getModifyDate());

		affected = preparedStatement.executeUpdate();

		if (affected > 0) {
			System.out.println("Row affected: " + affected);
		} else {
			System.out.println("No row affected.");
		}
		return affected;
	}

	public void delete(long bookmarkId) {

		final String SQL = "delete from bookmark where bookmark_id = ?";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setLong(1, bookmarkId);
			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("Delete Row: " + affected);
			} else {
				System.out.println("No row affected.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int update(Bookmark bookmark) throws SQLException {

		final String SQL = "update bookmark set bookmark_name = ?, sequence = ?, modify_date = ? where bookmark_id = ?";
		int affected = 0;

		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SQL);

		preparedStatement.setString(1, bookmark.getBookmarkName());
		preparedStatement.setLong(2, bookmark.getSequence());
		preparedStatement.setTimestamp(3, bookmark.getModifyDate());
		preparedStatement.setLong(4, bookmark.getBookmarkId());

		affected = preparedStatement.executeUpdate();

		if (affected > 0) {
			System.out.println("Update Rows: " + affected);
		} else {
			System.out.println("No rows affected.");
		}
		return affected;
	}

}
