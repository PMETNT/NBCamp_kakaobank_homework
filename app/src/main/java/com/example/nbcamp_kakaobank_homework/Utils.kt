package com.example.nbcamp_kakaobank_homework


import android.content.Context
import com.example.nbcamp_kakaobank_homework.image_data.Document
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Utils {

    fun setListPref(
        context: Context,
        key: String,
        values: MutableList<Document>

    ) {
//        val gson = Gson()
//        val json = gson.toJson(values)

        val prefs = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val editor = prefs.edit()


        var makeGson = GsonBuilder().create()

        var listType : TypeToken<MutableList<Document>> = object :TypeToken<MutableList<Document>>() {}

        var json = makeGson.toJson(values, listType.type)

        editor.clear()

        editor.putString(key, json)
        editor.apply()
    }

    fun getListPref(
        context: Context,
        key: String
    ): MutableList<Document> {
        val prefs = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val json = prefs.getString(key, "{}")

        var makeGson = GsonBuilder().create()
        var listType : TypeToken<MutableList<Document>> = object :TypeToken<MutableList<Document>>() {}

        var storedData : MutableList<Document> = makeGson.fromJson(json, listType)
//        val gson = Gson()
//
//        val storedData: MutableList<Document> = gson.fromJson(
//            json,
//            object : TypeToken<MutableList<Document>>() {}.type
//        )

        return storedData
    }

}