package ru.demoncho.getfit.Workout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.demoncho.getfit.R;

/**
 * Created by User on 1/1/2018.
 */

public class RVAdapter_workout extends RecyclerView.Adapter<RVAdapter_workout.ViewHolder>{

    private static final String TAG = "RVAdapter_workout";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();

    private List<Integer> mfirst;
    private List<Integer> msecond;
    private List<Integer> mthird;

    private List<String> dfirst;
    private List<String> dsecond;
    private List<String> dthird;

    private Context mContext;
    public FragmentManager f_manager; // нужно для смены фрагментов между собой

    public RVAdapter_workout(Context context, ArrayList<String> imageNames,ArrayList<String> imageDescroption ,ArrayList<Integer> images,ArrayList<Integer> first, FragmentManager f_manager) {
        mImageNames = imageNames;
        mImages = images;

        mContext = context;
        mfirst = first.subList(0,7);
        msecond = first.subList(7,14);
        mthird = first.subList(14,21);

        dfirst = imageDescroption.subList(0,7);
        dsecond = imageDescroption.subList(7,14);
        dthird= imageDescroption.subList(14,21);

        this.f_manager = f_manager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // действия при клике на элемент
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       // Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

       holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
                // Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

               // Log.d(TAG, "commit: " + mImageNames.get(position));

                Fragment argumentFragment = new item_workout();
                Bundle data = new Bundle();//create bundle instance
               // data.putString("image_url", mImages.get(position));
                data.putString("image_name", mImageNames.get(position));

                data.putString("dfirst",dfirst.get(position));
                data.putString("dsecond",dsecond.get(position));
                data.putString("dthird",dthird.get(position));

                data.putInt("first",mfirst.get(position));
                data.putInt("second",msecond.get(position));
                data.putInt("third",mthird.get(position));

                argumentFragment.setArguments(data);
                f_manager.beginTransaction().replace(R.id.screen_area, argumentFragment).addToBackStack("my_fragment").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}















