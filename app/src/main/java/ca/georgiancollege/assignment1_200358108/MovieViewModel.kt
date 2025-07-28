package ca.georgiancollege.assignment1_200358108

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.georgiancollege.assignment1_200358108.databinding.ActivityMainBinding
import okhttp3.Callback
import okhttp3.Call
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MovieViewModel(private var binding: ActivityMainBinding) : ViewModel() {


    private var movieData: MutableLiveData<MovieModel> = MutableLiveData()

    private var searchResultData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    public fun getMovieData(): MutableLiveData<MovieModel> {
        return movieData
    }
    fun getSearchedMoviesData(): MutableLiveData<List<MovieModel>>  = searchResultData

    fun fetchSearchResults() {
        var apiString: String = "http://www.omdbapi.com/?s="
        var apiKey: String = "&apikey=6ad0eb6"
        var userString: String = binding.searchEditText.text.toString()

        val searchString: String = apiString + userString + apiKey

        var apiClient: ApiClient = ApiClient()
        apiClient.get(
            searchString,
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("tag", "request failed")
                }

                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(call: Call, response: Response) {
                    Log.i("tag", "request passed")

                    assert(response.body != null)
                    var responseData: String = response.body!!.string()
                    Log.i("tag", responseData)

                    try {
                        val json = JSONObject(responseData)
                        val searchArray = json.optJSONArray("Search")

                        val movieList = mutableListOf<MovieModel>()
                        for (i in 0 until searchArray.length()) {
                            val item = searchArray.getJSONObject(i)
                            val movie = MovieModel(
                                title = item.getString("Title"),
                                year = item.getString("Year"),
                                poster = item.getString("Poster").toUri(),
                                imdbId = item.getString("imdbID"),
                            )
                            movieList.add(movie)
                        }
                        searchResultData.postValue(movieList)
                } catch (e: JSONException){
                }
                }
            })
    }
    fun search(){
        Log.i("tag", "Click")

        var apiString: String = "http://www.omdbapi.com/?t="
        var apiKey: String = "&apikey=6ad0eb6"
        var userString: String = binding.searchEditText.text.toString()

        val searchString: String = apiString + userString + apiKey

        var apiClient: ApiClient = ApiClient()
        apiClient.get(
            searchString,
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("tag", "request failed")
                }

                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(call: Call, response: Response) {
                    Log.i("tag", "request passed")

                    assert(response.body != null)
                    var responseData: String = response.body!!.string()
                    Log.i("tag", responseData)

                    var json: JSONObject? = null
                        try {
                            json = JSONObject(responseData)
                            val movie = MovieModel(
                                title = json.getString("Title"),
                                director= json.getString("Director"),
                                rating = json.getString("Rated"),
                                year = json.getString("Year"),
                                plot= json.getString("Plot"),
                                poster = json.getString("Poster").toUri())

                            movieData.postValue(movie)

                        } catch (e: JSONException){
                            throw RuntimeException()
                        }
                }

            },
        )
    }
}