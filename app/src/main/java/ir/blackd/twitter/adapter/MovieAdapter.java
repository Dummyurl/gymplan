package ir.blackd.twitter.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.blackd.twitter.G;
import ir.blackd.twitter.model.Movie;
import ir.blackd.twitter.ProductActivity;
import ir.blackd.twitter.R;

/**
 * Created by Diamond Android on 12/19/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public ImageView imgProduct;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            imgProduct = (ImageView) view.findViewById(R.id.imgWd);
            title.setTypeface(G.typeface);
        }
    }


    public MovieAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.title.setTypeface(G.morvarid);
     //   holder.genre.setText(movie.getGenre());
      //  holder.year.setText(movie.getYear());
        if (!movie.getImage().isEmpty()) {
        //    Picasso.with(G.context).load(movie.getImage()).into(holder.imgProduct);
            Log.i("MOL",movie.getImage());
        }else {
          //  Picasso.with(G.context).load(R.drawable.ic_post_no_image).into(holder.imgProduct);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(G.context,""+position,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(G.currentActivity,ProductActivity.class);
                intent.putExtra("title",movie.getTitle()); //Optional parameters
                intent.putExtra("type",movie.getGenre()); //Optional parameters
                intent.putExtra("main",movie.getMian()); //Optional parameters
                intent.putExtra("help",movie.getHelp()); //Optional parameters
                intent.putExtra("desc",movie.getYear()); //Optional parameters
                intent.putExtra("pic",movie.getImage()); //Optional parameters
                G.currentActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}