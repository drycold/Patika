import java.util.Scanner;

public class PolindromSayilar {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        int sayi;

        // Kullanıcıdan bir sayı al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bir sayı girin: ");
        sayi = scanner.nextInt();

        // Polindrom sayıları bulma ve ekrana yazdırma
        if (isPolindrom(sayi)) {
                System.out.print(sayi + " Bir polindrom sayıdır.");
        }else {
                System.out.print(sayi + " Bir polindrom sayı değildir.");
        }
    }

    // Polindrom sayı kontrolü için yardımcı metod
    public static boolean isPolindrom(int sayi) {
        int tersSayi = 0;
        int orijinalSayi = sayi;

        while (sayi != 0) {
            tersSayi = tersSayi * 10 + sayi % 10;
            sayi /= 10;
        }

        return orijinalSayi == tersSayi;
    }
}
