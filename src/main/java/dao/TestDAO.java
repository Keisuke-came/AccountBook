package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Item;
import model.Register;

public class TestDAO {
	private final String JDBC_URL = "jdbc:postgresql://ec2-100-24-169-249.compute-1.amazonaws.com:5432/accountbook";
	private final String DB_USER = "mkwkvavswmgzhf";
	private final String DB_PASS = "a7205fd9a18aa26f2615424d2f0e889db6e8f6cb361abbee141dc3d3314abab0";

	public void removeItem(Item item) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "DELETE FROM BOOK WHERE ITEM = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getItemName());

			int rs = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Item findByItem(Register register) {
		Item item = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT ITEM_ID, ITEM, PAYMENT, INCOME, DATE, CATEGORY.NAME AS CATEGORY, ACCOUNT.NAME AS ACCOUNT"
					+ " FROM BOOK JOIN CATEGORY ON BOOK.CATEGORY_ID = CATEGORY.ID JOIN ACCOUNT ON "
					+ "BOOK.ACCOUNT_ID = ACCOUNT.USER_ID WHERE ITEM = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, register.getItemName());

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int itemId = rs.getInt("ITEM_ID");
				String itemName = rs.getString("ITEM");
				int payment = rs.getInt("PAYMENT");
				int income = rs.getInt("INCOME");
				Date date = rs.getDate("DATE");
				String category = rs.getString("CATEGORY");
				String userName = rs.getString("ACCOUNT");
				item = new Item(itemId, itemName, payment, income, date, category, userName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return item;
	}
}
