package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dataobject.Brand;
import dataobject.Category;

public class BrandDA extends WHConnection{
	
	public Vector<Brand> getBrands() {
		String sql = "SELECT * FROM brand";
		Vector<Brand> brandList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				Brand brand = new Brand(rs.getInt("id"),
						rs.getString("brandname"));
				brandList.add(brand);
			}
			return brandList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return brandList;
	}
		
}
