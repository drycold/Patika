import java.util.Scanner;

public class AsalSayilar {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n;

        // Kullanıcıdan sayı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        n = inp.nextInt();

        // Asal sayıları bulma ve ekrana yazdırma
        System.out.println("Asal sayılar: ");
        for (int i = 2; i <= n; i++) {
            boolean asal = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    asal = false;
                    break;
                }
            }
            if (asal) {
                System.out.print(i + " ");
            }
        }
    }
}
