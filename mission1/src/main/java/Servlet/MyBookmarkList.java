package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBServiceMyBookmark;
import DTO.MyBookmark;

@WebServlet("/myBookmarkList")
public class MyBookmarkList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DBServiceMyBookmark dBServiceMyBookmark = new DBServiceMyBookmark();
		List<MyBookmark> myBookmarkList = dBServiceMyBookmark.select();

		request.setAttribute("myBookmarkList", myBookmarkList);
		request.getRequestDispatcher("myBookmarkList.jsp").forward(request, response);
	}
	
}
