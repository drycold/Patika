
import java.util.Scanner;

public class FaktöriyelveKombinasyon {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int f, n, r,kombinasyon, faktoriyel = 1, nfaktoriyel = 1, rfaktoriyel = 1, nrfaktoriyel = 1;

        Scanner inp = new Scanner(System.in);
        System.out.print("Faktöriyel sayısını giriniz: ");
        f = inp.nextInt();

        // Faktöriyel hesaplama
        for (int i = 1; i <= f; i++) {
            faktoriyel *= i;
        }
        System.out.println(f + "! = " + faktoriyel);

        // Kombinasyon hesaplama
        Scanner inp2 = new Scanner(System.in);
        System.out.print("Kombinasyon için eleman sayısını(n) giriniz: ");
        n = inp2.nextInt();
        System.out.print("Kombinasyon için oluşturulcak grubun eleman sayısını(r) giriniz: ");
        r = inp2.nextInt();
        System.out.println();

        for (int i = 1; i <= n; i++) {
            nfaktoriyel *= i;
        }
        
        for (int j = 1; j <= r; j++) {
            rfaktoriyel *= j;
        }

        for (int k = 1; k <= (n - r); k++) {
            nrfaktoriyel *= k;
        }

        kombinasyon = nfaktoriyel / (rfaktoriyel * nrfaktoriyel);
        System.out.println("Kombinasyon: " + kombinasyon);
    }
}