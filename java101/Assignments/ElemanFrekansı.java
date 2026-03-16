public class ElemanFrekansı {
    public static void main(String[] args) {
        
        // Dizinin tanımlanması
        int[] dizi = {10, 20, 20, 10, 10, 20, 5, 20};
        boolean[] isChecked = new boolean[dizi.length];

        for(int i = 0; i < dizi.length; i++){

            // Eleman daha önce sayıldı mı kontrolü 
            if (isChecked[i]) { 
                continue;
            }

            // Sayma işlemi
            int frekans = 1; 
            for (int j = i + 1; j < dizi.length; j++) {
                if (dizi[i] == dizi[j]) {

                    frekans++;
                    isChecked[j] = true; 
                }
            }

            // Sonucu yazdır
            System.out.println(dizi[i] + " sayısı " + frekans + " kere tekrar edildi.");
        }
    }
}
