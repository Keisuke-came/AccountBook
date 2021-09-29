package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account loginUser = (Account) session.getAttribute("account");
		ArrayList<Item> itemList = new ArrayList<>();

		String fowardPath = null;
		String action = request.getParameter("action");

		if(action == null) {
			if(loginUser == null) {
				response.sendRedirect("login.jsp");
			} else {
				BookLogic bo = new BookLogic();
				itemList = bo.executeFind(loginUser);
				request.setAttribute("itemList", itemList);
				fowardPath = "main.jsp";
			}
		}

		else if(action.equals("done")) {
			fowardPath = "register.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String fowardPath = null;
		String action = request.getParameter("action");

		if(action.equals("delete")) {
			BookLogic bo = new BookLogic();
			String param = request.getParameter("itemId");
			int itemId = Integer.parseInt(param);
			boolean result = bo.executeRemove(itemId);
			if(result) {
				fowardPath = "deleteOK.jsp";
			} else {
				response.sendRedirect("MainServlet");
			}
		}

		else if(action.equals("update")) {
			String param1 = request.getParameter("itemId");
			int itemId = Integer.parseInt(param1);
			String itemName = request.getParameter("itemName");
			String param2 = request.getParameter("payment");
			int payment = Integer.parseInt(param2);
			String param3 = request.getParameter("income");
			int income = Integer.parseInt(param3);
			String param4 = request.getParameter("date");
			Date date = null;
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				date = sdFormat.parse(param4);
			} catch(Exception e) {
				e.printStackTrace();
			}
			String category = request.getParameter("category");
			String userName = request.getParameter("userName");
			Item item = new Item(itemId, itemName, payment, income, date, category, userName);

			HttpSession session = request.getSession();
			session.setAttribute("item", item);
			fowardPath = "update.jsp";
		}


		else if(action.equals("search")) {
			String search = request.getParameter("search");
			BookLogic bo = new BookLogic();
			ArrayList<Item> itemList = new ArrayList<>();
			itemList = bo.executeSearch(search);
			if(itemList != null) {
				request.setAttribute("itemList", itemList);
				fowardPath = "main.jsp";
			} else {
				response.sendRedirect("MainServlet");
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardPath);
		dispatcher.forward(request, response);

	}

}
