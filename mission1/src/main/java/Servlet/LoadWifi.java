package Servlet;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import DAO.DBServiceWifi;
import Utils.WifiAPIUtils;

@WebServlet("/loadWifi")
public class LoadWifi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int MAX_NUMBER_OF_WIFI_TO_SEARCH = 1000; // 데이터 요청은 1000건을 넘을 수 없다고 하기에 최대 1000으로 설정

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			JsonArray wifiData = WifiAPIUtils.getWifiData(MAX_NUMBER_OF_WIFI_TO_SEARCH);
			List<DTO.Wifi> wifiList = WifiAPIUtils.convertToWifiList(wifiData);

			DBServiceWifi dbServiceWifi = new DBServiceWifi();
			dbServiceWifi.insert(wifiList);
			
			request.setAttribute("totalWifiCount", dbServiceWifi.getWifiCount());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("loadWifi.jsp");
			dispatcher.forward(request, response);

		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
			response.getWriter().write("{\"status\": \"error\", \"message\": \"Request timed out\"}");
		} catch (Exception e) {
			throw new ServletException("WIFI 데이터를 검색하는 중 오류가 발생했습니다.", e);
		}
	}
	
}
