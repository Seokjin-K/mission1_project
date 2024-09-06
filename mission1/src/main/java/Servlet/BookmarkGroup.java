package Servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DAO.DBServiceBookmark;
import DTO.Bookmark;

@WebServlet("/bookmarkGroup")
public class BookmarkGroup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DBServiceBookmark dbServiceBookmark = new DBServiceBookmark();
		List<Bookmark> bookmarkList = dbServiceBookmark.select();
		
		request.setAttribute("bookmarkList", bookmarkList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookmarkGroup.jsp");
		dispatcher.forward(request, response);
	}

}

