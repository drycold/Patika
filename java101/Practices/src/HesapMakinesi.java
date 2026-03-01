
import java.util.Scanner;

public class HesapMakinesi {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        int sayi1, sayi2, islem;

        // Kullanıcıdan iki sayı ve yapılacak işlemi al
        Scanner inp = new Scanner(System.in);

        System.out.print("Birinci sayıyı giriniz: ");
        sayi1 = inp.nextInt();

        System.out.print("İkinci sayıyı giriniz: ");
        sayi2 = inp.nextInt();
        
        System.out.println("1-Toplama\n2-Çıkarma\n3-Çarpma\n4-Bölme");
        System.out.print("Yapmak istediğiniz işlemi seçiniz (1-4): ");
        islem = inp.nextInt();

        // Seçilen işleme göre sonucu hesapla ve ekrana yazdır
        switch (islem) {
            case 1:
                System.out.println("Sonuç: " + (sayi1 + sayi2));
                break;
            case 2:
                System.out.println("Sonuç: " + (sayi1 - sayi2));
                break;
            case 3:
                System.out.println("Sonuç: " + (sayi1 * sayi2));
                break;
            case 4:
                if (sayi2 != 0) {
                    System.out.println("Sonuç: " + (sayi1 / sayi2));
                } else {
                    System.out.println("Hata: Bir sayı sıfıra bölünemez.");
                }
                break;
            default:
                System.out.println("Geçersiz işlem seçimi.");
        }
    }
}