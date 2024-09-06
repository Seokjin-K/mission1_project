package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBServiceHistory;
import DTO.History;

@WebServlet("/historyList")
public class HistoryList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DBServiceHistory dbService = new DBServiceHistory();
		List<History> historyList = dbService.select();

		request.setAttribute("historyList", historyList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("historyList.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteId = request.getParameter("deleteId");
        
        if (deleteId != null) {
        	DBServiceHistory dbService = new DBServiceHistory();
            dbService.delete(Long.parseLong(deleteId));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
