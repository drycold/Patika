import java.util.Scanner;

public class HarmonikSayi {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n;
        double harmonikSayi = 0.0;

        // Kullanıcıdan n bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("n sayısını giriniz: ");
        n = inp.nextInt();

        // Harmonik sayı hesaplama
        for (int i = 1; i <= n; i++) {
            harmonikSayi += (1.0 / i);
        }

        // Sonucu ekrana yazdır
        System.out.println("Harmonik sayı: " + harmonikSayi);
    }
}
