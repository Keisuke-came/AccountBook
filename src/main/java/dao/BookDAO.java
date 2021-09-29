package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Account;
import model.Item;
import model.Register;

public class BookDAO {
	private final String JDBC_URL = "jdbc:postgresql://localhost/accountbook";
	private final String DB_USER = "kandakeisuke";
	private final String DB_PASS = "";

	public int setItem(Register register, Account account) {
		int rs = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			String sql = "INSERT INTO BOOK (ITEM, PAYMENT, INCOME, DATE, CATEGORY_ID, ACCOUNT_ID) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, register.getItemName());
			pStmt.setInt(2, register.getPayment());
			pStmt.setInt(3, register.getIncome());
			pStmt.setDate(4, date);
			pStmt.setInt(5, register.getCategoryId());
			pStmt.setString(6, account.getUserId());

			rs = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ArrayList<Item> findAllItem(Account account) {
		Item item = null;
		ArrayList<Item> itemList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT ITEM_ID, ITEM, PAYMENT, INCOME, DATE, NAME AS CATEGORY FROM BOOK JOIN CATEGORY ON "
					+ "BOOK.CATEGORY_ID = CATEGORY.ID WHERE ACCOUNT_ID = ? ORDER BY DATE DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int itemId = rs.getInt("ITEM_ID");
				String itemName = rs.getString("ITEM");
				int payment = rs.getInt("PAYMENT");
				int income = rs.getInt("INCOME");
				Date date = rs.getDate("DATE");
				String category = rs.getString("CATEGORY");
				String userName = account.getName();
				item = new Item(itemId, itemName, payment, income, date, category, userName);
				itemList.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return itemList;
	}

	public int updateItem(Register register, Account account, int itemId) {
		int rs = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			String sql = "UPDATE BOOK SET ITEM = ?, PAYMENT = ?, INCOME = ?, DATE = ?, CATEGORY_ID = ?, ACCOUNT_ID = ? "
					+ "WHERE ITEM_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, register.getItemName());
			pStmt.setInt(2, register.getPayment());
			pStmt.setInt(3, register.getIncome());
			pStmt.setDate(4, date);
			pStmt.setInt(5, register.getCategoryId());
			pStmt.setString(6, account.getUserId());
			pStmt.setInt(7, itemId);

			rs = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int removeItem(int itemId) {
		int rs = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "DELETE FROM BOOK WHERE ITEM_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, itemId);

			rs = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ArrayList<Item> searchItems(String letter) {
		Item item = null;
		ArrayList<Item> itemList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT ITEM_ID, ITEM, PAYMENT, INCOME, DATE, CATEGORY.NAME AS CATEGORY, ACCOUNT.NAME AS ACCOUNT"
					+ " FROM BOOK JOIN CATEGORY ON BOOK.CATEGORY_ID = CATEGORY.ID JOIN ACCOUNT ON "
					+ "BOOK.ACCOUNT_ID = ACCOUNT.USER_ID WHERE ITEM LIKE '%" + letter + "%' ORDER BY DATE DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int itemId = rs.getInt("ITEM_ID");
				String itemName = rs.getString("ITEM");
				int payment = rs.getInt("PAYMENT");
				int income = rs.getInt("INCOME");
				Date date = rs.getDate("DATE");
				String category = rs.getString("CATEGORY");
				String userName = rs.getString("ACCOUNT");
				item = new Item(itemId, itemName, payment, income, date, category, userName);
				itemList.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return itemList;
	}
}
