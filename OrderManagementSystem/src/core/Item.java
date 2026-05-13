package core;

/**
 * Anahtar-değer çifti için kullanılan yardımcı sınıf
 * ComboBox gibi bileşenlerde kullanılmak üzere tasarlanmıştır
 */
public class Item {
    private int key;
    private String value;

    /**
     * Item nesnesi oluşturur
     * @param key Anahtar değeri
     * @param value Görüntülenecek metin
     */
    public Item(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Anahtar değerini döndürür
     * @return Anahtar değeri
     */
    public int getKey() {
        return key;
    }

    /**
     * Anahtar değerini ayarlar
     * @param key Yeni anahtar değeri
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Görüntülenecek metni döndürür
     * @return Metin değeri
     */
    public String getValue() {
        return value;
    }

    /**
     * Görüntülenecek metni ayarlar
     * @param value Yeni metin değeri
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * ComboBox'ta görüntülenecek metni döndürür
     * @return Metin değeri
     */
    @Override
    public String toString() {
        return this.value;
    }
}
