import java.util.Scanner;

public class TaksimetreProgrami {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        double km, toplamTutar, acilisUcreti = 10, kmUcreti = 2.20;

        // Kullanıcıdan gidilen mesafeyi al
        Scanner inp = new Scanner(System.in);

        System.out.print("Gidilen Mesafeyi Giriniz (km) : ");
        km = inp.nextDouble();

        // Toplam tutarı hesapla
        toplamTutar = acilisUcreti + (km * kmUcreti);

        // Eğer toplam tutar 20 TL'den az ise, toplam tutarı 20 TL yap
        if (toplamTutar < 20) {
            toplamTutar = 20;
        }

        // Sonucu yazdır
        System.out.println("Toplam Tutar : " + toplamTutar);
    } 
}
