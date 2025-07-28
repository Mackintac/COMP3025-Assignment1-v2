package ca.georgiancollege.assignment1_200358108

import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Callback
import okhttp3.MediaType.Companion.get
import okhttp3.MediaType.Companion.toMediaType


class ApiClient() {

    private var client: OkHttpClient = OkHttpClient()
    private var json: MediaType = "application/json; charset=utf-8".toMediaType()

    fun get(url: String, callback: Callback) {

        var request: Request = Request.Builder().url(url).build()
        Log.i("tag", "Click")
        client.newCall(request).enqueue(callback)
        Log.i("tag", "get after enqueue")
    }
}