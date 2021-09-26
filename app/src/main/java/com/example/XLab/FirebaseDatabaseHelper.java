package com.example.XLab;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceLabapp;
    private List<Appointmentlab> appLab = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Appointmentlab> appointmentlabs,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceLabapp=mDatabase.getReference("Appointmentlab");
    }

    public void readLabApp(final DataStatus dataStatus){
        mReferenceLabapp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appLab.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Appointmentlab appointmentlab = keyNode.getValue(Appointmentlab.class);
                    appLab.add(appointmentlab);
                }
                dataStatus.DataIsLoaded(appLab,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateLabApp(String key,Appointmentlab applab,final DataStatus dataStatus){
        mReferenceLabapp.child(key).setValue(applab)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteLabApp(String key,final DataStatus dataStatus){
        mReferenceLabapp.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }

}
