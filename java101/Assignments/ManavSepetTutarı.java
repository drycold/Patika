
import java.util.Scanner;

public class ManavSepetTutarı {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        double toplamTutar, armutKg = 2.14, elmaKg = 3.67, domatesKg = 1.11, muzKg = 0.95, patlıcanKg = 5.00;

        // Kullanıcıdan miktarları al
        Scanner inp = new Scanner(System.in);

        System.out.print("kaç kilo Armut alındı? : ");
        double armutMiktar = inp.nextDouble();

        System.out.print("kaç kilo Elma alındı? : ");
        double elmaMiktar = inp.nextDouble();

        System.out.print("kaç kilo Domates alındı? : ");
        double domatesMiktar = inp.nextDouble();

        System.out.print("kaç kilo Muz alındı? : ");
        double muzMiktar = inp.nextDouble();

        System.out.print("kaç kilo Patlıcan alındı? : ");
        double patlıcanMiktar = inp.nextDouble();

        // Toplam tutarı hesapla
        toplamTutar = (armutKg * armutMiktar) + (elmaKg * elmaMiktar) +
                (domatesKg * domatesMiktar) + (muzKg * muzMiktar) +
                (patlıcanKg * patlıcanMiktar);

        // Sonucu ekrana yazdır
        System.out.println("Toplam Tutar: " + toplamTutar + " TL");
    }
}
