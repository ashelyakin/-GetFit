package ru.demoncho.getfit.Food;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.demoncho.getfit.R;
import ru.demoncho.getfit.Workout.RVAdapter_workout;

/**
 * Created by pvoro on 08.03.2018.
 */

public class nav_food extends Fragment {
    private ArrayList<String> mItems_titles = new ArrayList<>();
    private ArrayList<String> mItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_food, null);
        initImageBitmaps(view);
        return view;
    }

    //Загружает картинки и названия для элементов списка
    private void initImageBitmaps(View view){
        // без if создает копии каждый раз при нажатии, потом доделаем
        if (mItems.isEmpty()){
            mItems_titles.add(getString(R.string.Massa_title));
            mItems.add("Massa_description");

            mItems_titles.add(getString(R.string.Atkinson_title));
            mItems.add("Atkinson_description");

            mItems_titles.add(getString(R.string.Kefir_title));
            mItems.add("Kefir_description");

            mItems_titles.add(getString(R.string.Ogranichenie_title));
            mItems.add("Ogranichenie_description");

            mItems_titles.add(getString(R.string.Japan_title));
            mItems.add("Japan_description");

            mItems_titles.add(getString(R.string.Razdelnoe_title));
            mItems.add("Razdelnoe_description");

            mItems_titles.add(getString(R.string.Drobnoe_title));
            mItems.add("Drobnoe_description");
            //mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        }
        initRecyclerView(view);
    }

    //Инициализация списка
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
        RVAdapter_food adapter = new RVAdapter_food(this.getActivity(), mItems_titles, mItems, getFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Диеты");
    }
}
