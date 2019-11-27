package ru.demoncho.getfit.Workout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ru.demoncho.getfit.R;

import static ru.demoncho.getfit.MainActivity.PACKAGE_NAME;

/**
 * Created by User on 1/2/2018.
 */

public class item_workout extends Fragment {

    private static final String TAG = "item_workout";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d(TAG, "allo");
        View view = inflater.inflate(R.layout.item_workout, null);
        getIncomingArguments(view);
        return view;
    }

    private void getIncomingArguments(View view){
        //Log.d(TAG, "getIncomingArguments: checking for incoming intents.");

        if(!(getArguments().getString("image_name").isEmpty())){
           // Log.d(TAG, "getIncomingArguments: found Arguments extras.");

           // String imageUrl = getArguments().getString("image_url");
            String imageName = getArguments().getString("image_name");
            Integer first = getArguments().getInt("first");
            Integer second = getArguments().getInt("second");
            Integer third = getArguments().getInt("third");
            String dfirst = getArguments().getString("dfirst");
            String dsecond = getArguments().getString("dsecond");
            String dthird = getArguments().getString("dthird");

            setImage(view, imageName, first,second,third,dfirst,dsecond,dthird,null);
        }
    }


    private void setImage(View view, String imageName, Integer first, Integer second, Integer third, String dfirst ,String dsecond,String dthird ,@Nullable Bundle savedInstanceState){
        //Log.d(TAG, "setImage: setting te image and name to widgets.");

        ImageView image = view.findViewById(R.id.image);
        Glide.with(this)
                .asGif()
                .load(first)
                .into(image);

        image = view.findViewById(R.id.image1);
        Glide.with(this)
                .asGif()
                .load(second)
                .into(image);

        image = view.findViewById(R.id.image2);
        Glide.with(this)
                .asGif()
                .load(third)
                .into(image);

        TextView tv_metod = view.findViewById(R.id.image_description);
        int resId = getResources().getIdentifier(dfirst, "string", PACKAGE_NAME);
        tv_metod.setText(Html.fromHtml(getString(resId)));

        tv_metod = view.findViewById(R.id.image_description1);
        resId = getResources().getIdentifier(dsecond, "string", PACKAGE_NAME);
        tv_metod.setText(Html.fromHtml(getString(resId)));

        tv_metod = view.findViewById(R.id.image_description2);
        resId = getResources().getIdentifier(dthird, "string", PACKAGE_NAME);
        tv_metod.setText(Html.fromHtml(getString(resId)));

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(imageName);

    }



}


















