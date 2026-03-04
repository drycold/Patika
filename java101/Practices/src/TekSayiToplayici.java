import java.util.Scanner;

public class TekSayiToplayici {
    public static void main(String[] args) {
        
        int sayi, toplam = 0;

        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        sayi = inp.nextInt();

        do {

            if (sayi % 2 == 0 && sayi % 4 == 0) {
                toplam += sayi;
            }
            sayi = inp.nextInt();

        } while (sayi % 2 == 0);
        
        System.out.println("4'ün katları toplamı: " + toplam);
    }
}
