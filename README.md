🚀 Proje Bilgileri ve Amaç
Kriter	Detay	Puan Değeri
Geliştirici	Enes Konez	-
Öğrenci No	1240505012	-
Port	1989	-
Teknoloji	Sadece Java (java.net.ServerSocket, java.net.Socket, java.io.*)	-
Gereksinim 1	H1 Boyutunda Adı Soyadı	10 Puan
Gereksinim 2	H2 Boyutunda Öğrenci No	10 Puan
Gereksinim 3	Biçimlendirilmiş (Renk, Font, vs.) Biyografi	20 Puan
Genel Kriter	3. Parti Bileşen Kullanmama	Tam Puan

E-Tablolar'a aktar
Projenin Amacı
Projenin temel amacı, bir web tarayıcısından gelen TCP bağlantısını (HTTP isteği) kabul etmek ve tarayıcının beklediği standart HTTP Başlıkları ve HTML içeriğini içeren bir yanıt (Response) göndererek sayfayı ekranda göstermektir.
🛠️ Kurulum ve Çalıştırma
Bu projeyi çalıştırmak için bilgisayarınızda bir Java Geliştirme Kiti (JDK) kurulu olmalıdır.

1. Dosyayı Kaydetme
Verilen kodu, ana sınıf adıyla eşleşecek şekilde BasitWebServer.java olarak kaydedin.

2. Derleme (Compile)
Komut satırını (CMD/Terminal) açın ve dosyanın bulunduğu dizine gidin. Ardından kodu derleyin:javac BasitWebServer.java
3. Çalıştırma (Run)
Derlenen kodu çalıştırın. Sunucu, 1989 portunda bağlantı beklemeye başlayacaktır.java BasitWebServer
Terminalde şu çıktıyı görmelisiniz:

>>> Web Sunucusu Başlatıldı.
>>> 1989 portunda bağlantı bekleniyor...
>>> Terminalde şu çıktıyı görmelisiniz:

>>> Web Sunucusu Başlatıldı.
>>> 1989 portunda bağlantı bekleniyor...
>>> ⚙️ Teknik Yapı
Proje, temel olarak aşağıdaki Socket Programlama adımlarını izler:

Dinleme: ServerSocket sınıfı, belirtilen portta (1989) gelen bağlantıları dinler.

Kabul Etme: serverSocket.accept() metodu ile bir tarayıcıdan gelen bağlantı kabul edilir ve bir Socket nesnesi (clientSocket) oluşturulur.

Yanıt Akışı: clientSocket.getOutputStream() kullanılarak veri gönderme akışı (OutputStream) elde edilir.

HTTP Protokolü:

Önce HTTP/1.1 200 OK ve Content-Type: text/html gibi HTTP Başlıkları gönderilir.

Ardından bu başlıkları ayıran zorunlu bir boş satır (\r\n\r\n) gönderilir.

Son olarak, tarayıcının ekranda göstereceği HTML içeriği gönderilir.

Sürekli Çalışma: Tüm bu işlemler bir while(true) döngüsü içinde gerçekleştiğinden, sunucu birden fazla bağlantıyı art arda kabul edebilir.
