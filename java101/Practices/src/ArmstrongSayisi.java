import java.util.Scanner;

public class ArmstrongSayisi {
    public static void main(String[] args) {
        int sayi, basamakSayisi = 0, geciciSayi, basamakDegeri, armstrong = 0, basamakToplami = 0;

        // Kullanıcıdan sayı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        sayi = inp.nextInt();

        geciciSayi = sayi;

        // Basamak sayısını hesapla
        while (geciciSayi != 0) {
            geciciSayi /= 10;
            basamakSayisi++;
        }

        geciciSayi = sayi;

        // Armstrong sayısı olup olmadığını kontrol et
        while (geciciSayi != 0) {
            basamakDegeri = geciciSayi % 10;
            armstrong += Math.pow(basamakDegeri, basamakSayisi);
            geciciSayi /= 10;
        }

        // Sonucu ekrana yazdır
        if (armstrong == sayi) {
            System.out.println(sayi + " bir Armstrong sayısıdır.");
        } else {
            System.out.println(sayi + " bir Armstrong sayısı değildir.");
        }

        // Basamak sayıları toplamını hesapla
        for (int i = 0; i < basamakSayisi; i++) {
            basamakDegeri = sayi % 10;
            basamakToplami += basamakDegeri;
            sayi /= 10;
        }

        // Basamak sayıları toplamını ekrana yazdır
        System.out.println("Basamak sayıları toplamı: " + basamakToplami);
    }
}
