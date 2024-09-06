package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import DTO.History;
import DTO.MyBookmark;
import Utils.DBUtils;

public class DBServiceMyBookmark {

	public List<MyBookmark> select() {

		List<MyBookmark> myBookmarkList = new ArrayList<>();
		final String SQL = "select * from my_bookmark";

		// 리소스가 자동으로 닫히기 위해 Try-with-resources 사용
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL);
				ResultSet rs = preparedStatement.executeQuery()) {

			while (rs.next()) {
				MyBookmark myBookmark = new MyBookmark(rs.getLong("my_bookmark_id"), rs.getString("my_bookmark_name"),
						rs.getString("wifi_name"), rs.getTimestamp("registration_date"));

				myBookmarkList.add(myBookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myBookmarkList;
	}

	public int insert(MyBookmark myBookmark) throws SQLException {

		final String SQL = "INSERT INTO my_bookmark (my_bookmark_name, wifi_name, registration_date) VALUES (?, ?, ?)";
		int affected = 0;

		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SQL);

		preparedStatement.setString(1, myBookmark.getMyBookmarkName());
		preparedStatement.setString(2, myBookmark.getWifiName());
		preparedStatement.setTimestamp(3, myBookmark.getRegistrationDate());

		affected = preparedStatement.executeUpdate();

		if (affected > 0) {
			System.out.println("Row affected: " + affected);
		} else {
			System.out.println("No row affected.");
		}
		return affected;
	}

	public int delete(long myBookmarkId) {

		final String SQL = "delete from my_bookmark where my_bookmark_id = ?";
		int affected = 0;

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setLong(1, myBookmarkId);
			affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("Delete Row: " + affected);
			} else {
				System.out.println("No row affected.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

}
