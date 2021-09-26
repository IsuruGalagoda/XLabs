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

public class FirebaseDBHelper {
    private FirebaseDatabase dbref;
    DatabaseReference mReferenceOrders;
    private List<PharmacyD>orders =  new ArrayList<>();

    public interface DataStatus{

        void DataIsLoaded(List<PharmacyD>orders,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDBHelper() {
        dbref =FirebaseDatabase.getInstance();
        mReferenceOrders=dbref.getReference("PharmacyD");
    }
    //View details--------------------
    public void readOrders(final DataStatus dataStatus){
        mReferenceOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();
                List<String> keys= new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    PharmacyD order=keyNode.getValue(PharmacyD.class);
                    orders.add(order);
                }
                dataStatus.DataIsLoaded(orders,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateOrder(String key, PharmacyD order,final DataStatus dataStatus){
        mReferenceOrders.child(key).setValue(order)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteOrder(String key, final DataStatus dataStatus){
        mReferenceOrders.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
