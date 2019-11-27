package ru.demoncho.getfit.Equipment;

import android.os.Bundle;
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

import ru.demoncho.getfit.R;

import static ru.demoncho.getfit.MainActivity.PACKAGE_NAME;

/**
 * Created by User on 1/2/2018.
 */

public class item_equipment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_equipment, null);
        getIncomingArguments(view);
        return view;
    }

    private void getIncomingArguments(View view){
        if(!(getArguments().getString("titles").isEmpty())){
            String title = getArguments().getString("titles");
            String item = getArguments().getString("items");
            Integer image = getArguments().getInt("images");
            setContent(view, title, item, image, null);
        }
    }

    private void setContent(View view, String title, String item, Integer image, @Nullable Bundle savedInstanceState){
        ImageView image1 = view.findViewById(R.id.image_equip);
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(image1);

        TextView tv_title = view.findViewById(R.id.item_title_equip);
        tv_title.setText(title);

        TextView tv_metod = view.findViewById(R.id.item_description_equip);
        int resId = getResources().getIdentifier(item, "string", PACKAGE_NAME);
        tv_metod.setText(Html.fromHtml(getString(resId)));

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(title);
    }
}