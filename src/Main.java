import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    // Ana programın başladığı metot.
    public static void main(String[] args) {

        // --- PROJE AYARLARI ---
        final int PORT = 1989; // Hocamızın istediği port numarası

        try {

            // 1. Sunucu Soketini Başlatma (Dinleme Başlıyor)
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println(">>> Web Sunucusu Başlatıldı.");
            System.out.println(">>> http://localhost:" + PORT + " adresinde bağlantı bekleniyor...");

            // 2. Sonsuz Döngü: Sunucunun Sürekli Çalışması
            while (true) {

                // 3. Bağlantı Kabul Etme (Program burada durur ve bekler)
                Socket clientSocket = serverSocket.accept();
                System.out.println("\n--- Yeni Bağlantı Kabul Edildi ---");

                // --- İstemciden Gelen İsteği Okuma ---
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String requestLine = input.readLine();
                if (requestLine != null) {
                    System.out.println("İstemciden Gelen İlk İstek Satırı: " + requestLine);
                }

                // --- HTML İçeriğini Oluşturma (20 Puanlık Biçimlendirilmiş Kısım) ---
                String htmlBody = "<html><head><title>Web Sunucusu Projesi - Enes Konez</title>" +
                        "<style>" +
                        "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #e6e6fa; margin: 50px; }" +
                        "h1 { color: #800000; border-bottom: 2px solid #800000; padding-bottom: 5px;}" + // H1: Ad Soyad
                        "h2 { color: #004d40; font-style: italic; }" + // H2: Öğrenci No
                        "p { color: #333333; font-size: 1.1em; line-height: 1.6; background-color: #ffffff; padding: 15px; border-radius: 8px; box-shadow: 2px 2px 5px #aaaaaa;}" +
                        "</style></head><body>" +

                        // H1 Boyutunda Adı Soyadı - 10 Puan
                        "<h1>Enes Konez</h1>" +

                        // H2 Boyutunda Öğrenci No - 10 Puan
                        "<h2>Öğrenci No: 1240505012</h2>" +

                        // Uygun Biçimlendirilmiş Biyografi - 20 Puan
                        "<p>" +
                        "Merhabalar! Bu sayfa, Java programlama dilinde <span style='font-weight: bold;'>socket programlama</span> tekniği kullanılarak " +
                        "tarafımdan geliştirilen basit bir web sunucusu üzerinden yayınlanmaktadır. " +
                        "Hiçbir harici kütüphane kullanmadan, tamamen temel Java sınıfları ile " +
                        "bir web tarayıcısının anlayacağı formatta bir <span style='color: #cc0000;'>HTTP yanıtı</span> üretmek, bu projenin temel amacıdır." +
                        "</p>" +
                        "</body></html>";


                // --- HTTP Başlıklarını Oluşturma (Yanıt Zarfı) ---
                String httpHeaders = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=utf-8\r\n" +
                        "Content-Length: " + htmlBody.getBytes("UTF-8").length + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n"; // Zorunlu boş satır

                // --- Yanıtı İstemciye Gönderme ---
                String fullResponse = httpHeaders + htmlBody;

                OutputStream output = clientSocket.getOutputStream();

                output.write(fullResponse.getBytes("UTF-8"));
                output.flush();

                // 4. Bağlantıyı Kapatma
                clientSocket.close();
                System.out.println("--- Bağlantı Kapatıldı ---");

            }

        } catch (Exception e) {
            // Hata Durumunda Bilgi Verme
            System.err.println("Sunucu beklenmedik bir hata ile durdu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
