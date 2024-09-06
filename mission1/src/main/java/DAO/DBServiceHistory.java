package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import DTO.History;
import Utils.DBUtils;

public class DBServiceHistory {

	public List<History> select() {

		List<History> historyList = new ArrayList<>();
		final String SQL = "select * from history";

		// 리소스가 자동으로 닫히기 위해 Try-with-resources 사용
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL);
				ResultSet rs = preparedStatement.executeQuery()) {

			while (rs.next()) {
				History history = new History(rs.getLong("history_id"), rs.getDouble("x_coord"),
						rs.getDouble("y_coord"), rs.getTimestamp("check_date"));

				historyList.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return historyList;
	}

	public int insert(History history) {

		final String SQL = "INSERT INTO history (x_coord, y_coord, check_date) " + "VALUES (?, ?, ?)";
		int affected = 0;

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setDouble(1, history.getxCoord());
			preparedStatement.setDouble(2, history.getyCoord());
			preparedStatement.setTimestamp(3, history.getCheckDate());

			affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("Row affected: " + affected);
			} else {
				System.out.println("No row affected.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	public void delete(long historyId) {

		final String SQL = "delete from history where history_id = ?";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setLong(1, historyId);
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

}
