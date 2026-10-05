# Android 3D Scanner

Bu proje, Android cihazlardan kamera ve sensör verilerini kullanarak temel bir 3D tarama uygulaması için başlangıç iskeleti sunar.

## Özellikler
- Kamera önizleme
- Taranın başlatma/durdurma butonları
- Kamera frame işleme için ayrılmış yönlendirilmiş mimari
- ARCore/Depth API entegrasyonu için hazırlık
- 3D model çıktısı için genişletilebilir yapı

## Hedef mimari
- CameraX: kamera akışı ve önizleme
- ARCore: derinlik verisi ve hareket takibi için hazırlık
- Nokta bulutu / mesh işleme: sonraki aşama için soyut katman
- OBJ / PLY kaydı: taranmış verinin dışa aktarımı

## Geliştirme ortamı
1. Android Studio Ladybug veya daha yeni sürüm
2. JDK 17
3. Android SDK 34
4. Android 8.0+ cihaz (önerilir)

## Çalıştırma
1. Projeyi Android Studio'da açın
2. `app` modülünü çalıştırın
3. Kotlin/Gradle bağımlılıklarının indirilmesini bekleyin
4. cihaz üzerinde uygulamayı çalıştırın

## Gelecek adım
- ARCore Depth API ile nokta bulutu üretimi
- mesh ve OBJ/PLY dosya çıktısı
- tarama deneyimini iyileştirme (yakınlaştırma, otomatik tarama, kayıt)
