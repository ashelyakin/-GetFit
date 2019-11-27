package ru.demoncho.getfit.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.demoncho.getfit.Food.RVAdapter_food;
import ru.demoncho.getfit.R;

/**
 * Created by pvoro on 08.03.2018.
 */

public class nav_training extends Fragment {
    private ArrayList<String> mItems_titles = new ArrayList<>();
    private ArrayList<String> mItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_training, null);
        initImageBitmaps(view);
        return view;
    }

    private void initImageBitmaps(View view){
        if(mItems.isEmpty()){
            mItems_titles.add(getString(R.string.Massa_title1));
            mItems.add("Massa_description1");

            mItems_titles.add(getString(R.string.Massa_title2));
            mItems.add("Massa_description2");

            mItems_titles.add(getString(R.string.Dry_title));
            mItems.add("Dry_description");

            mItems_titles.add(getString(R.string.BaseW_title));
            mItems.add("BaseW_description");

            mItems_titles.add(getString(R.string.BaseM_title));
            mItems.add("BaseM_description");
        }
        initRecyclerView(view);
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
        RVAdapter_training adapter = new RVAdapter_training(this.getActivity(), mItems_titles, mItems, getFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Программы тренировок");
    }

}


