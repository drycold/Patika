# Order Management System Android Mobil Versiyon Rehberi

Bu doküman, mevcut Java Swing tabanlı `OrderManagementSystem` projesinin Android Studio ile mobil uygulamaya dönüştürülmesi için hazırlanmıştır. Amaç yalnızca ekranları mobilde yeniden çizmek değil; mevcut müşteri, ürün, sepet ve sipariş mantığını Android'e uygun, sürdürülebilir ve güvenli bir mimariyle taşımaktır.

## 1. Mevcut Proje Özeti

Mevcut proje masaüstü Java uygulamasıdır ve Swing arayüzü kullanır. Veriler MySQL veritabanında tutulur.

### Mevcut Katmanlar

| Katman | Klasör | Görev |
|---|---|---|
| UI | `src/view` | Swing ekranları |
| Controller | `src/business` | İş kuralları ve DAO çağrıları |
| DAO | `src/dao` | MySQL CRUD işlemleri |
| Entity | `src/entity` | Veri modelleri |
| Core | `src/core` | Veritabanı bağlantısı ve yardımcı sınıflar |

### Mevcut Ana Özellikler

- Kullanıcı girişi
- Müşteri listeleme, ekleme, güncelleme, silme
- Müşteri filtreleme
- Ürün listeleme, ekleme, güncelleme, silme
- Ürün filtreleme
- Ürünü sepete ekleme
- Sepeti listeleme ve temizleme
- Sepetteki ürünlerden sipariş oluşturma
- Siparişleri listeleme

## 2. Mobil Uygulama İçin Önerilen Mimari

Android uygulaması doğrudan MySQL'e bağlanmamalıdır. Bunun yerine mobil uygulama bir backend API ile konuşmalıdır.

### Neden Android'den MySQL'e Doğrudan Bağlanılmamalı?

- Veritabanı kullanıcı adı ve şifresi APK içinde kalır.
- MySQL sunucusunu internete açmak güvenlik riski oluşturur.
- Mobil ağ kopmaları doğrudan veritabanı bağlantısını kararsız hale getirir.
- Yetkilendirme, doğrulama ve loglama zorlaşır.
- Uygulama büyüdükçe bakım maliyeti artar.

### Önerilen Mimari

```text
Android App
    |
    | HTTPS / JSON
    v
Backend REST API
    |
    | JDBC / ORM
    v
MySQL Database
```

### Önerilen Teknoloji Seçimi

Mobil uygulama:

- Android Studio
- Kotlin
- Jetpack Compose
- MVVM mimarisi
- Retrofit
- Kotlin Coroutines
- ViewModel
- Navigation Compose
- DataStore veya EncryptedSharedPreferences

Backend API:

- Spring Boot önerilir.
- Alternatif olarak mevcut Java bilgisine yakın kalmak için Java + Spring Boot iyi seçimdir.
- Veritabanı olarak mevcut MySQL kullanılabilir.

## 3. Mobilde Oluşturulacak Ekranlar

Mevcut Swing ekranları Android'de aşağıdaki şekilde karşılanabilir.

| Mevcut Swing Ekranı | Android Ekranı | Açıklama |
|---|---|---|
| `LoginUI` | `LoginScreen` | Kullanıcı girişi |
| `DashboardUI` | `MainScreen` | Ana navigasyon alanı |
| Müşteriler tabı | `CustomerListScreen` | Müşteri listesi ve filtre |
| `CustomerUI` | `CustomerFormScreen` | Müşteri ekleme/güncelleme |
| Ürünler tabı | `ProductListScreen` | Ürün listesi ve filtre |
| `ProductUI` | `ProductFormScreen` | Ürün ekleme/güncelleme |
| Sipariş Oluştur tabı | `BasketScreen` | Sepet görüntüleme |
| `CartUI` | `CreateOrderScreen` | Müşteri seçerek sipariş oluşturma |
| Siparişler tabı | `OrderListScreen` | Oluşturulan siparişleri listeleme |

## 4. Android Navigasyon Yapısı

Mobil uygulamada masaüstündeki tab yapısı yerine alt navigasyon veya navigation drawer kullanılabilir.

