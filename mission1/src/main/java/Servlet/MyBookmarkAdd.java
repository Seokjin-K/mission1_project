package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBServiceMyBookmark;
import DTO.MyBookmark;
import Utils.DateTimeUtils;

@WebServlet("/myBookmarkAdd")
public class MyBookmarkAdd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String bookmarkName = request.getParameter("bookmarkName");
		String ssid = request.getParameter("ssid");

		if (isNullOrEmpty(bookmarkName)) {
			setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "북마크 그룹을 선택하세요.");
			return;
		}

		MyBookmark myBookmark = new MyBookmark(0, bookmarkName, ssid, DateTimeUtils.getCurrentTimestamp());
		DBServiceMyBookmark dbServiceMyBookmark = new DBServiceMyBookmark();

		try {
			dbServiceMyBookmark.insert(myBookmark);
			setResponse(response, HttpServletResponse.SC_OK, "북마크 정보를 추가하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터베이스 에러 발생");
		}
	}

	private boolean isNullOrEmpty(String value) {
		return value == null || value.isEmpty();
	}

	private void setResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
		response.setStatus(statusCode);
		response.getWriter().write(message);
	}
}
