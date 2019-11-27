package ru.demoncho.getfit.Workout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import ru.demoncho.getfit.R;

/**
 * Created by pvoro on 08.03.2018.
 */

public class nav_workout extends Fragment {
    private static final String TAG = "Workout Fragment";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> first = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_workout, null);
        initImageBitmaps(view);
        return view;
    }

    //Загружает картинки и названия для элементов списка
    private void initImageBitmaps(View view){
        //Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        // без if создает копии каждый раз при нажатии, потом доделаем
        if (mNames.isEmpty() && mImageUrls.isEmpty() && first.isEmpty()){
        mImageUrls.add(R.drawable.biceps);
        mNames.add("Бицепс");

        mImageUrls.add(R.drawable.chest);
        mNames.add("Грудь");

        mImageUrls.add(R.drawable.legs);
        mNames.add("Ноги");

        mImageUrls.add(R.drawable.shoulders);
        mNames.add("Плечи");

        mImageUrls.add(R.drawable.press);
        mNames.add("Пресс");

        mImageUrls.add(R.drawable.back);
        mNames.add("Спина");

        mImageUrls.add(R.drawable.triceps);
        mNames.add("Трицепс");

            first.add(R.drawable.ser_1);
            first.add(R.drawable.ser_5);
            first.add(R.drawable.ser_3);
            first.add(R.drawable.ser_9);
            first.add(R.drawable.ser_18);
            first.add(R.drawable.ser_4);
            first.add(R.drawable.ser_2);
            first.add(R.drawable.ser_8);
            first.add(R.drawable.ser_6);
            first.add(R.drawable.ser_25);
            first.add(R.drawable.ser_13);
            first.add(R.drawable.ser_19);
            first.add(R.drawable.ser_10);
            first.add(R.drawable.ser_22);
            first.add(R.drawable.ser_28);
            first.add(R.drawable.ser_7);
            first.add(R.drawable.ser_26);
            first.add(R.drawable.ser_14);
            first.add(R.drawable.ser_20);
            first.add(R.drawable.ser_11);
            first.add(R.drawable.ser_24);

            mDescription.add("ser1");
            mDescription.add("ser5");
            mDescription.add("ser3");
            mDescription.add("ser9");
            mDescription.add("ser18");
            mDescription.add("ser4");
            mDescription.add("ser2");
            mDescription.add("ser8");
            mDescription.add("ser6");
            mDescription.add("ser25");
            mDescription.add("ser13");
            mDescription.add("ser19");
            mDescription.add("ser10");
            mDescription.add("ser22");
            mDescription.add("ser28");
            mDescription.add("ser7");
            mDescription.add("ser26");
            mDescription.add("ser14");
            mDescription.add("ser20");
            mDescription.add("ser11");
            mDescription.add("ser24");

        }

        initRecyclerView(view);
    }

    //Инициализация списка
    private void initRecyclerView(View view){
       // Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
        RVAdapter_workout adapter = new RVAdapter_workout(this.getActivity(), mNames,mDescription, mImageUrls,first, getFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Упражнения");
    };

}
