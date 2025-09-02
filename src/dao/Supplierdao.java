package dao;
import db.dbconnection;
import model.Supplier;
import java.sql.*;
import java.util.HashMap;
public class Supplierdao {
    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (name, contact, email, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbconnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getContact());
            preparedStatement.setString(3, supplier.getEmail());
            preparedStatement.setString(4, supplier.getAddress());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public HashMap<Integer, Supplier> getAllSuppliers() {
        HashMap<Integer, Supplier> supplierMap = new HashMap<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection connection = dbconnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Supplier s = new Supplier(
                        rs.getInt("supplierID"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                supplierMap.put(s.getSupplierID(), s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierMap;
    }
}
