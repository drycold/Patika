
import java.util.Scanner;

public class YildizlarlaUcgenveElmas {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n;

        // Kullanıcıdan basamak sayısı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        n = inp.nextInt();

        // Üçgen oluşturma
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j < (n - i); j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i + 1); k++) {
                System.out.print("*");
            }
            System.out.println(" ");
        }

        System.out.println();

        // Elmas oluşturma

        // Elmasın üst kısmı
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Elmasın alt kısmı
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        }
    }
