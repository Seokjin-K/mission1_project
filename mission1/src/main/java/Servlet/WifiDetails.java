package Servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DAO.DBServiceBookmark;

@WebServlet("/wifiDetails")
public class WifiDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String wifiJson = request.getParameter("wifi");

		if (wifiJson == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "WiFi 정보가 전달되지 않았습니다.");
			return;
		}

		String decodedWifiJson = URLDecoder.decode(wifiJson, "UTF-8"); // URL 디코딩
		JsonObject wifiDetails = JsonParser.parseString(decodedWifiJson).getAsJsonObject(); // JSON 문자열을 JsonObject로 변환

		setWifiDetailsAttributes(request, wifiDetails);
		request.setAttribute("bookmarkList", new DBServiceBookmark().select());
		request.getRequestDispatcher("/wifiDetails.jsp").forward(request, response);
	}

	private void setWifiDetailsAttributes(HttpServletRequest request, JsonObject wifiDetails) {
		request.setAttribute("distance", wifiDetails.get("distance").getAsString());
		request.setAttribute("mgrNo", wifiDetails.get("mgrNo").getAsString());
		request.setAttribute("borough", wifiDetails.get("borough").getAsString());
		request.setAttribute("ssid", wifiDetails.get("ssid").getAsString());
		request.setAttribute("address", wifiDetails.get("address").getAsString());
		request.setAttribute("detailAddress", wifiDetails.get("detailAddress").getAsString());
		request.setAttribute("installFloor", wifiDetails.get("installFloor").getAsString());
		request.setAttribute("installType", wifiDetails.get("installType").getAsString());
		request.setAttribute("installAgency", wifiDetails.get("installAgency").getAsString());
		request.setAttribute("serviceType", wifiDetails.get("serviceType").getAsString());
		request.setAttribute("networkType", wifiDetails.get("networkType").getAsString());
		request.setAttribute("installYear", wifiDetails.get("installYear").getAsString());
		request.setAttribute("indoorOutdoor", wifiDetails.get("indoorOutdoor").getAsString());
		request.setAttribute("connectionEnv", wifiDetails.get("connectionEnv").getAsString());
		request.setAttribute("xCoord", wifiDetails.get("xCoord").getAsString());
		request.setAttribute("yCoord", wifiDetails.get("yCoord").getAsString());
		request.setAttribute("workDate", wifiDetails.get("workDate").getAsString());
	}
	
}
