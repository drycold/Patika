import java.util.Scanner;

public class MukemmelSayi {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int sayi, toplam = 0;

        // Kullanıcıdan sayı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        sayi = inp.nextInt();

        // Mükemmel sayı kontrolü
        for (int i = 1; i < sayi; i++) {
            if (sayi % i == 0) {
                toplam += i;
            }
        }

        // Sonucu ekrana yazdır
        if (toplam == sayi) {
            System.out.println(sayi + " bir mükemmel sayıdır.");
        } else {
            System.out.println(sayi + " bir mükemmel sayı değildir.");
        }
    }
}
