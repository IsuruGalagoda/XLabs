package com.example.XLab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {

    private Context Context;
    private LabAdapter LabAdapter;

    public void setConfig(RecyclerView recyclerView,Context context,List<Appointmentlab> applab,List<String>keys){
        Context=context;
        LabAdapter=new LabAdapter(applab,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(LabAdapter);
    }

    class LabAppView extends RecyclerView.ViewHolder {
        private TextView Name;
        private TextView TestID;
        private TextView Date;
        private TextView Time;
        private TextView Email;
        private TextView contNo;

        private String key;

        public LabAppView(ViewGroup parent){
            super(LayoutInflater.from(Context).
                    inflate(R.layout.lab_list_recycle,parent,false));

             Name = (TextView) itemView.findViewById(R.id.name_textView);
            TestID = (TextView) itemView.findViewById(R.id.chemIdTextView);
             Date = (TextView) itemView.findViewById(R.id.date_textView);
             Time = (TextView) itemView.findViewById(R.id.time_textView);
             Email = (TextView) itemView.findViewById(R.id.email_textView);
             contNo = (TextView) itemView.findViewById(R.id.contactNum_textView);

             //navigate to another activity that displays the clicked section details
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     Intent intent= new Intent(Context,DisplayLabBkDetails.class);

                     intent.putExtra("key",key);
                     intent.putExtra("name",Name.getText().toString());
                     intent.putExtra("chemId",TestID.getText().toString());
                     intent.putExtra("date",Date.getText().toString());
                     intent.putExtra("time",Time.getText().toString());
                     intent.putExtra("email",Email.getText().toString());
                     intent.putExtra("contactNumb",contNo.getText().toString());

                     Context.startActivity(intent);

                 }
             });

        }

        public void bind(Appointmentlab appointmentlab,String key){
             Name.setText(appointmentlab.getPatientName());
            TestID.setText(""+appointmentlab.getTestId());
             Date.setText(appointmentlab.getDate());
             Time.setText(appointmentlab.getTime());
             Email.setText(appointmentlab.getEmail());
             contNo.setText(""+appointmentlab.getContactNumber());
             this.key = key;
        }

    }
        class LabAdapter extends RecyclerView.Adapter<LabAppView>{

        private List<Appointmentlab> AppLab;
        private List<String> Keys;

            public LabAdapter(List<Appointmentlab> mAppLab, List<String> mKeys) {
                this.AppLab = mAppLab;
                this.Keys = mKeys;
            }

            @NonNull
            @Override
            public LabAppView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new LabAppView(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull LabAppView holder, int position) {

                holder.bind(AppLab.get(position),Keys.get(position));

            }

            @Override
            public int getItemCount() {
                return AppLab.size();
            }
        }
}
