package ca.georgiancollege.assignment1_200358108

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    lateinit var imageView: ImageView
    lateinit var title: TextView
    lateinit var plot: TextView

    init {
        imageView = itemView.findViewById(R.id.posterImageView)
        title = itemView.findViewById(R.id.titleTextView)
        plot = itemView.findViewById(R.id.plotTextView)
    }
}