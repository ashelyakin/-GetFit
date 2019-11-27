package ru.demoncho.getfit.training;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.demoncho.getfit.R;

import static ru.demoncho.getfit.MainActivity.PACKAGE_NAME;

/**
 * Created by Дмитрий on 12.03.2018.
 */

public class item_training extends Fragment implements View.OnClickListener {

    private Button mFavButton;
    private TextView tv_title;
    private String title;
    private String item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_training, null);
        getIncomingArguments(view);
        return view;
    }

    private void getIncomingArguments(View view){
        if(!(getArguments().getString("titles").isEmpty())){
            String title1 = getArguments().getString("titles");
            String item1 = getArguments().getString("items");
            title = title1;
            item = item1;
            setContent(view, null);
        }
    }

    private void setContent(final View view, @Nullable Bundle savedInstanceState){
        tv_title = view.findViewById(R.id.item_title);
        tv_title.setText(title);

        TextView tv_metod = view.findViewById(R.id.item_description);
        int resId = getResources().getIdentifier(item, "string", PACKAGE_NAME);
        tv_metod.setText(Html.fromHtml(getString(resId)));

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(title);

        mFavButton = (Button) view.findViewById(R.id.button_fav_training);
        mFavButton.setOnClickListener(this);

        try {
            FileInputStream fileInputStream = view.getContext().openFileInput("training.dat");
            String str = getFileContent(fileInputStream, "UTF-8");
            if (str.startsWith(title)){
                mFavButton.setText("Добавлено в избранное");
                mFavButton.setEnabled(false);
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
    public void onClick(View view) {
        if (view.getId() == R.id.button_fav_training){
            uploadFileInFireBaseStorage(view);
        }
    }

    public void uploadFileInFireBaseStorage (View view){
        File file = new File(view.getContext().getFilesDir(), "training.dat");

        FileOutputStream outputStream;
        try {
            outputStream = view.getContext().openFileOutput("training.dat", view.getContext().MODE_PRIVATE);
            outputStream.write(title.getBytes());
            outputStream.write("/".getBytes());
            outputStream.write(item.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference mStorageRef = storageRef.child("food.dat");
            Uri file_uri = Uri.fromFile(file);
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType(title).setCustomMetadata("title", title)
                    .setCustomMetadata("item", item)
                    .build();
            UploadTask uploadTask = mStorageRef.putFile(file_uri, metadata);

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                }
            });
        }

        mFavButton.setText("Добавлено в избранное");
        mFavButton.setEnabled(false);
    }
}
