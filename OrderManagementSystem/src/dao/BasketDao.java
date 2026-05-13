package dao;

import core.Database;
import entity.Basket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BasketDao {
    private final Connection connection;
    private final ProductDao productDao = new ProductDao();

    public BasketDao() {
        this.connection = Database.getInstance();
    }

    public boolean save(int productId) {
        String query = "INSERT INTO basket (product_id) VALUES (?)";

        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, productId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Retrieves all basket items from the database.
     * @return ArrayList of Basket objects containing all basket entries
     */
    public ArrayList<Basket> findAll() {
        String query = "SELECT * FROM basket";
        ArrayList<Basket> baskets = new ArrayList<>();

        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                baskets.add(match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baskets;
    }

    /**
     * Saves a Basket object to the database by delegating to save(int productId).
     * @param basket The Basket object to save
     * @return true if save was successful, false otherwise
     */
    public boolean save(Basket basket) {
        if (basket == null) {
            return false;
        }
        return save(basket.getProductId());
    }

    /**
     * Clears all items from the basket by deleting all records from the database.
     * @return true if the operation was successful, false otherwise
     */
    public boolean clear() {
        String query = "DELETE FROM basket";

        try {
            PreparedStatement pr = this.connection.prepareStatement(query); 
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Basket match(ResultSet rs) throws SQLException {
        Basket basket = new Basket();
        basket.setId(rs.getInt("id"));
        basket.setProductId(rs.getInt("product_id"));
        basket.setProduct(this.productDao.getById(rs.getInt("product_id"))); 
        return basket;
    }

}
