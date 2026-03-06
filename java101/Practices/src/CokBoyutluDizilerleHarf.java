public class CokBoyutluDizilerleHarf {
    public static void main(String[] args) {
        
        // Diziyi tanımla
        String[][] dizi = new String[7][4];

        // B harfi için gerekli satır ve kolon indexlerinin belirlenmesi ve yıldızlanması
        for (int i = 0 ; i < dizi.length ; i++){
            for ( int j = 0 ; j < dizi[0].length ; j++){
                if ((i == 0 || i == 3|| i == 6) && j != 3)
                    dizi[i][j] = " * ";
                else if (j == 0 || (j == 3 && i % 3 != 0))
                    dizi[i][j] = " * ";
                else 
                    dizi[i][j] = "   ";
            }
        }

        // Sonucun yazılması
        for (String[] strings : dizi) {
            for (String string2 : strings) {
                System.out.print(string2);

            }
            System.out.println();
        }
    }
}
