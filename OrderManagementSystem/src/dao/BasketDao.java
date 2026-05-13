package dao;

import core.Database;
import entity.Basket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Sepet kayıtları için veri erişim sınıfı.
 * Basket tablosuna ürün ekleme, listeleme ve temizleme işlemlerini uygular.
 */
public class BasketDao {
    private final Connection connection;
    private final ProductDao productDao = new ProductDao();

    public BasketDao() {
        this.connection = Database.getInstance();
    }

    /**
     * Veritabanına yeni bir ürün sepete ekler.
     * @param productId Sepete eklenecek ürün ID'si
     * @return Başarılıysa true, hatada false
     */
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
     * Veritabanından tüm sepet öğelerini getirir.
     * @return Sepet içindeki Basket kayıtlarının listesi
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
     * Sepetteki tüm ürünleri veritabanından siler.
     * @return Silme işlemi başarılıysa true
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

    /**
     * ResultSet satırını Basket nesnesine dönüştürür.
     * @param rs Veritabanı sorgusunun ResultSet sonucu
     * @return Dönüştürülmüş Basket nesnesi
     * @throws SQLException SQL sorgusu sırasında hata oluşursa
     */
    public Basket match(ResultSet rs) throws SQLException {
        Basket basket = new Basket();
        basket.setId(rs.getInt("id"));
        basket.setProductId(rs.getInt("product_id"));
        basket.setProduct(this.productDao.getById(rs.getInt("product_id"))); 
        return basket;
    }

}