Önerilen yapı:

```text
LoginScreen
    |
    v
MainScreen
    |
    |-- Customers
    |-- Products
    |-- Basket
    |-- Orders
```

Alt navigasyon önerilir çünkü uygulamanın ana modülleri nettir:

- Müşteriler
- Ürünler
- Sepet
- Siparişler

## 5. Android Proje Klasör Yapısı

Android Studio'da yeni proje açarken `Empty Activity` ve Kotlin seçilebilir.

Önerilen paket yapısı:

```text
app/src/main/java/com/example/ordermanagement/
    data/
        remote/
            ApiService.kt
            RetrofitClient.kt
        model/
            UserDto.kt
            ProductDto.kt
            CustomerDto.kt
            BasketDto.kt
            OrderDto.kt
        repository/
            AuthRepository.kt
            ProductRepository.kt
            CustomerRepository.kt
            BasketRepository.kt
            OrderRepository.kt
    domain/
        model/
            User.kt
            Product.kt
            Customer.kt
            BasketItem.kt
            Order.kt
    ui/
        auth/
            LoginScreen.kt
            LoginViewModel.kt
        customer/
            CustomerListScreen.kt
            CustomerFormScreen.kt
            CustomerViewModel.kt
        product/
            ProductListScreen.kt
            ProductFormScreen.kt
            ProductViewModel.kt
        basket/
            BasketScreen.kt
            BasketViewModel.kt
        order/
            OrderListScreen.kt
            CreateOrderScreen.kt
            OrderViewModel.kt
        navigation/
            AppNavigation.kt
        theme/
            Color.kt
            Theme.kt
            Type.kt
```

## 6. Veri Modeli Eşleştirmesi

### User

Mevcut Java sınıfı:

```java
User {
    int id;
    String name;
    String mail;
    String password;
}
```

Android Kotlin modeli:

```kotlin
data class User(
    val id: Int,
    val name: String,
    val mail: String
)
```

Not: Şifre mobil uygulamada model içinde tutulmamalıdır. Giriş isteğinde gönderilir, sonra saklanmaz.

### Product

```kotlin
data class Product(
    val id: Int,
    val name: String,
    val code: String,
    val price: Int,
    val stock: Int
)
```

### Customer

```kotlin
enum class CustomerType {
    PERSON,
    COMPANY
}

data class Customer(
    val id: Int,
    val name: String,
    val mail: String,
    val phone: String,
    val address: String,
    val type: CustomerType
)
```

### BasketItem

```kotlin
data class BasketItem(
    val id: Int,
    val productId: Int,
    val product: Product?
)
```

### Order

Mevcut projede sipariş entity adı `Cart` olarak geçiyor. Mobil uygulamada kullanıcı deneyimi açısından `Order` ismi daha anlaşılırdır.

```kotlin
data class Order(
    val id: Int,
    val customerId: Int,
    val productId: Int,
    val price: Int,
    val date: String,
    val note: String?,
    val customer: Customer?,
    val product: Product?
)
```

## 7. Backend API Tasarımı

Mobil uygulamanın kullanacağı REST endpoint'leri aşağıdaki gibi olabilir.

### Auth

| Metot | Endpoint | Açıklama |
|---|---|---|
| POST | `/api/auth/login` | Kullanıcı girişi |

Örnek istek:

```json
{
  "mail": "admin@example.com",
  "password": "1234"
}
```

Örnek cevap:

```json
{
  "token": "jwt-token",
  "user": {
    "id": 1,
    "name": "Admin",
    "mail": "admin@example.com"
  }
}
```

### Customers

| Metot | Endpoint | Açıklama |
|---|---|---|
| GET | `/api/customers` | Müşterileri listeler |
| GET | `/api/customers/{id}` | Tek müşteri getirir |
| POST | `/api/customers` | Yeni müşteri ekler |
| PUT | `/api/customers/{id}` | Müşteri günceller |
| DELETE | `/api/customers/{id}` | Müşteri siler |
| GET | `/api/customers?name=Ali&type=PERSON` | Filtreli listeleme |

### Products

