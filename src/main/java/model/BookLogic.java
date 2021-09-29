package model;

import java.util.ArrayList;

import dao.BookDAO;

public class BookLogic {
	public boolean executeSet(Register register, Account account) {
		BookDAO dao = new BookDAO();
		int rs = dao.setItem(register, account);
		return rs != 0;
	}

	public ArrayList<Item> executeFind(Account account) {
		BookDAO dao = new BookDAO();
		ArrayList<Item> itemList = dao.findAllItem(account);
		return itemList;
	}

	public boolean executeUpdate(Register register, Account account, int itemId) {
		BookDAO dao = new BookDAO();
		int rs = dao.updateItem(register, account, itemId);
		return rs != 0;
	}

	public boolean executeRemove(int itemId) {
		BookDAO dao = new BookDAO();
		int rs = dao.removeItem(itemId);
		return rs != 0;
	}

	public ArrayList<Item> executeSearch(String letter) {
		BookDAO dao = new BookDAO();
		ArrayList<Item> itemList = dao.searchItems(letter);
		return itemList;
	}
}
