import java.util.Scanner;

public class MinveMax {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int sayi, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // Kullanıcıdan kaç adet sayı gireceğini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Kaç adet sayı gireceksiniz: ");
        int adet = inp.nextInt();

        // Kullanıcıdan sayı bilgilerini al ve minimum ile maksimum değerleri güncelle
        for (int i = 0; i < adet; i++) {
            System.out.print((i + 1) + ". sayıyı giriniz: ");
            sayi = inp.nextInt();

            if (sayi < min) {
                min = sayi;
            }

            if (sayi > max) {
                max = sayi;
            }
        }

        // Minimum ve maksimum değerleri ekrana yazdır
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
    }
}
