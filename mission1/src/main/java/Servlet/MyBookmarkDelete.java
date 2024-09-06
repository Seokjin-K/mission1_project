package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import DAO.DBServiceMyBookmark;

@WebServlet("/myBookmarkDelete")
public class MyBookmarkDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		DBServiceMyBookmark dbServiceMyBookmark = new DBServiceMyBookmark();

		try {
			dbServiceMyBookmark.delete(Long.parseLong(request.getParameter("myBookmarkId")));
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
