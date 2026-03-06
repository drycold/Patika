import java.util.Arrays;

public class TekrarEdenSayilar {
    public static void main(String[] args) {
        
        // Dizileri ve değişken tanımla
        int[] dizi = { 3, 4, 3, 4, 2, 9, 10, 24, 1, 2, 9, 24 };
        int[] tekrarEdenler = new int[dizi.length];
        int baslangicIndexi = 0;

        // Tekrar eden elemaları tekrarEdenler dizisine ekle
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi.length; j++) {
                if ((i != j) && (dizi[i] == dizi[j] && dizi[j] % 2 == 0)) {
                    
                    tekrarEdenler[baslangicIndexi++] = dizi[j];
                    break;
                }
            }
        }
        
        // Sonucu yazdır
        System.out.println(Arrays.toString(tekrarEdenler));

    }
}
