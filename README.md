
# Projenin çalıştırılması ve sistem gereksinimleri

  

Proje indirilip cmd ekranı yönetici olarak çalıştırıldıktan sonra java -version komutunu girerek 17 veya üstü bir versiyon olduğunu kontrol etmeniz gerekiyor.JDK 17 versiyonuyla yazıldığı için jdk17 yüklü olması gerekmekte.
Sonrasında "java -jar "indirilendosyakonumu\vetproject.jar" komutu girilir ufak bir yükleme sonrası proje çalışır hale gelmektedir.



  

Default url : http://localhost:8093

Default url'e giriş yapıldığında karşımıza login page çıkıyor. Giriş sayfasında mail ve şifre ile giriş yaptıktan sonra Owner ekleme sayfasına geçiş yapılıyor. Owner ekledikten sonra sistemin bütün fonksiyonunu görebileceğiniz bir sayfaya yönlendirileceksiniz.

  

# Seçimler ve gerekçeleri

  

-Database olarak H2 in memory database kullanmayı seçtim. Proje jar olarak gönderileceği ve bir IDE'den bağımsız olacağı için H2'nun daha uygun olacağını düşündüm.

Test kısmı için sadece service layer'ı test ettim. Junit ve Mockito kullandım.

Daha iyi bir görünüm için jquery datatable kullanmayı tercih ettim.
