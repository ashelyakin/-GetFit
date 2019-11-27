package ru.demoncho.getfit.Equipment;

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

public class nav_equipment extends Fragment {
    private ArrayList<String> mItems_titles = new ArrayList<>();
    private ArrayList<String> mItems = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_equipment, null);
        initImageBitmaps(view);
        return view;
    }

    //Загружает картинки и названия для элементов списка
    private void initImageBitmaps(View view){
        // без if создает копии каждый раз при нажатии, потом доделаем
        if (mItems.isEmpty()){

            mItems_titles.add(getString(R.string.Velo_title));
            mItems.add("Vello_description");
            mImageUrls.add(R.drawable.velo);

            mItems_titles.add(getString(R.string.Beg_title));
            mItems.add("Beg_description");
            mImageUrls.add(R.drawable.beg);

            mItems_titles.add(getString(R.string.Ellipt_title));
            mItems.add("Ellipt_description");
            mImageUrls.add(R.drawable.elipt);

            mItems_titles.add(getString(R.string.Stepper_title));
            mItems.add("Stepper_description");
            mImageUrls.add(R.drawable.stepper);

            mItems_titles.add(getString(R.string.Grebnoy_title));
            mItems.add("Grebnoy_description");
            mImageUrls.add(R.drawable.grebnoy);

            mItems_titles.add(getString(R.string.Tyaga_title));
            mItems.add("Tyaga_description");
            mImageUrls.add(R.drawable.tyaga);

            mItems_titles.add(getString(R.string.Razgib_title));
            mItems.add("Razgib_description");
            mImageUrls.add(R.drawable.razgib);
        }
        initRecyclerView(view);
    }

    //Инициализация списка
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
        RVAdapter_equipment adapter = new RVAdapter_equipment(this.getActivity(), mItems_titles, mItems, mImageUrls, getFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Спортивные снаряды");
    }
}
