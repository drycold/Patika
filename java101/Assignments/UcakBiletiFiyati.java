
import java.util.Scanner;

public class UcakBiletiFiyati {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int km, yas, tip;
        double normalFiyat, indirimliFiyat, kmBasiFiyat = 0.10;

        // Kullanıcıdan bilgileri al
        Scanner inp = new Scanner(System.in);

        System.out.print("Mesafeyi km cinsinden giriniz: ");
        km = inp.nextInt();

        System.out.print("Yaşınızı giriniz: ");
        yas = inp.nextInt();

        System.out.print("Yolculuk tipini giriniz (1 => Tek Yön, 2 => Gidiş Dönüş): ");
        tip = inp.nextInt();

        // Verilerin geçerliliğini kontrol et
        if (km <= 0 || yas <= 0 || (tip != 1 && tip != 2)) {
            System.out.println("Hatalı veri girdiniz!");
            return;
        }

        // Bilet fiyatını hesapla
        normalFiyat = km * kmBasiFiyat;

        // Yaşa göre indirim uygulama
        if (yas < 12) {
            indirimliFiyat = normalFiyat * 0.5;
        } else if (yas >= 12 && yas <= 24) {
            indirimliFiyat = normalFiyat * 0.9;
        } else if (yas > 65) {
            indirimliFiyat = normalFiyat * 0.7;
        } else {
            indirimliFiyat = normalFiyat;
        }

        // Yolculuk tipine göre indirim uygulama
        if (tip == 2) {
            indirimliFiyat *= 0.8;
        }

        // Sonuçları ekrana yazdır
        System.out.println("Toplam tutar: " + indirimliFiyat + " TL");
    }
}
