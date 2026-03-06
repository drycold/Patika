public class DiziOrtalamasi {
    public static void main(String[] args) {
        
        int[] dizi = {1, 2, 3, 4, 5};

        int toplam = 0;
        double ort = 0;

        for (int i = 0; i < dizi.length ; i++){
            
            toplam += dizi[i];
        }
        
        ort = toplam/dizi.length;
        System.out.println("Dizi elemanlarının ortalaması: " + ort);


    }
}
