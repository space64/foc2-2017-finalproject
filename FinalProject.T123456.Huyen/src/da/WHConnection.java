package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WHConnection {
	protected Connection connect() {
		String url = "jdbc:sqlite:foc2warehouse.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
