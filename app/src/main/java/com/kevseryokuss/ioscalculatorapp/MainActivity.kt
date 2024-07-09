package com.kevseryokuss.ioscalculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var sonucGoster: EditText
    lateinit var buton0: Button
    lateinit var buton1: Button
    lateinit var buton2: Button
    lateinit var buton3: Button
    lateinit var buton4: Button
    lateinit var buton5: Button
    lateinit var buton6: Button
    lateinit var buton7: Button
    lateinit var buton8: Button
    lateinit var buton9: Button
    lateinit var butonArtiEksi: Button
    lateinit var butonBolu: Button
    lateinit var butonCarpi: Button
    lateinit var butonArti: Button
    lateinit var butonEksi: Button

    var operator = "*" // Operatörü varsayılan olarak * (çarpma) olarak ayarladım.
    var eskiSayi = ""
    var yeniOperator = true
    var eksiDurumu = false // Eksi durumunu kontrol etmek için değişken ekledik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Arayüz öğelerini bağlama işlemleri
        sonucGoster = findViewById(R.id.sonucGoster)
        buton0 = findViewById(R.id.buton0)
        buton1 = findViewById(R.id.buton1)
        buton2 = findViewById(R.id.buton2)
        buton3 = findViewById(R.id.buton3)
        buton4 = findViewById(R.id.buton4)
        buton5 = findViewById(R.id.buton5)
        buton6 = findViewById(R.id.buton6)
        buton7 = findViewById(R.id.buton7)
        buton8 = findViewById(R.id.buton8)
        buton9 = findViewById(R.id.buton9)
        butonArtiEksi = findViewById(R.id.butonArtiEksi)
        butonBolu = findViewById(R.id.butonBolu)
        butonCarpi = findViewById(R.id.butonCarpi)
        butonArti = findViewById(R.id.butonArti)
        butonEksi = findViewById(R.id.butonEksi)
    }

    fun butonSayiTikla(view: View) {
        try {
            if (yeniOperator) {
                sonucGoster.setText("") // Eğer yeni operatör ise, ekranı temizle
            }
            yeniOperator = false
            val butonSec = view as Button
            var butonTikDeger: String = sonucGoster.text.toString()

            // Hangi butona tıklandığını kontrol et
            when (butonSec.id) {
                R.id.buton0 -> butonTikDeger += "0"
                R.id.buton1 -> butonTikDeger += "1"
                R.id.buton2 -> butonTikDeger += "2"
                R.id.buton3 -> butonTikDeger += "3"
                R.id.buton4 -> butonTikDeger += "4"
                R.id.buton5 -> butonTikDeger += "5"
                R.id.buton6 -> butonTikDeger += "6"
                R.id.buton7 -> butonTikDeger += "7"
                R.id.buton8 -> butonTikDeger += "8"
                R.id.buton9 -> butonTikDeger += "9"
            }

            // Sonuç göstericiye değeri yaz
            sonucGoster.setText(butonTikDeger)
        } catch (ex: Exception) {
            // Hata durumunda kullanıcıya bilgi ver
            Toast.makeText(this, "Buton tıklama sırasında bir hata oluştu: ${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun butonArtiEksiTikla() {
        try {
            var sayi: String = sonucGoster.text.toString()

            if (sayi.isNotEmpty()) {
                if (sayi.startsWith("-")) {
                    sayi = sayi.substring(1) // Eğer sayı negatifse eksi işaretini kaldır
                } else {
                    sayi = "-$sayi" // Eğer sayı pozitifse eksi işareti ekle
                }
            }

            // Eksi durumu kontrolü güncelle
            eksiDurumu = !eksiDurumu

            // Sonuç göstericiye değeri yaz
            sonucGoster.setText(sayi)
        } catch (ex: Exception) {
            // Hata durumunda kullanıcıya bilgi ver
            Toast.makeText(this, "Artı Eksi butonuna tıklama sırasında bir hata oluştu: ${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun butonOperatorTikla(view: View) {
        try {
            val butonSec = view as Button
            when (butonSec.id) {
                R.id.butonBolu -> {
                    operator = "÷" // Bölme operatörü seçildi
                }
                R.id.butonCarpi -> {
                    operator = "X" // Çarpma operatörü seçildi
                }
                R.id.butonEksi -> {
                    operator = "-" // Çıkarma operatörü seçildi
                }
                R.id.butonArti -> {
                    operator = "+" // Toplama operatörü seçildi
                }
            }
            // Eski sayıyı kaydet ve yeni operatörü aktif hale getir
            eskiSayi = sonucGoster.text.toString()
            yeniOperator = true
        } catch (ex: Exception) {
            // Hata durumunda kullanıcıya bilgi ver
            Toast.makeText(this, "Operatör seçimi sırasında bir hata oluştu: ${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun butonEsittirTikla() {
        try {
            val yeniSayi = sonucGoster.text.toString()
            var sonucSayisi: Double? = null
            when (operator) {
                "÷" -> {
                    // Bölme işlemi yap
                    sonucSayisi = eskiSayi.toDouble() / yeniSayi.toDouble()
                }
                "X" -> {
                    // Çarpma işlemi yap
                    sonucSayisi = eskiSayi.toDouble() * yeniSayi.toDouble()
                }
                "-" -> {
                    // Çıkarma işlemi yap
                    sonucSayisi = eskiSayi.toDouble() - yeniSayi.toDouble()
                }
                "+" -> {
                    // Toplama işlemi yap
                    sonucSayisi = eskiSayi.toDouble() + yeniSayi.toDouble()
                }
            }
            // Sonucu ekrana yaz ve yeni operatörü aktif hale getir
            sonucGoster.setText(sonucSayisi.toString())
            yeniOperator = true
        } catch (ex: Exception) {
            // Hata durumunda kullanıcıya bilgi ver
            Toast.makeText(this, "Hesaplama sırasında bir hata oluştu: ${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun butonAcTikla() {
        // Ekranı sıfırla ve yeni operatörü aktif hale getir
        sonucGoster.setText("0")
        yeniOperator = true
    }

    fun butonYuzdeTikla() {
        try {
            // Yüzde hesaplama işlemi yap
            var sayi: Double = sonucGoster.text.toString().toDouble() / 100
            sonucGoster.setText(sayi.toString())
            yeniOperator = true
        } catch (ex: Exception) {
            // Hata durumunda kullanıcıya bilgi ver
            Toast.makeText(this, "Yüzde hesaplama sırasında bir hata oluştu: ${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
