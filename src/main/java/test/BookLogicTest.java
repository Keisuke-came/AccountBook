package test;

import java.sql.Date;
import java.util.ArrayList;

import dao.TestDAO;
import model.Account;
import model.BookLogic;
import model.Item;
import model.Register;

public class BookLogicTest {
	public static void main(String[] args) {
		testSetItem();
		testFindAllItem();
		testRmoveItem();
		testUpdateItem();
		testSearchItem();
	}

	public static void testSetItem() {
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		Register register = new Register("Test1", 800, 0, 101);
		Item item = new Item(0, "Test1", 800, 0, date, "食費", "神田 圭佑");
		Account account = new Account("1", "1234", "神田 圭佑");
		BookLogic bo = new BookLogic();
		TestDAO dao = new TestDAO();

		boolean result = bo.executeSet(register, account);
		dao.removeItem(item);
		if(result) {
			System.out.println("testSetItem:成功しました");
		} else {
			System.out.println("testSetItem:失敗しました");
		}
	}

	public static void testFindAllItem() {
		Account account = new Account("1", "1234", "神田 圭佑");
		ArrayList<Item> itemList = new ArrayList<>();
		BookLogic bo = new BookLogic();

		itemList = bo.executeFind(account);
		if(itemList != null) {
			System.out.println("testFindAllItem:成功しました");
		} else {
			System.out.println("testFindAllItem:失敗しました");
		}
	}

	public static void testRmoveItem() {
		Register register = new Register("Test1", 800, 0, 101);
		Account account = new Account("1", "1234", "神田 圭佑");
		BookLogic bo = new BookLogic();
		TestDAO dao = new TestDAO();

		bo.executeSet(register, account);
		Item item = dao.findByItem(register);
		int itemId = item.getItemId();
		boolean result = bo.executeRemove(itemId);
		if(result) {
			System.out.println("testRmoveItem:成功しました");
		} else {
			System.out.println("testRmoveItem:失敗しました");
		}
	}

	public static void testUpdateItem() {
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		Register register = new Register("Test1", 800, 0, 101);
		Item item = new Item(0, "Test1", 800, 0, date, "食費", "神田 圭佑");
		Register register2 = new Register("Test2", 1200, 0, 102);
		Item item2 = new Item(0, "Test2", 1200, 0, date, "日用品", "神田 圭佑");
		Account account = new Account("1", "1234", "神田 圭佑");
		BookLogic bo = new BookLogic();
		TestDAO dao = new TestDAO();

		bo.executeSet(register, account);
		item = dao.findByItem(register);
		boolean result = bo.executeUpdate(register2, account, item.getItemId());
		item2 = dao.findByItem(register2);
		dao.removeItem(item2);
		if(result) {
			System.out.println("testSetItem:成功しました");
		} else {
			System.out.println("testSetItem:失敗しました");
		}
	}

	public static void testSearchItem() {
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		Register register = new Register("Test1", 800, 0, 101);
		Item item = new Item(0, "Test1", 800, 0, date, "食費", "神田 圭佑");
		Account account = new Account("1", "1234", "神田 圭佑");
		ArrayList<Item> itemList = new ArrayList<>();
		BookLogic bo = new BookLogic();
		TestDAO dao = new TestDAO();

		bo.executeSet(register, account);
		itemList = bo.executeSearch("Test");
		dao.removeItem(item);
		if(itemList != null) {
			System.out.println("testSearchItem:成功しました");
		} else {
			System.out.println("testSearchItem:失敗しました");
		}
	}
}
