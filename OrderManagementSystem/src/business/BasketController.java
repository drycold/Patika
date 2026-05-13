package business;

import dao.BasketDao;
import entity.Basket;
import java.util.ArrayList;

/**
 * Sepet işlemlerini yöneten kontrol sınıfı.
 * Bu sınıf, sepet ekleme, listeleme ve temizleme işlemlerini DAO katmanına iletir.
 */
public class BasketController {
    private final BasketDao basketDao = new BasketDao();

    /**
     * Sepete yeni bir ürün kaydeder.
     * @param basket Sepet kaydı
     * @return Kaydetme başarılıysa true
     */
    public boolean save(Basket basket) {
        return this.basketDao.save(basket);
    }

    /**
     * Sepetteki tüm ürün kayıtlarını getirir.
     * @return Sepet içeriği listesi
     */
    public ArrayList<Basket> findAll() {
        return this.basketDao.findAll();
    }

    /**
     * Sepeti temizler.
     * @return Temizleme başarılıysa true
     */
    public boolean clear() {
        return this.basketDao.clear();
    }
}
