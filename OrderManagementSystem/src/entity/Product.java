package entity;

/**
 * Ürün varlığını temsil eden entity sınıfı
 * Veritabanındaki product tablosuna karşılık gelir
 */
public class Product {
    private int id;
    private String name;
    private String code;
    private int price;
    private int stock;

    /**
     * Varsayılan yapıcı metod
     */
    public Product() {
    }

    /**
     * Ürün ID'sini döndürür
     * @return Ürün ID'si
     */
    public int getId() {
        return id;
    }

    /**
     * Ürün ID'sini ayarlar
     * @param id Yeni ürün ID'si
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ürün adını döndürür
     * @return Ürün adı
     */
    public String getName() {
        return name;
    }

    /**
     * Ürün adını ayarlar
     * @param name Yeni ürün adı
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ürün kodunu döndürür
     * @return Ürün kodu
     */
    public String getCode() {
        return code;
    }

    /**
     * Ürün kodunu ayarlar
     * @param code Yeni ürün kodu
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Ürün fiyatını döndürür
     * @return Ürün fiyatı
     */
    public int getPrice() {
        return price;
    }

    /**
     * Ürün fiyatını ayarlar
     * @param price Yeni ürün fiyatı
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Ürün stok miktarını döndürür
     * @return Stok miktarı
     */
    public int getStock() {
        return stock;
    }

    /**
     * Ürün stok miktarını ayarlar
     * @param stock Yeni stok miktarı
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Ürün bilgilerini string olarak döndürür
     * @return Ürün bilgileri
     */
    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

}
