import java.util.Scanner;

public class ArtikYilHesaplama {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int yil;

        // Kullanıcıdan yıl bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Yıl bilgisini giriniz: ");
        yil = inp.nextInt();

        // Yılın artık yıl olup olmadığını kontrol et ve ekrana yazdır
        if ((yil % 4 == 0 && yil % 100 != 0) || (yil % 400 == 0)) {
            System.out.println(yil + " bir artık yıldır.");
        } else {
            System.out.println(yil + " bir artık yıl değildir.");
        }
    }
}
