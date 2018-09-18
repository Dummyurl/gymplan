package ir.blackd.twitter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

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
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            title.setTypeface(G.typeface);
        }
    }


    public MovieAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
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
                G.currentActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}