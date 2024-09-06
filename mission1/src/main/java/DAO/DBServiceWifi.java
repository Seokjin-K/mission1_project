package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DTO.Wifi;
import Utils.DBUtils;

public class DBServiceWifi {
	
	 public int getWifiCount() throws SQLException {
	        String countQuery = "SELECT COUNT(*) FROM wifi";

	        try (Connection connection = DBUtils.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                return resultSet.getInt(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new SQLException("와이파이 정보 개수 에러ㅏ", e);
	        }
	        return 0;
	    }

	public void insert(List<Wifi> wifiList) throws SQLException {
		
		final String INSERT_WIFI_SQL = "INSERT INTO wifi (mgr_number, ssid, address, detail_address, install_floor, install_type, install_agency, "
				+ "service_type, network_type, install_year, indoor_outdoor, connection_env, x_coord, y_coord, work_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		 try (Connection connection = DBUtils.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WIFI_SQL)) {
	            
	            for (Wifi wifi : wifiList) {
	                preparedStatement.setString(1, wifi.getMgrNum());
	                preparedStatement.setString(2, wifi.getSsid());
	                preparedStatement.setString(3, wifi.getAddress());
	                preparedStatement.setString(4, wifi.getDetailAddress());
	                preparedStatement.setString(5, wifi.getInstallFloor());
	                preparedStatement.setString(6, wifi.getInstallType());
	                preparedStatement.setString(7, wifi.getInstallAgency());
	                preparedStatement.setString(8, wifi.getServicteType());
	                preparedStatement.setString(9, wifi.getNetworkType());
	                preparedStatement.setInt(10, wifi.getInstallYear());
	                preparedStatement.setString(11, wifi.getIndoorOutdoor());
	                preparedStatement.setString(12, wifi.getConnectionEnv());
	                preparedStatement.setDouble(13, wifi.getxCoord());
	                preparedStatement.setDouble(14, wifi.getyCoord());
	                preparedStatement.setTimestamp(15, wifi.getWordDate());

	                preparedStatement.addBatch();
	            }
	            preparedStatement.executeBatch();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new SQLException("와이파이 정보 저장 실패", e);
	        }
	}
	
}
