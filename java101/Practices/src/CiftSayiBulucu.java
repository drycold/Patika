
import java.util.Scanner;

public class CiftSayiBulucu {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int hedef;

        // Kullanıcıdan hedef sayıyı al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        hedef = inp.nextInt();

        // 1'den hedef sayıya kadar olan çift sayıları ekrana yazdır
        for (int i = 1; i <= hedef; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
    }
}
