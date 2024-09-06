package Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DTO.Wifi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WifiAPIUtils {

	private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();

	private static final String API_URL = "http://openapi.seoul.go.kr:8088";
	private static final String API_KEY = "6857484361776c7338306b46466f6c";

	public static OkHttpClient getOkHttpClientInstance() {
		return client;
	}

	public static JsonArray searchWifiAPI(final int MAX_NUMBER_OF_WIFI_TO_SEARCH, final int MAX_DISTANCE_TO_SEARCH,
			final double LAT, final double LNT) throws Exception {

		JsonArray wifiData = getWifiData(MAX_NUMBER_OF_WIFI_TO_SEARCH);
		return filterByLocation(wifiData, MAX_DISTANCE_TO_SEARCH, LAT, LNT);
	}
	
	public static void writeJsonResponse(HttpServletResponse response, JsonArray jsonData) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonData.toString());
		out.flush();
	}

	public static JsonArray getWifiData(final int MAX_NUMBER_OF_WIFI_TO_SEARCH) throws Exception {

		String url = String.format("%s/%s/json/TbPublicWifiInfo/1/" + MAX_NUMBER_OF_WIFI_TO_SEARCH,
				WifiAPIUtils.API_URL, WifiAPIUtils.API_KEY);
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code " + response);
			}
			String jsonResponse = response.body().string();
			JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
			return jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
		}
	}

	private static JsonArray filterByLocation(JsonArray wifiArray, final int MAX_DISTANCE_TO_SEARCH, final double LAT,
			final double LNT) {
		JsonArray resultArray = new JsonArray();

		for (JsonElement element : wifiArray) {
			JsonObject wifi = element.getAsJsonObject();
			double wifiLat = wifi.get("LAT").getAsDouble();
			double wifiLon = wifi.get("LNT").getAsDouble();
			double distance = calculateDistance(LAT, LNT, wifiLat, wifiLon);

			if (distance <= MAX_DISTANCE_TO_SEARCH) { // MAX_DISTANCE_TO_SEARCH 이내로 검색
				JsonObject wifiInfo = extractWifiInfo(wifi, distance);
				resultArray.add(wifiInfo);
			}
		}
		return resultArray;
	}

	private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double radius = 6371; // 지구 반경 (km)
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return radius * c;
	}

	private static JsonObject extractWifiInfo(JsonObject wifi, double distance) {
		JsonObject wifiInfo = new JsonObject();

		wifiInfo.addProperty("distance", String.format("%.4f", distance));
		wifiInfo.addProperty("mgrNo", wifi.get("X_SWIFI_MGR_NO").getAsString());
		wifiInfo.addProperty("borough", wifi.get("X_SWIFI_WRDOFC").getAsString());
		wifiInfo.addProperty("ssid", wifi.get("X_SWIFI_MAIN_NM").getAsString());
		wifiInfo.addProperty("address", wifi.get("X_SWIFI_ADRES1").getAsString());
		wifiInfo.addProperty("detailAddress", wifi.get("X_SWIFI_ADRES2").getAsString());
		wifiInfo.addProperty("installFloor", wifi.get("X_SWIFI_INSTL_FLOOR").getAsString());
		wifiInfo.addProperty("installType", wifi.get("X_SWIFI_INSTL_TY").getAsString());
		wifiInfo.addProperty("installAgency", wifi.get("X_SWIFI_INSTL_MBY").getAsString());
		wifiInfo.addProperty("serviceType", wifi.get("X_SWIFI_SVC_SE").getAsString());
		wifiInfo.addProperty("networkType", wifi.get("X_SWIFI_CMCWR").getAsString());
		wifiInfo.addProperty("installYear", wifi.get("X_SWIFI_CNSTC_YEAR").getAsString());
		wifiInfo.addProperty("indoorOutdoor", wifi.get("X_SWIFI_INOUT_DOOR").getAsString());
		wifiInfo.addProperty("connectionEnv", wifi.get("X_SWIFI_REMARS3").getAsString());
		wifiInfo.addProperty("xCoord", wifi.get("LAT").getAsString());
		wifiInfo.addProperty("yCoord", wifi.get("LNT").getAsString());
		wifiInfo.addProperty("workDate", wifi.get("WORK_DTTM").getAsString());

		return wifiInfo;
	}
	
	public static List<Wifi> convertToWifiList(JsonArray wifiData) {
        List<Wifi> wifiList = new ArrayList<>();

        for (JsonElement element : wifiData) {
            JsonObject wifiJson = element.getAsJsonObject();
            Wifi wifi = new Wifi();

            wifi.setMgrNum(wifiJson.get("X_SWIFI_MGR_NO").getAsString());
            wifi.setSsid(wifiJson.get("X_SWIFI_MAIN_NM").getAsString());
            wifi.setAddress(wifiJson.get("X_SWIFI_ADRES1").getAsString());
            wifi.setDetailAddress(wifiJson.get("X_SWIFI_ADRES2").getAsString());
            wifi.setInstallFloor(wifiJson.get("X_SWIFI_INSTL_FLOOR").getAsString());
            wifi.setInstallType(wifiJson.get("X_SWIFI_INSTL_TY").getAsString());
            wifi.setInstallAgency(wifiJson.get("X_SWIFI_INSTL_MBY").getAsString());
            wifi.setServicteType(wifiJson.get("X_SWIFI_SVC_SE").getAsString());
            wifi.setNetworkType(wifiJson.get("X_SWIFI_CMCWR").getAsString());
            wifi.setInstallYear(wifiJson.get("X_SWIFI_CNSTC_YEAR").getAsInt());
            wifi.setIndoorOutdoor(wifiJson.get("X_SWIFI_INOUT_DOOR").getAsString());
            wifi.setConnectionEnv(wifiJson.get("X_SWIFI_REMARS3").getAsString());
            wifi.setxCoord(wifiJson.get("LAT").getAsDouble());
            wifi.setyCoord(wifiJson.get("LNT").getAsDouble());
            wifi.setWordDate(Timestamp.valueOf(wifiJson.get("WORK_DTTM").getAsString()));

            wifiList.add(wifi);
        }

        return wifiList;
    }

}
