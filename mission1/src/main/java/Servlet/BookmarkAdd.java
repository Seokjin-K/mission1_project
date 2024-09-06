package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBServiceBookmark;
import DTO.Bookmark;
import Utils.DateTimeUtils;

@WebServlet("/bookmarkAdd")
public class BookmarkAdd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String errorMessage = null;

		try {
			addBookmark(request);
		} catch (SQLIntegrityConstraintViolationException e) {
			errorMessage = "이름 또는 순서가 중복될 수 없습니다.";
		} catch (SQLException e) {
			errorMessage = "데이터베이스 오류가 발생했습니다.";
		}

		if (errorMessage != null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(errorMessage);
		} else {
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	private int addBookmark(HttpServletRequest request) throws SQLException {
		Bookmark bookmark = new Bookmark(0, request.getParameter("bookmarkName"),
				Long.parseLong(request.getParameter("sequence")), DateTimeUtils.getCurrentTimestamp(), null);

		DBServiceBookmark dbServiceBookmark = new DBServiceBookmark();
		return dbServiceBookmark.insert(bookmark);
	}

}