| Metot | Endpoint | Açıklama |
|---|---|---|
| GET | `/api/products` | Ürünleri listeler |
| GET | `/api/products/{id}` | Tek ürün getirir |
| POST | `/api/products` | Yeni ürün ekler |
| PUT | `/api/products/{id}` | Ürün günceller |
| DELETE | `/api/products/{id}` | Ürün siler |
| GET | `/api/products?name=Kalem&code=KLM&stockStatus=IN_STOCK` | Filtreli listeleme |

### Basket

| Metot | Endpoint | Açıklama |
|---|---|---|
| GET | `/api/basket` | Sepeti listeler |
| POST | `/api/basket` | Sepete ürün ekler |
| DELETE | `/api/basket` | Sepeti temizler |

Örnek sepete ekleme isteği:

```json
{
  "productId": 5
}
```

### Orders

| Metot | Endpoint | Açıklama |
|---|---|---|
| GET | `/api/orders` | Siparişleri listeler |
| POST | `/api/orders` | Sipariş oluşturur |

Örnek sipariş oluşturma isteği:

```json
{
  "customerId": 3,
  "items": [
    {
      "productId": 5,
      "price": 250
    }
  ],
  "date": "2026-05-15",
  "note": "Mobil uygulamadan oluşturuldu"
}
```

## 8. Android Retrofit Servis Örneği

```kotlin
interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("customers")
    suspend fun getCustomers(
        @Query("name") name: String? = null,
        @Query("type") type: String? = null
    ): List<CustomerDto>

    @POST("customers")
    suspend fun createCustomer(@Body request: CustomerDto): CustomerDto

    @PUT("customers/{id}")
    suspend fun updateCustomer(
        @Path("id") id: Int,
        @Body request: CustomerDto
    ): CustomerDto

    @DELETE("customers/{id}")
    suspend fun deleteCustomer(@Path("id") id: Int)

    @GET("products")
    suspend fun getProducts(
        @Query("name") name: String? = null,
        @Query("code") code: String? = null,
        @Query("stockStatus") stockStatus: String? = null
    ): List<ProductDto>

    @POST("products")
    suspend fun createProduct(@Body request: ProductDto): ProductDto

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body request: ProductDto
    ): ProductDto

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int)

    @GET("basket")
    suspend fun getBasket(): List<BasketDto>

    @POST("basket")
    suspend fun addToBasket(@Body request: AddBasketRequest)

    @DELETE("basket")
    suspend fun clearBasket()

    @GET("orders")
    suspend fun getOrders(): List<OrderDto>

    @POST("orders")
    suspend fun createOrder(@Body request: CreateOrderRequest): OrderDto
}
```

## 9. Android Ekran Detayları

### LoginScreen

Alanlar:

- E-posta
- Şifre
- Giriş butonu

Davranış:

- Boş alan kontrolü yapılır.
- API'ye login isteği atılır.
- Başarılı girişte token saklanır.
- Kullanıcı `MainScreen` ekranına yönlendirilir.
- Hatalı girişte kullanıcıya kısa hata mesajı gösterilir.

### CustomerListScreen

İçerik:

- Müşteri arama alanı
- Müşteri tipi filtresi
- Müşteri listesi
- Yeni müşteri ekleme butonu

Kart veya liste satırında gösterilecek bilgiler:

- Ad
- Telefon
- E-posta
- Tip

İşlemler:

- Müşteri ekleme
- Müşteri düzenleme
- Müşteri silme
- Filtreleme

### CustomerFormScreen

Alanlar:

- Ad
- E-posta
- Telefon
- Adres
- Tip: `PERSON` veya `COMPANY`

Validasyon:

- Ad boş olamaz.
- Telefon boş olamaz.
- E-posta formatı kontrol edilebilir.
- Tip seçili olmalıdır.

### ProductListScreen

İçerik:

- Ürün adı arama
- Ürün kodu arama
- Stok durumu filtresi
- Ürün listesi
- Yeni ürün ekleme butonu

Kart veya liste satırında gösterilecek bilgiler:

- Ürün adı
- Ürün kodu
- Fiyat
- Stok

İşlemler:

