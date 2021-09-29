package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.BookLogic;
import model.Item;
import model.Register;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String fowardPath = null;
		String action = request.getParameter("action");

		if(action == null) {
			String itemName = request.getParameter("itemName");
			String param1 = request.getParameter("payment");
			int payment = Integer.parseInt(param1);
			String param2 = request.getParameter("income");
			int income = Integer.parseInt(param2);
			String param3 = request.getParameter("category");
			int categoryId = Integer.parseInt(param3);
			Register register = new Register(itemName, payment, income, categoryId);

			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");

			BookLogic bo = new BookLogic();
			boolean result = bo.executeSet(register, account);

			if(result) {
				fowardPath = "WEB-INF/jsp/itemOK.jsp";
			} else {
				response.sendRedirect("/accountBook2/MainServlet");
			}
		}

		else if(action.equals("done")) {
			String itemName = request.getParameter("itemName");
			String param1 = request.getParameter("payment");
			int payment = Integer.parseInt(param1);
			String param2 = request.getParameter("income");
			int income = Integer.parseInt(param2);
			String param3 = request.getParameter("category");
			int categoryId = Integer.parseInt(param3);
			Register register = new Register(itemName, payment, income, categoryId);

			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");

			Item item = (Item) session.getAttribute("item");
			int itemId = item.getItemId();

			BookLogic bo = new BookLogic();
			boolean result = bo.executeUpdate(register, account, itemId);

			session.removeAttribute("item");

			if(result) {
				fowardPath = "WEB-INF/jsp/updateOK.jsp";
			} else {
				response.sendRedirect("/accountBook2/MainServlet");
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardPath);
		dispatcher.forward(request, response);
	}

}
