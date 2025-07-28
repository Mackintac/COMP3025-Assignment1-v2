package ca.georgiancollege.assignment1_200358108

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import ca.georgiancollege.assignment1_200358108.databinding.ActivityMainBinding
import ca.georgiancollege.assignment1_200358108.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = intent.getStringExtra("title")
        if(title != null){
            viewModel.search(title)

            viewModel.getMovieData().observe(this) { movieModel ->
                binding.detailsImageView.setImageURI(movieModel.poster)
                binding.detailsTitleTextView.setText(movieModel.title)
                binding.detailsDirectorTextView.setText(movieModel.director)
                binding.detailsRatingTextView.setText(movieModel.rating)
                binding.detailsYearTextView.setText(movieModel.year)
                binding.detailsPlotTextView.setText(movieModel.plot)
            }
        }

    }
}