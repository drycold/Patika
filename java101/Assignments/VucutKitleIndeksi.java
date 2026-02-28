import java.util.Scanner;

public class VucutKitleIndeksi {
    public static void main(String[] args) {
    
    // Değişkenleri tanımla
    double boy, kilo, vki;

    // Kullanıcıdan boy ve kilo bilgilerini al
    Scanner inp = new Scanner(System.in);

    System.out.print("Lütfen boyumuzu (metre cinsinden) giriniz : ");
    boy = inp.nextDouble();
    System.out.print("Lütfen kilonuzu (kg cinsinden) giriniz : ");
    kilo = inp.nextDouble();

    // Vücut Kitle İndeksini hesapla
    vki = kilo / (boy * boy);
    
    // Sonucu yazdır
    System.out.println("Vücut Kitle İndeksiniz : " + vki);
    }
}