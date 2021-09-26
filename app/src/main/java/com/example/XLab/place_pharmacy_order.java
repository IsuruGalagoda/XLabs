package com.example.XLab;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class place_pharmacy_order extends AppCompatActivity {
    EditText Pno,Pname,Address,phno;
    Button placeO,selectImg;
    DatabaseReference dbref;
    ImageView img;
    PharmacyD phod;
    Uri imageUri;
    String url;
    private TextView mDisplayDate;

    private static final  int IMAGE_REQUEST=2;
    private static final String Tag = "place_pharmacy_order";

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public static  String key ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_pharmacy_order);

        Pno=findViewById(R.id.etxtpno);
        Pname=findViewById(R.id.etxtpname);
        Address=findViewById(R.id.etxtaddress);
        phno=findViewById(R.id.etxtphno);

        selectImg=findViewById(R.id.imageCbtn);
        img=findViewById(R.id.imgViewUp);

        placeO=findViewById(R.id.plcebtn);
        phod=new PharmacyD();

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        placeO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("PharmacyD");


                try {
                    if (TextUtils.isEmpty(Pno.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Pharmacy Number", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(Pname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Your Name", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(Address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Your Address", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(phno.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Your Phone Number", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(mDisplayDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Insert the order date", Toast.LENGTH_LONG).show();
                    else {


                        //String id=dbref.push().getKey();
                        key = dbref.push().getKey();

                        phod.setPno(Pno.getText().toString().trim());
                        phod.setPname(Pname.getText().toString().trim());
                        phod.setAddress(Address.getText().toString().trim());
                        phod.setPhno(Integer.parseInt(phno.getText().toString().trim()));
                        phod.setDate(mDisplayDate.getText().toString().trim());
                        dbref.child(key).setValue(phod);
                        uploadImage();

                      //  dbref.child(id).setValue(phod);

                        Toast.makeText(getApplicationContext(),"Successfully ADDED",Toast.LENGTH_SHORT).show();
                        clearControld();

                        openActivity();



                    }


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDisplayDate=(TextView) findViewById(R.id.dateTxt);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal= Calendar.getInstance();
                int year= cal.get(Calendar. YEAR);
                int month= cal.get(Calendar. MONTH);
                int day= cal.get(Calendar. DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        place_pharmacy_order.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.show();

            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                Log.d(Tag,"onDateSet: date:" + i + "/" +i1+ "/" +i2);
                String date= i + "/" +i1+ "/" +i2 ;
                mDisplayDate.setText(date);
            }
        };

    }

    private void openImage() {
        Intent intent=new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            imageUri=data.getData();

            img.setImageURI(imageUri);



        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this) ;
        pd.setMessage("Uploading...");
        pd.show();
        if(imageUri != null)
        {
            final StorageReference fileRef= FirebaseStorage.getInstance().getReference().child("PharmacyD")
                    .child(System.currentTimeMillis() + "." +getFileExtension(imageUri)); //"upload"
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url=uri.toString();
                            Log.d("DowloadUrl" ,url);
                            FirebaseDatabase.getInstance().getReference().child("PharmacyD").child(key).child("url").setValue(uri.toString());
                            //phod.setUrl(url);
                        }
                    });
                }
            });
        }
        pd.dismiss();





    }

    private void clearControld(){
        Pno.setText("");
        Pname.setText("");
        phno.setText("");
        Address.setText("");
        mDisplayDate.setText("");

    }
    public void openActivity(){

        Intent intent = new Intent(this,Order_List.class);
        startActivity(intent);
    }
}