package test;

import dao.AccountDAO;
import model.Account;
import model.Login;

public class AccountDAOTest {
	public static void main(String[] args) {
		testFindByLogin1();
		testFindByLogin2();
	}
	public static void testFindByLogin1() {
		Login login = new Login("1", "1234");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result != null &&
				result.getUserId().equals("1") &&
				result.getPass().equals("1234") &&
				result.getName().equals("神田 圭佑")) {
			System.out.println("testFindByLogin1:成功しました");
		} else {
			System.out.println("testFindByLogin1:失敗しました");
		}
	}
	public static void testFindByLogin2() {
		Login login = new Login("keisuke", "12345");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result == null) {
			System.out.println("testFindByLogin2:成功しました");
		} else {
			System.out.println("testFindByLogin2:失敗しました");
		}
	}
}
