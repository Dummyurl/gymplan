package ir.blackd.twitter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.blackd.twitter.G;
import ir.blackd.twitter.R;
import ir.blackd.twitter.adapter.MovieAdapter;


/**
 * Created by Diamond Android on 12/18/2016.
 */
public class OneFragment extends Fragment {

    public RecyclerView recyclerView;
    public static MovieAdapter mAdapter;
    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new MovieAdapter(G.movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(G.context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

     //   G.prepareMovieData();


       return view;
    }



    @Override
    public void onResume() {
        super.onResume();

        mAdapter.notifyDataSetChanged();
    }
}
