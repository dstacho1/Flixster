package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // involves inflating a layout from xml and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // populates data into the item through viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the movie at the position passed in
        Movie movie = movies.get(position);
        //bind the movie data into the view holder
        holder.bind(movie);
    }

    // returns the total count of itme sin the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverView;
        ImageView ivPoster;

        // representation of the row
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverivew);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText((movie.getTitle()));
            tvOverView.setText(movie.getOverview());
            String imageUrl;
            // if phone is in landscape then imageUrl = back drop image
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
                imageUrl = movie.getBackdropPath();
            // else imageUrl = poster image
            else {
                imageUrl = movie.getPosterPath();
            }

            Glide.with(context).load(imageUrl).into(ivPoster);
        }
    }
}
