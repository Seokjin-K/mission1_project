package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBServiceBookmark;
import DAO.DBServiceMyBookmark;

@WebServlet("/bookmarkGroupDelete")
public class BookmarkGroupDelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		DBServiceBookmark dbServiceBookmark = new DBServiceBookmark();

		try {
			dbServiceBookmark.delete(Long.parseLong(request.getParameter("bookmarkId")));
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			System.out.println("여기");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
