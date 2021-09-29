package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
	private final String JDBC_URL = "jdbc:postgresql://ec2-100-24-169-249.compute-1.amazonaws.com:5432/accountbook";
	private final String DB_USER = "mkwkvavswmgzhf";
	private final String DB_PASS = "a7205fd9a18aa26f2615424d2f0e889db6e8f6cb361abbee141dc3d3314abab0";

	public Account findByLogin(Login login) {
		Account account = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT USER_ID, PASS, NAME FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String name = rs.getString("NAME");
				account = new Account(userId, pass, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
}
