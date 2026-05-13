package business;

import core.Helper;
import core.Item;
import dao.ProductDao;
import entity.Product;
import java.util.ArrayList;

/**
 * Ürün işlemlerini yöneten kontrol sınıfı
 * DAO katmanı ile iletişim kurarak ürün CRUD işlemlerini gerçekleştirir
 */
public class ProductController {
    private final ProductDao productDao = new ProductDao();

    /**
     * Tüm ürünleri getirir
     * @return Ürün listesi
     */
    public ArrayList<Product> findAll(){
        return this.productDao.findAll();
    }

    /**
     * ID'ye göre ürünü getirir
     * @param id Ürün ID'si
     * @return Ürün nesnesi
     */
    public Product getById(int id){
        return this.productDao.getById(id);
    }

    /**
     * Yeni ürünü kaydeder
     * @param product Kaydedilecek ürün
     * @return İşlem başarılı ise true
     */
    public boolean save(Product product){
        return this.productDao.save(product);
    }

    /**
     * Var olan ürünü günceller
     * @param product Güncellenecek ürün
     * @return İşlem başarılı ise true
     */
    public boolean update(Product product){
        if (this.productDao.getById(product.getId()) == null){
            Helper.showMessage(product.getId() + " ID'li ürün bulunamadı!");
            return false;
        }
        return this.productDao.update(product);
    }

    /**
     * Ürünü siler
     * @param id Silinecek ürünün ID'si
     * @return İşlem başarılı ise true
     */
    public boolean delete(int id){
        if (this.productDao.getById(id) == null){
            Helper.showMessage(id + " ID'li ürün bulunamadı!");
            return false;
        }
        return this.productDao.delete(id);
    }

    /**
     * Ürünleri filtreler
     * @param name Ürün adı filtresi
     * @param code Ürün kodu filtresi
     * @param isStock Stok durumu filtresi
     * @return Filtrelenmiş ürün listesi
     */
    public ArrayList<Product> filter (String name, String code, Item isStock){
        String query = "SELECT * FROM product";
        ArrayList<String> whereList = new ArrayList<>();

        if (name.length() > 0){
            whereList.add("name LIKE '%" + name + "%'");
        }
        if (code.length() > 0){
            whereList.add("code LIKE '%" + code + "%'");
        }
        if (isStock != null){
            whereList.add("stock " + (isStock.getKey() == 1 ? "> 0" : "<= 0"));
        }

        if (!whereList.isEmpty()) {
            String whereQuery = " WHERE " + String.join(" AND ", whereList);
            query += whereQuery;
        }

        return this.productDao.query(query);
    }
}