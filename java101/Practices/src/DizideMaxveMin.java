public class DizideMaxveMin {
    public static void main(String[] args) {

        // Değişkneleri tanımla
        int[] dizi = {56, 34, 1, 8, 101, -2, -33};

        // Max ve min değerlerini tanımla
        int max = dizi[0];
        int min = dizi[0];

        // Dizini her elemanının ilk max ve min değerleriyle karşılaştır
        for (int i : dizi){
            if (i < min){
                min = i;    
            }

            if (i > max){
                max = i;
            }
        }

        // Sonucu yazdır
        System.out.println("Dizinin en büyük elemanı : " + max);
        System.out.println("Dizinin en küçük elemanı : " + min);
    }
}
