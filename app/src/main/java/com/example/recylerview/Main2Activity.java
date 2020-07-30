package com.example.recylerview;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.DateFormat;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Main2Activity extends AppCompatActivity {
EditText name,rollno,decp;
ImageView imageup;
int count=10000;
    FirebaseAuth mAuth;
String nam,rol,des,imageurl;

    private Uri filePath;


    FirebaseStorage storage;
    StorageReference storageReference;
    private final int PICK_IMAGE_REQUEST = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=(EditText) findViewById(R.id.Name);
        //rollno=(EditText) findViewById(R.id.Rollno);
        decp=(EditText) findViewById(R.id.descp);
        imageup=(ImageView) findViewById(R.id.imageView2);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();






        imageup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."),
                        PICK_IMAGE_REQUEST);

            }
        });


    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);


        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                getContentResolver(),
                                filePath);
                imageup.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }





    @RequiresApi(api = Build.VERSION_CODES.N)
    public void push(View v)
    { uploadimage();
    pushtodatabse();







    }
public  void pushtodatabse(){
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Hyderabad");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String nam = user.getDisplayName();
    //nam=name.getText().toString();

    //rol=rollno.getText().toString();
    des=decp.getText().toString();


    mAuth = FirebaseAuth.getInstance();



    String  currentDateTimeString = DateFormat.getDateTimeInstance()
            .format(new Date());

    Infom info=new Infom(nam,currentDateTimeString,des,imageurl);
    String key = myRef.push().getKey();
    myRef.child(key).setValue(info);

}





    private void uploadimage() {
        if (filePath != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("Rec");

            // Code for showing progressDialog while uploading
           final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                                    while(!uriTask.isComplete());
                                    Uri urlImage=uriTask.getResult();
                                    imageurl=urlImage.toString();
                                   progressDialog.dismiss();
                                    Toast
                                            .makeText(Main2Activity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    pushtodatabse();

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                           progressDialog.dismiss();
                            Toast
                                    .makeText(Main2Activity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int)progress + "%");
                                }
                            });
        }
    }
}
