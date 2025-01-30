package com.zeynepsudeyilmaz.sozlukuygulamasi

class Kelimelerdao {

    fun tumKelimeler(vt: VeritabaniYardimcisi) : ArrayList<Kelimeler>{
        val kelimelerListe = ArrayList<Kelimeler>()

        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM kelimeler", null)

        while (cursor.moveToNext()){
            val kelime = Kelimeler(cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id"))
            , cursor.getString(cursor.getColumnIndexOrThrow("ingilizce"))
            , cursor.getString(cursor.getColumnIndexOrThrow("turkce")))
            kelimelerListe.add(kelime)
        }

        return kelimelerListe
    }
    fun aramaYap(vt: VeritabaniYardimcisi, aramaKelime:String) : ArrayList<Kelimeler>{
        val kelimelerListe = ArrayList<Kelimeler>()

        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM kelimeler WHERE ingilize like '%$aramaKelime%'", null)

        while (cursor.moveToNext()){
            val kelime = Kelimeler(cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id"))
                , cursor.getString(cursor.getColumnIndexOrThrow("ingilizce"))
                , cursor.getString(cursor.getColumnIndexOrThrow("turkce")))
            kelimelerListe.add(kelime)
        }

        return kelimelerListe
    }
}