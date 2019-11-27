package ru.demoncho.getfit.MainMenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ru.demoncho.getfit.R;
import ru.demoncho.getfit.Workout.item_workout;

/**
 * Created by Дмитрий on 10.03.2018.
 */

public class nav_mainmenu extends Fragment implements View.OnTouchListener{
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> first = new ArrayList<>();
    private List<Integer> mfirst = new ArrayList<>();
    private List<Integer> msecond = new ArrayList<>();
    private List<Integer> mthird = new ArrayList<>();

    private List<String> dfirst = new ArrayList<>();
    private List<String> dsecond = new ArrayList<>();
    private List<String> dthird = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_mainmenu, null);
        ImageView iv = (ImageView) view.findViewById (R.id.image);
        initObjects(view);
        iv.setOnTouchListener (this);

        return view;
    }

    //Загружает картинки и названия для элементов списка
    private void initObjects(View view){
        // без if создает копии каждый раз при нажатии, потом доделаем
        if (mNames.isEmpty()){

            mNames.add("Бицепс");

            mNames.add("Грудь");

            mNames.add("Ноги");

            mNames.add("Плечи");

            mNames.add("Пресс");

            mNames.add("Спина");

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

            mfirst = first.subList(0,7);
            msecond = first.subList(7,14);
            mthird = first.subList(14,21);

            dfirst = mDescription.subList(0,7);
            dsecond = mDescription.subList(7,14);
            dthird= mDescription.subList(14,21);
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Главное меню");
    }

    public boolean onTouch (View v, MotionEvent ev)
    {
        boolean handledHere = false;

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;			// resource id of the next image to display

        // If we cannot find the imageView, return.
        ImageView imageView = (ImageView) v.findViewById (R.id.image);
        if (imageView == null) return false;

        // When the action is Down, see if we should show the "pressed" image for the default image.
        // We do this when the default image is showing. That condition is detectable by looking at the
        // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
        Integer tagNum = (Integer) imageView.getTag ();
        int currentResource = (tagNum == null) ? R.drawable.topbody : tagNum.intValue ();

        // Now that we know the current resource being displayed we can handle the DOWN and UP events.

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                handledHere = true;
                break;

            case MotionEvent.ACTION_UP :
                // On the UP, we do the click action.
                // The hidden image (image_areas) has three different hotspots on it.
                // The colors are red, blue, and yellow.
                // Use image_areas to determine which region the user touched.
                int touchColor = getHotspotColor (R.id.image_areas, evX, evY);

                // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
                // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
                // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
                // varying pixel density.
                ColorTool ct = new ColorTool ();
                int tolerance = 10;
                nextImage = R.drawable.topbody_back;
                if (ct.closeMatch (Color.rgb(255, 245, 59), touchColor, tolerance)){
                    LoadItem(0);
            }
                else if (ct.closeMatch (Color.BLACK, touchColor, tolerance)){
                    LoadItem(1);
                }
                else if (ct.closeMatch (Color.RED, touchColor, tolerance)){
                    LoadItem(2);
                }
                else if (ct.closeMatch (Color.rgb(62, 181, 241), touchColor, tolerance)){
                    LoadItem(3);
                }
                else if (ct.closeMatch (Color.GREEN, touchColor, tolerance)){
                    LoadItem(4);
                }
                else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)){
                    LoadItem(5);
                }
                else if (ct.closeMatch (Color.rgb(165, 60, 165), touchColor, tolerance)){
                    LoadItem(6);
                }

                // If the next image is the same as the last image, go back to the default.
                // toast ("Current image: " + currentResource + " next: " + nextImage);
                /*if (currentResource == nextImage) {
                    nextImage = R.drawable.topbody;
                }
                handledHere = true;
                break;*/

            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage > 0) {
                imageView.setImageResource (nextImage);
                imageView.setTag (nextImage);
            }
        }
        return handledHere;
    }



    /**
 */
// More methods

    /**
     * Get the color from the hotspot image at point x-y.
     *
     */

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) getView().findViewById (hotspotId);
        if (img == null) {

            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }


    /*
    public boolean onTouch (View v, MotionEvent ev) {

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                ImageView img = (ImageView) v.findViewById(R.id.body_image);
                img.setDrawingCacheEnabled(true);
                Bitmap imgbmp = Bitmap.createBitmap(img.getDrawingCache());
                img.setDrawingCacheEnabled(false);
                int pxl = imgbmp.getPixel(evX, evY);
                int r = Color.red(pxl);
                int g = Color.green(pxl);
                int b = Color.blue(pxl);
                if (r == 255 && g == 245) {  // грудь

                }
                else if(r == )



                break;
        }

        return true;
    }*/

    public void LoadItem(Integer position) {
        Fragment argumentFragment = new item_workout();
        Bundle data = new Bundle();//create bundle instance
        // data.putString("image_url", mImages.get(position));
        data.putString("image_name", mNames.get(position));

        data.putString("dfirst",dfirst.get(position));
        data.putString("dsecond",dsecond.get(position));
        data.putString("dthird",dthird.get(position));

        data.putInt("first",mfirst.get(position));
        data.putInt("second",msecond.get(position));
        data.putInt("third",mthird.get(position));



        argumentFragment.setArguments(data);
        FragmentManager f_manager = getFragmentManager();
        f_manager.beginTransaction().replace(R.id.screen_area, argumentFragment).addToBackStack("my_fragment_main").commit();

    }

    }
