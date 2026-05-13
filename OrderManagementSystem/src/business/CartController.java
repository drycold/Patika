package business;

import dao.CartDao;
import entity.Cart;
import java.util.ArrayList;

/**
 * Sipariş sepeti işlemlerini yöneten kontrol sınıfı.
 * Bu sınıf, iş mantığının DAO katmanı ile iletişim kurduğu noktadır.
 */
public class CartController {
    private final CartDao cartDao = new CartDao();

    /**
     * Yeni bir siparişi kaydeder.
     * @param cart Kaydedilecek Cart nesnesi
     * @return Kaydetme başarılıysa true
     */
    public boolean save(Cart cart) {
        return cartDao.save(cart);
    }

    /**
     * Veritabanındaki tüm siparişleri listeler.
     * @return Tüm Cart kayıtlarından oluşan liste
     */
    public ArrayList<Cart> findAll() {
        return cartDao.findAll();
    }
}
