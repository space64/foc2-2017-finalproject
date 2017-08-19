package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDA extends WHConnection{

	public void getAllProducts() {
		String sql = "SELECT * FROM products";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.format("%3s %-40s %7.2f\n",
						rs.getString("productcode"),
						rs.getString("productname"),
						rs.getDouble("unitprice"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insert(String pCode, String pName, int categoryid, int brandid, int unitOfMeasure, double unitPrice, String description) {
		String sql = "INSERT INTO products(productcode, productname, categoryid, brandid, unitofmeasureid, unitprice, description)"+
					" VALUES(?,?,?,?,?,?,?)";

		try (Connection conn = connect(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pCode);
			pstmt.setString(2, pName);
			pstmt.setInt(3, categoryid);
			pstmt.setInt(4, brandid);
			pstmt.setInt(5, unitOfMeasure);
			pstmt.setDouble(6,  unitPrice);
			pstmt.setString(7, description);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