- Ürün ekleme
- Ürün düzenleme
- Ürün silme
- Sepete ekleme
- Filtreleme

### ProductFormScreen

Alanlar:

- Ürün adı
- Ürün kodu
- Fiyat
- Stok

Validasyon:

- Ürün adı boş olamaz.
- Ürün kodu boş olamaz.
- Fiyat negatif olamaz.
- Stok negatif olamaz.

### BasketScreen

İçerik:

- Sepetteki ürünler
- Toplam tutar
- Sepeti temizle butonu
- Sipariş oluştur butonu

Davranış:

- Sepet boşsa boş durum mesajı gösterilir.
- Sipariş oluştururken müşteri seçimi yapılır.
- Sipariş tamamlanınca sepet temizlenir.

### CreateOrderScreen

Alanlar:

- Müşteri seçimi
- Tarih
- Not
- Sipariş özeti

Davranış:

- Müşteri seçmeden sipariş oluşturulamaz.
- Sepette ürün yoksa sipariş oluşturulamaz.
- Başarılı siparişten sonra `OrderListScreen` veya `BasketScreen` ekranına dönülür.

### OrderListScreen

İçerik:

- Sipariş tarihi
- Müşteri adı
- Ürün adı
- Fiyat
- Not

Ek geliştirme önerisi:

- Tarihe göre filtreleme
- Müşteriye göre filtreleme
- Sipariş detay ekranı

## 10. Android State Yönetimi

Her ekran için ViewModel kullanılmalıdır.

Örnek UI state:

```kotlin
data class ProductUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null,
    val nameFilter: String = "",
    val codeFilter: String = "",
    val stockStatus: StockStatus? = null
)
```

ViewModel sorumlulukları:

- API çağrılarını repository üzerinden yapmak
- Loading durumunu yönetmek
- Hataları UI'a taşımak
- Liste yenileme ve filtreleme işlemlerini yönetmek

Repository sorumlulukları:

- Retrofit servislerini çağırmak
- DTO ile domain model dönüşümlerini yapmak
- Hata cevaplarını merkezi şekilde ele almak

## 11. Güvenlik Notları

Mobil uygulamada şu noktalara dikkat edilmelidir:

- Şifre düz metin olarak saklanmamalıdır.
- Backend tarafında şifreler hashlenmelidir.
- Login başarılı olunca JWT veya benzeri token dönülmelidir.
- Token Android tarafında `EncryptedSharedPreferences` veya güvenli DataStore yaklaşımıyla saklanmalıdır.
- API HTTPS üzerinden çalışmalıdır.
- Backend CORS ve yetkilendirme ayarları yapılmalıdır.
- Silme işlemlerinde kullanıcıdan onay alınmalıdır.

## 12. Veritabanı ve Backend İçin İyileştirme Önerileri

Mevcut projede mobil geçiş öncesi şu iyileştirmeler yapılmalıdır:

- `Cart` entity adı backend tarafında `Order` veya `Sale` olarak yeniden adlandırılabilir.
- `basket` tablosu kullanıcı bazlı hale getirilmelidir. Aksi halde tüm kullanıcılar aynı sepeti görür.
- `user` ve `users` tablo adı tutarsızlığı kontrol edilmelidir.
- Şifreler veritabanında düz metin tutulmamalıdır.
- Filtreleme sorguları string birleştirme ile değil parametreli sorgularla yapılmalıdır.
- Ürün stok azaltma işlemi sipariş oluşturma sırasında transaction içinde yapılmalıdır.
- Sipariş birden fazla ürün içerecekse `order` ve `order_item` tabloları ayrılmalıdır.

Önerilen daha temiz sipariş şeması:

```text
orders
    id
    customer_id
    date
    note
    total_price

order_items
    id
    order_id
    product_id
    quantity
    unit_price
```

## 13. Geliştirme Sırası

Mobil versiyonu aşağıdaki sırayla geliştirmek daha kontrollü olur.

