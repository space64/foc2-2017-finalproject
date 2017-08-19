package da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import dataobject.Brand;
import dataobject.Category;
import dataobject.Product;
import dataobject.UnitOfMeasure;

public class ProductDA extends WHConnection {

	public void getAllProducts() {
		String sql = "SELECT * FROM products";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.format("%3s %-40s %7.2f\n", rs.getString("productcode"), rs.getString("productname"),
						rs.getDouble("unitprice"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Product getProduct(int id) {
		String sql = "SELECT p.id, p.productcode, p.productname, p.unitprice, p.description, p.categoryid, c.categoryname,"
				+ " p.brandid, b.brandname, p.unitofmeasureid, u.unitname "
				+ "FROM products p,categories c, brand b, unitofmeasure u "
				+ "WHERE p.categoryid = c.id AND p.brandid = b.id AND p.unitofmeasureid = u.id AND p.id = " + id;

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			if (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductCode(rs.getString("productcode"));
				product.setProductName(rs.getString("productname"));
				product.setUnitPrice(rs.getDouble("unitprice"));
				Category cat = new Category();
				cat.setCategoryId(rs.getInt("categoryid"));
				cat.setCategoryName(rs.getString("categoryname"));
				product.setCategory(cat);
				Brand brand = new Brand();
				brand.setId(rs.getInt("brandid"));
				brand.setName(rs.getString("brandname"));
				product.setBrand(brand);
				UnitOfMeasure unit = new UnitOfMeasure();
				unit.setId(rs.getInt("unitofmeasureid"));
				unit.setName(rs.getString("unitname"));
				product.setUnitOfMeasure(unit);
				return product;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public DefaultTableModel getProducts() {
		String sql = "SELECT * FROM products";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			return buildTableModel(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void insert(String pCode, String pName, int categoryid, int brandid, int unitOfMeasure, double unitPrice,
			String description) {
		String sql = "INSERT INTO products(productcode, productname, categoryid, brandid, unitofmeasureid, unitprice, description)"
				+ " VALUES(?,?,?,?,?,?,?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pCode);
			pstmt.setString(2, pName);
			pstmt.setInt(3, categoryid);
			pstmt.setInt(4, brandid);
			pstmt.setInt(5, unitOfMeasure);
			pstmt.setDouble(6, unitPrice);
			pstmt.setString(7, description);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
