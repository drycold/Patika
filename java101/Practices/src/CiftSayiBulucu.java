
import java.util.Scanner;

public class CiftSayiBulucu {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int hedef,ort, toplam=0,sayac = 0;

        // Kullanıcıdan hedef sayıyı al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        hedef = inp.nextInt();

        
        for (int i = 1; i <= hedef; i++) {

            // Eğer sayı çift ise ekrana yazdır
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
            
            // Eğer sayı 3 ve 4'e tam bölünüyorsa sayacı artır ve toplamı güncelle
            if (i % 12 == 0) {
                sayac++;
                toplam += i;
            }
        }
        // 3 ve 4'e tam bölünen sayıların ortalamasını hesapla ve ekrana yazdır
        ort = toplam / sayac;
        System.out.println("\n3 ve 4'e tam bölünen sayıların ortalaması: " + ort);
    }
}
