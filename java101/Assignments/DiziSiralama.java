import java.util.Arrays;
import java.util.Scanner;

public class DiziSiralama {
    public static void main(String[] args) {


        // Dizi boyutunun tanımlanması  
        Scanner inp = new Scanner(System.in);

        System.out.print("Dizinin boyutu n : ");
        int size = inp.nextInt();

        int[] dizi = new int[size];

        // Elemanların kullanıcıdan alınması
        System.out.println("Dizinin elemanlarını giriniz : ");

        for(int i = 0; i < size; i++){

            System.out.print((i + 1) + ". Eleman : ");
            dizi[i] = inp.nextInt();
        }
        inp.close();
        
        // Elemanların sıralanması
        for (int i = 0; i < dizi.length - 1; i++) {
        int minIndex = i;

            for (int j = i + 1; j < dizi.length; j++) {
                if (dizi[j] < dizi[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = dizi[i];
            dizi[i] = dizi[minIndex];
            dizi[minIndex] = temp;
        }

        // Sonucun yazdırılması
        System.out.println("Sıralama: " + Arrays.toString(dizi));
    }
}
