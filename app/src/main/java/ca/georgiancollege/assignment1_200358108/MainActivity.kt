package ca.georgiancollege.assignment1_200358108

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment1_200358108.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieList: List<MovieModel>
    private lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        movieList = ArrayList<MovieModel>()


        Log.i("tag", "before click")
        binding.searchButton.setOnClickListener(View.OnClickListener {
            Log.i("tag", "pre-function")
                viewModel.fetchSearchResults(binding.searchEditText.text.toString())
            Log.i("tag", "post-function")
        })

        viewModel.getSearchedMoviesData().observe(this) { movies ->
            movieList = movies
            myAdapter.updateItems(movieList)
        }

        var layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setLayoutManager(layoutManager)
        Log.i("tag", "after click")

        myAdapter = MyAdapter( movieList, this)
        binding.recyclerView.adapter = myAdapter


    }
}