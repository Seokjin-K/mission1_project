package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBServiceBookmark;
import DTO.Bookmark;
import Utils.DateTimeUtils;

@WebServlet("/bookmarkModify")
public class BookmarkModify extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errorMessage = null;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {
			updateBookmark(request);
		} catch (SQLIntegrityConstraintViolationException e) {
			errorMessage = "순서는 중복될 수 없습니다.";
		} catch (SQLException e) {
			errorMessage = "데이터베이스 에러";
		}

		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (errorMessage != null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(errorMessage);
		} else {
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	private int updateBookmark(HttpServletRequest request) throws SQLException {
		Bookmark bookmark = new Bookmark(Long.parseLong(request.getParameter("bookmarkId")),
				request.getParameter("bookmarkName"), Long.parseLong(request.getParameter("sequence")), null,
				DateTimeUtils.getCurrentTimestamp());

		DBServiceBookmark dbServiceBookmark = new DBServiceBookmark();
		return dbServiceBookmark.update(bookmark);
	}

}
