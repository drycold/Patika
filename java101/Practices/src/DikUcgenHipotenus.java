import java.util.Scanner;

public class DikUcgenHipotenus {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        double a, b, c;

        // Kullanıcıdan dik üçgenin iki kenarını al
        Scanner inp = new Scanner(System.in);

        System.out.print("Dik Üçgenin Birinci Kenarını Giriniz : ");
        a = inp.nextDouble();

        System.out.print("Dik Üçgenin İkinci Kenarını Giriniz : ");
        b = inp.nextDouble();

        // Hipotenüsü hesapla
        c = Math.sqrt((a * a) + (b * b));

        // Sonucu yazdır
        System.out.println("Dik Üçgenin Hipotenüsü : " + c);

        // Üçgenin çevresini ve alanını hesapla
        double u, cevre, alan;

        cevre = a + b + c;
        u = cevre / 2;

        alan = Math.sqrt(u * (u - a) * (u - b) * (u - c));

        // Sonuçları yazdır
        System.out.println("Üçgenin Çevresi : " + cevre);
        System.out.println("Üçgenin Alanı : " + alan);
    }
}