1. Backend API projesini oluştur.
2. Mevcut MySQL bağlantısını backend'e taşı.
3. Login endpoint'ini yaz.
4. Product CRUD endpoint'lerini yaz ve test et.
5. Customer CRUD endpoint'lerini yaz ve test et.
6. Basket endpoint'lerini yaz ve test et.
7. Order endpoint'lerini yaz ve test et.
8. Android Studio'da Kotlin + Compose proje oluştur.
9. Retrofit bağlantısını kur.
10. Login ekranını geliştir.
11. Ürün listeleme ekranını geliştir.
12. Müşteri listeleme ekranını geliştir.
13. Ürün ve müşteri form ekranlarını geliştir.
14. Sepete ekleme ve sepet listeleme akışını geliştir.
15. Sipariş oluşturma akışını geliştir.
16. Sipariş listeleme ekranını geliştir.
17. Hata durumları, boş liste durumları ve loading durumlarını düzenle.
18. Fiziksel cihaz veya emulator üzerinde test et.

## 14. Android Studio Kurulum Adımları

1. Android Studio'yu aç.
2. `New Project` seç.
3. `Empty Activity` seç.
4. Language olarak `Kotlin` seç.
5. Minimum SDK için en az API 24 seçilebilir.
6. UI için Jetpack Compose kullan.
7. Gradle bağımlılıklarını ekle.

Örnek bağımlılıklar:

```kotlin
dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
```

Sürüm numaraları proje oluşturulduğu tarihte kontrol edilmelidir.

## 15. Lokal Geliştirme İçin API Adresi

Android emulator, bilgisayardaki localhost adresine doğrudan `localhost` ile erişemez.

Emulator için:

```text
http://10.0.2.2:8080/api/
```

Fiziksel cihaz için:

```text
http://BILGISAYARIN_YEREL_IP_ADRESI:8080/api/
```

Örnek:

```text
http://192.168.1.25:8080/api/
```

Fiziksel cihaz kullanırken telefon ve bilgisayar aynı Wi-Fi ağında olmalıdır.

## 16. Test Planı

### Backend Testleri

- Login doğru bilgilerle başarılı olmalı.
- Login yanlış bilgilerle hata dönmeli.
- Ürün ekleme, listeleme, güncelleme ve silme çalışmalı.
- Müşteri ekleme, listeleme, güncelleme ve silme çalışmalı.
- Sepete ürün eklenebilmeli.
- Sepet temizlenebilmeli.
- Sipariş oluşturulunca sipariş listesinde görünmeli.
- Sipariş sonrası stok güncelleniyorsa doğru azalmalı.

### Android Testleri

- Uygulama internet yokken hata mesajı göstermeli.
- Login olmadan ana ekranlara erişilememeli.
- Liste ekranlarında loading durumu görünmeli.
- Boş müşteri veya ürün listesinde boş durum görünmeli.
- Form validasyonları çalışmalı.
- Silme işlemi onay dialogu ile yapılmalı.
- Ekran döndürmede veri kaybolmamalı.

## 17. İlk MVP Kapsamı

İlk mobil sürüm için kapsamı küçük tutmak daha iyi olur.

MVP içinde olmalı:

- Login
- Ürün listeleme
- Ürün ekleme/güncelleme/silme
- Müşteri listeleme
- Müşteri ekleme/güncelleme/silme
- Sepete ürün ekleme
- Sepeti görüntüleme
- Müşteri seçerek sipariş oluşturma
- Sipariş listeleme

MVP dışında bırakılabilir:

- Rol bazlı yetkilendirme
- Detaylı raporlama
- Barkod okuma
- Offline çalışma
- Bildirimler
- PDF fatura

## 18. Sonuç

Bu projenin mobil versiyonu için en sağlıklı yol, mevcut MySQL veritabanını koruyup masaüstü uygulamadaki DAO ve controller mantığını bir backend API'ye taşımaktır. Android uygulaması ise bu API ile Retrofit üzerinden haberleşmelidir.

Bu yaklaşım sayesinde:

- Veritabanı bilgileri mobil uygulamaya gömülmez.
- Güvenlik artar.
- Kod bakımı kolaylaşır.
- Hem masaüstü hem mobil uygulama aynı backend'i kullanabilir.
- İleride web panel veya farklı istemciler eklemek kolaylaşır.

