package ir.blackd.twitter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

/**
 * Created by Diamond Android on 12/18/2016.
 */
public class CategoryFragment extends Fragment {


    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.category, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);

        mAdapter = new MovieAdapter(G.movieList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(G.context,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        //Picasso.with(context).load(url)
               // .resize(50, 50)
               // .centerCrop()
                //.into(imageView)
       // G.prepareMovieData();
       return view;
    }


}
