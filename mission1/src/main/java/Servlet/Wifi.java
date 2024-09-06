package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import DAO.DBServiceHistory;
import DTO.History;
import Utils.DateTimeUtils;
import Utils.WifiAPIUtils;

@WebServlet("/wifi")
public class Wifi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int MAX_NUMBER_OF_WIFI_TO_SEARCH = 1000; // 데이터 요청은 1000건을 넘을 수 없다고 하기에 최대 1000으로 설정
	private static final int MAX_DISTANCE_TO_SEARCH = 1; // km

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String latParam = request.getParameter("lat");
		String lntParam = request.getParameter("lnt");

		// Test 제로베이스
		/*
		 * latParam = "37.5030426";
		 * lntParam = "127.041588";
		 */

		if (latParam == null || lntParam == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "위도와 경도가 필요합니다.");
			return;
		}

		final double LAT = Double.parseDouble(latParam);
		final double LNT = Double.parseDouble(lntParam);

		try {
			JsonArray wifiData = WifiAPIUtils.searchWifiAPI(MAX_NUMBER_OF_WIFI_TO_SEARCH, MAX_DISTANCE_TO_SEARCH, LAT,
					LNT);
			WifiAPIUtils.writeJsonResponse(response, wifiData);
			historyRegistraion(LAT, LNT);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
			response.getWriter().write("{\"status\": \"error\", \"message\": \"Request timed out.\"}");
		} catch (Exception e) {
			throw new ServletException("WIFI 데이터를 검색하는 중 오류가 발생했습니다.", e);
		}
	}

	private void historyRegistraion(double lat, double lnt) {
		History history = new History(0, lat, lnt, DateTimeUtils.getCurrentTimestamp());
		DBServiceHistory dbService = new DBServiceHistory();
		dbService.insert(history);
	}

}