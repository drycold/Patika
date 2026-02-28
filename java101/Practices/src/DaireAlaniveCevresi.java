import java.util.Scanner;

public class DaireAlaniveCevresi {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        double r, pi = 3.14, alan, cevre, daireDilimiAlani, merkezAci;

        // Kullanıcıdan dairenin yarıçapını al
        Scanner inp = new Scanner(System.in);
        System.out.print("Dairenin Yarıçapını Giriniz : ");
        r = inp.nextDouble();

        // Daire alanını ve çevresini hesapla
        alan = pi * r * r;
        cevre = 2 * pi * r;

        // Kullanıcıdan merkez açıyı al
        System.out.print("Merkez Açıyı Giriniz : ");
        merkezAci = inp.nextDouble();
        
        // Daire dilimi alanını hesapla
        daireDilimiAlani = (pi * r * r * merkezAci) / 360;

        // Sonuçları yazdır
        System.out.println("Daire Alanı : " + alan);
        System.out.println("Daire Çevresi : " + cevre);
        System.out.println("Daire Dilimi Alanı : " + daireDilimiAlani);
    }
}
