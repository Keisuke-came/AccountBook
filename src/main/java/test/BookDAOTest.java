package test;

import java.sql.Date;
import java.util.ArrayList;

import dao.BookDAO;
import dao.TestDAO;
import model.Account;
import model.Item;
import model.Register;

public class BookDAOTest {
	public static void main(String[] args) {
		testSetFindAllItem1();
		testSetFindAllItem2();
		testRemoveItem();
		testUpdateItem();
		testSearchItem();
	}

	public static void testSetFindAllItem1() {
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		Register register = new Register("Test1", 800, 0, 101);
		Item item = new Item(0, "Test1", 800, 0, date, "食費", "神田 圭佑");
		Account account = new Account("1", "1234", "神田 圭佑");
		ArrayList<Item> itemList = new ArrayList<>();
		BookDAO dao1 = new BookDAO();
		TestDAO dao2 = new TestDAO();

		dao1.setItem(register, account);
		itemList = dao1.findAllItem(account);
		dao2.removeItem(item);
		Item rsItem = itemList.get(0);
		boolean result = rsItem.getItemName().equals(item.getItemName());
		if(itemList != null && result) {
			System.out.println("testSetFindAllItem1:成功しました");
		} else {
			System.out.println("testSetFindAllItem1:失敗しました");
		}
	}

	public static void testSetFindAllItem2() {
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		Register register = new Register("Test1", 800, 0, 101);
		Item item1 = new Item(0, "Test1", 800, 0, date, "食費", "神田 圭佑");
		Item item2 = new Item(1, "Test2", 800, 0, date, "Test2", "Test2");
		Account account = new Account("1", "1234", "神田 圭佑");
		ArrayList<Item> itemList = new ArrayList<>();
		BookDAO dao1 = new BookDAO();
		TestDAO dao2 = new TestDAO();

		dao1.setItem(register, account);
		itemList = dao1.findAllItem(account);
		dao2.removeItem(item1);
		boolean result = itemList.contains(item2);
		if(result == false) {
			System.out.println("testSetFindAllItem2:成功しました");
		} else {
			System.out.println("testSetFindAllItem2:失敗しました");
		}
	}

	public static void testRemoveItem() {
		Register register = new Register("Test1", 800, 0, 101);
		Account account = new Account("1", "1234", "神田 圭佑");
		BookDAO dao1 = new BookDAO();
		TestDAO dao2 = new TestDAO();

		dao1.setItem(register, account);
		Item item = dao2.findByItem(register);
		int itemId = item.getItemId();
		int result = dao1.removeItem(itemId);
		if(result != 0) {
			System.out.println("testRemoveItem:成功しました");
		} else {
			System.out.println("testRemoveItem:失敗しました");
		}
	}

	public static void testUpdateItem() {
		Register register = new Register("Test1", 800, 0, 101);
		Register register2 = new Register("Test2", 1200, 0, 102);
		Account account = new Account("1", "1234", "神田 圭佑");
		BookDAO dao1 = new BookDAO();
		TestDAO dao2 = new TestDAO();

		dao1.setItem(register, account);
		Item item = dao2.findByItem(register);
		int rs = dao1.updateItem(register2, account, item.getItemId());
		dao2.removeItem(item);
		if(rs != 0) {
			System.out.println("testSetFindAllItem1:成功しました");
		} else {
			System.out.println("testSetFindAllItem1:失敗しました");
		}
	}

	public static void testSearchItem() {
		long d = System.currentTimeMillis();
		Date date = new Date(d);
		Register register = new Register("Test1", 800, 0, 101);
		Item item = new Item(0, "Test1", 800, 0, date, "食費", "神田 圭佑");
		Account account = new Account("1", "1234", "神田 圭佑");
		ArrayList<Item> itemList = new ArrayList<>();
		BookDAO dao1 = new BookDAO();
		TestDAO dao2 = new TestDAO();

		dao1.setItem(register, account);
		itemList = dao1.searchItems("Test");
		dao2.removeItem(item);
		if(itemList != null) {
			System.out.println("testSearchItem:成功しました");
		} else {
			System.out.println("testSearchItem:失敗しました");
		}
	}
}
