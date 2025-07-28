package ca.georgiancollege.assignment1_200358108

import android.net.Uri

public class MovieModel{
    constructor(
        title: String,
        director: String,
        rating: String,
        year: String,
        plot: String,
        poster: Uri
    ) {
        this.title = title
        this.director = director
        this.rating = rating
        this.year = year
        this.plot = plot
        this.poster = poster
    }


    constructor(
        title: String,
        year: String,
        poster: Uri,
        imdbId: String
        ){
        this.title = title
        this.year = year
        this.poster = poster
        this.imdbId = imdbId
    }

    public lateinit var title: String
    public lateinit var director: String
    public lateinit var rating: String
    public lateinit var year: String
    public lateinit var plot: String
    public lateinit var poster: Uri
    public lateinit var imdbId: String
}


