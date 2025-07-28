package ca.georgiancollege.assignment1_200358108

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var movies: List<MovieModel>, private var context: Context) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var movieView: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout,parent, false)

        var myViewHolder: MyViewHolder = MyViewHolder(movieView)

        return myViewHolder
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var movie: MovieModel = movies.get(position)
        holder.title.setText(movie.title)
        holder.plot.setText(movie.year)
        holder.imageView.setImageURI(movie.poster)
    }
    fun updateItems(newMovies: List<MovieModel>){
        this.movies = newMovies
        notifyDataSetChanged()
    }
}