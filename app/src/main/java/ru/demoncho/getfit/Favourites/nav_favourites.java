package ru.demoncho.getfit.Favourites;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ru.demoncho.getfit.Food.item_food;
import ru.demoncho.getfit.R;
import ru.demoncho.getfit.Workout.RVAdapter_workout;
import ru.demoncho.getfit.training.item_training;

/**
 * Created by pvoro on 08.03.2018.
 */

public class nav_favourites extends Fragment implements View.OnClickListener{
    public Button mFavTVFood;
    public String title_food;
    public String item_food;
    public String title_training;
    public String item_training;
    public Button mFavTVTraining;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_favourite, null);
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
        initFavourites(view);
        return view;
    }

    //Загружает картинки и названия для элементов списка
    private void initFavourites(View view){
        mFavTVFood = (Button) view.findViewById(R.id.fav_food_link);
        mFavTVTraining = (Button) view.findViewById(R.id.fav_training_link);

        try {
            FileInputStream fileInputStream = view.getContext().openFileInput("food.dat");
            String str = getFileContent(fileInputStream, "UTF-8");
            if (!str.isEmpty()){
                String []ss = str.split("/");
                mFavTVFood.setText(ss[0]);
            mFavTVFood.setOnClickListener(this);
            title_food = ss[0];
            item_food = ss[1];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = view.getContext().openFileInput("training.dat");
            String str1 = getFileContent(fileInputStream, "UTF-8");
            if (!str1.isEmpty()) {
                String[] ss1 = str1.split("/");
                mFavTVTraining.setText(ss1[0]);
                mFavTVTraining.setOnClickListener(this);
                title_training = ss1[0];
                item_training = ss1[1];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getFileContent(
            FileInputStream fis,
            String          encoding ) throws IOException
    {
        try( BufferedReader br =
                     new BufferedReader( new InputStreamReader(fis, encoding )))
        {
            StringBuilder sb = new StringBuilder();
            String line;
            while(( line = br.readLine()) != null ) {
                sb.append( line );
                sb.append( '\n' );
            }
            return sb.toString();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Избранное");
    }

    @Override
    public void onClick(View view) {
        FragmentManager f_manager = getFragmentManager();
        if (view.getId() == R.id.fav_food_link){
            Fragment argumentFragment = new item_food();
            Bundle data = new Bundle();//create bundle instance
            data.putString("titles", title_food);
            data.putString("items", item_food.trim());
            argumentFragment.setArguments(data);
            f_manager.beginTransaction().replace(R.id.screen_area, argumentFragment).addToBackStack("my_fav_food").commit();
        }
        else if (view.getId() == R.id.fav_training_link){
            Fragment argumentFragment = new item_training();
            Bundle data = new Bundle();//create bundle instance
            data.putString("titles", title_training);
            data.putString("items", item_training.trim());
            argumentFragment.setArguments(data);
            f_manager.beginTransaction().replace(R.id.screen_area, argumentFragment).addToBackStack("my_fav_training").commit();
        }
        // return true if you don't want it handled by any other touch/click events after this
    }
}
