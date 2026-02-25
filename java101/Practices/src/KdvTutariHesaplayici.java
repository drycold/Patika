import java.util.Scanner;

public class KdvTutariHesaplayici {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        double KDVsizFiyat, KDVliFiyat, KDVTutari, KDV_ORANİ;

        // Kullanıcıdan KDV'siz fiyatı al
        Scanner inp = new Scanner(System.in);

        System.out.print("KDV'siz Fiyatı Giriniz : ");
        KDVsizFiyat = inp.nextDouble();
        
        // KDV oranını belirle
        if (KDVsizFiyat > 0 && KDVsizFiyat <= 1000) {
            KDV_ORANİ = 0.18;
        }
        else {
            KDV_ORANİ = 0.08;
        }

        // KDV'li fiyatı ve KDV tutarını hesapla
        KDVliFiyat = KDVsizFiyat + (KDVsizFiyat * KDV_ORANİ);
        KDVTutari = KDVliFiyat - KDVsizFiyat;

        // Sonuçları yazdır
        System.out.println("KDV'li Fiyat : " + KDVliFiyat);
        System.out.println("KDV Tutarı : " + KDVTutari);
        
    }
}
