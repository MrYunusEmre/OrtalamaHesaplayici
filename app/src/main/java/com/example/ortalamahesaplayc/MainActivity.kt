package com.example.ortalamahesaplayc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {

    private val DERSLER = arrayOf("Matematik","Türkçe","Fizik","Edebiyat","Algoritma","Tarih")
    private var tumDerslerinBilgileri = ArrayList<Dersler>(5)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,DERSLER)

        etDersAd.setAdapter(adapter)

        if (scrollLinear.childCount <= 0) {
            btnHesapla.visibility = View.INVISIBLE
        } else {
            btnHesapla.visibility = View.VISIBLE
        }


        btnDersEkle.setOnClickListener {

            if (!etDersAd.text.isEmpty()) {

                //xml dosyasını java koduna donustrp koda eklycez
                var inflater = LayoutInflater.from(this)
                //var inflater2 = layoutInflater
                // var inflater3 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                //inflater3.inflate()

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)

                yeniDersView.etYeniDersAd.setAdapter(adapter)

                //statik alandan kullanıcı girdigi degerler

                var dersAdı = etDersAd.text.toString()
                var dersKredi = spnDersKredi.selectedItem.toString()
                var dersHarfi = spnDersNot.selectedItem.toString()

                yeniDersView.etYeniDersAd.setText(dersAdı)
                yeniDersView.YenispnDersKredi.setSelection(getSpinnerIndex(spnDersKredi, dersKredi))
                yeniDersView.YenispnDersNot.setSelection((getSpinnerIndex(spnDersNot, dersHarfi)))


                yeniDersView.btnDersSil.setOnClickListener {

                    scrollLinear.removeView(yeniDersView)

                    if (scrollLinear.childCount <= 0) {
                        btnHesapla.visibility = View.INVISIBLE
                    } else {
                        btnHesapla.visibility = View.VISIBLE
                    }

                }

                scrollLinear.addView(yeniDersView)

                btnHesapla.visibility = View.VISIBLE

                sıfırla()
            } else {
                FancyToast.makeText(this,"Ders Adını Giriniz!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
               // Toast.makeText(this, "Ders Adını Giriniz.", Toast.LENGTH_LONG).show()

            }


        }
    }

    private fun sıfırla() {
        etDersAd.setText("")
        spnDersKredi.setSelection(0)
        spnDersNot.setSelection(0)
    }



    fun getSpinnerIndex(spn: Spinner, aranacakDeger: String): Int {


        for (i in 0..spn.count) {
            if (spn.getItemAtPosition(i).equals(aranacakDeger)) {
                return i;
            }
        }
        return -1;
    }

    fun ortalamaHesapla(view: View) {

        var toplamNot = 0.0
        var toplamKredi = 0.0

        for(i in 0..scrollLinear.childCount-1){

            var tekSatır = scrollLinear.getChildAt(i)

            var geciciDers = Dersler(tekSatır.etYeniDersAd.text.toString(),
                ((tekSatır.YenispnDersKredi.selectedItemPosition)+1).toString(),
                 tekSatır.YenispnDersNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)
        }

        for(currentDers in tumDerslerinBilgileri){
            toplamNot += harfiNotaCevir(currentDers.dersHarfNotu) * (currentDers.dersKredi.toDouble())
            toplamKredi += currentDers.dersKredi.toDouble()

        }
        FancyToast.makeText(this,"Ortalama : ${toplamNot/toplamKredi}",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
        //Toast.makeText(this,"Ortalama : ${toplamNot/toplamKredi}",Toast.LENGTH_LONG).show()
        tumDerslerinBilgileri.clear()

    }

    fun harfiNotaCevir(gelenHarf:String) : Double{

        var deger = 0.0

        when(gelenHarf){
            "AA" -> deger = 4.0
            "BA" -> deger = 3.5
            "BB" -> deger = 3.0
            "CB" -> deger = 2.5
            "CC" -> deger = 2.0
            "DC" -> deger = 1.5
            "DD" -> deger = 1.0
            "FF" -> deger = 0.0
        }

        return deger

    }
}
