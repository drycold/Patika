import java.util.Scanner;

public class TersUcgen {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n;

        // Kullanıcıdan basamak sayısı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Basamak sayısını giriniz: ");
        n = inp.nextInt();

        // Ters üçgen oluşturma
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j < (n - i); j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
