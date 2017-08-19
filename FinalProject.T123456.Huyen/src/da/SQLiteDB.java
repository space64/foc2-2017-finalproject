package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataobject.Category;

public class SQLiteDB {
	private Connection connect() {
		String url = "jdbc:sqlite:foc2warehouse.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void getAllProducts() {
		String sql = "SELECT * FROM products";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.format("%3d %-40s %7.2f\n",
						rs.getInt("productcode"),
						rs.getString("productname"),
						rs.getDouble("unitprice"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet getProducts() {
		String sql = "SELECT * FROM products";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			return rs;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getProductModel(){
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
	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		if(rs == null)
			return null;
		ResultSetMetaData metaData = rs.getMetaData();

		// tên cột - trường dữ liệu
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// các dòng dữ liệu, gồm nhiều dòng, mỗi dòng gồm nhiều trường (kiểu object)
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> row = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				row.add(rs.getObject(columnIndex));
			}
			data.add(row);
		}

		return new DefaultTableModel(data, columnNames);
	}
	
	public Vector<Category> getCategories() {
		String sql = "SELECT * FROM categories";
		Vector<Category> categoryList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				Category cat = new Category(rs.getInt("categoryid"),
						rs.getString("categoryname"));
				categoryList.add(cat);
			}
			return categoryList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return categoryList;
	}
	
	public void insert(String pCode, String pName, int categoryid, int brandid, int unitOfMeasure, double unitPrice, String description) {
		String sql = "INSERT INTO products(productcode, productname, categoryid, brandid, unitofmeasure, unitprice, description)"+
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
