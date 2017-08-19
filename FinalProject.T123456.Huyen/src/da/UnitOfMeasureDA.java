package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dataobject.Brand;
import dataobject.UnitOfMeasure;

public class UnitOfMeasureDA extends WHConnection {
	
	public Vector<UnitOfMeasure> getUnitOfMeasure() {
		String sql = "SELECT * FROM unitofmeasure";
		Vector<UnitOfMeasure> unitList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				UnitOfMeasure unit = new UnitOfMeasure(rs.getInt("id"),
						rs.getString("unitname"));
				unitList.add(unit);
			}
			return unitList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return unitList;
	}
}
