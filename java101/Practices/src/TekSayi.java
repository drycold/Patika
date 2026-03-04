import java.util.Scanner;

public class TekSayi {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int sayi, toplam = 0;

        // Kullanıcıdan sayı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        sayi = inp.nextInt();

        // Kullanıcı tek sayı girdiği sürece döngüyü devam ettir
        do {

            // Eğer sayı çift ve 4'ün katı ise toplamı güncelle
            if (sayi % 2 == 0 && sayi % 4 == 0) {
                toplam += sayi;
            }
            sayi = inp.nextInt();

        } while (sayi % 2 == 0);
        
        // 4'ün katları toplamını ekrana yazdır
        System.out.println("4'ün katları toplamı: " + toplam);
    }
}
