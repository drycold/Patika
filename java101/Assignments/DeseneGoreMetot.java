import java.util.Scanner;

public class DeseneGoreMetot {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n;

        // Kullanıcıdan basamak sayısını al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sayı girin: ");
        n = scanner.nextInt();

        // Desene göre sayıları yazdırma
        System.out.println("Desene göre sayılar: ");
        deseneGoreYazdir(n);
    }

    // Desene göre sayıları yazdıran recursive metod
    public static void deseneGoreYazdir(int n) {
        if (n <= 0) {
            System.out.print(n + " ");
            return;
        }
        System.out.print(n + " ");
        deseneGoreYazdir(n - 5);
        System.out.print(n + " ");
    }
}
