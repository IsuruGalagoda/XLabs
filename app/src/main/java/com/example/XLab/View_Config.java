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

public class View_Config {


    private Context mContext;
    private OrderAdapter mOrderAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<PharmacyD> orders, List<String> keys){
        mContext = context;
        mOrderAdapter= new OrderAdapter(orders,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mOrderAdapter);

    }

    class OrdersView extends RecyclerView.ViewHolder {
        private TextView PharmacyNo;
        private TextView PharmacyName;
        private TextView Address;
        private TextView PhoneNum;
        private TextView O_Date;

        private String key;


        public OrdersView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.order_list_recycle,parent,false));
            PharmacyNo=(TextView) itemView.findViewById(R.id.txtNo);
            PharmacyName=(TextView) itemView.findViewById(R.id.txtName);
            Address=(TextView) itemView.findViewById(R.id.txtAddress);
            PhoneNum=(TextView) itemView.findViewById(R.id.txtPhNum);
            O_Date=(TextView) itemView.findViewById(R.id.txtDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(mContext,Update_Pharmacy_order.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Pnumber",PharmacyNo.getText().toString());
                    intent.putExtra("Pname",PharmacyName.getText().toString());
                    intent.putExtra("Address",Address.getText().toString());
                    intent.putExtra("Phonenum",PhoneNum.getText().toString());
                    intent.putExtra("Date",O_Date.getText().toString());

                    mContext.startActivity(intent);
                }
            });

        }
        public void bind(PharmacyD order,String key)
        {
            PharmacyNo.setText(order.getPno());
            PharmacyName.setText(order.getPname());
            Address.setText(order.getAddress());
            PhoneNum.setText(String.valueOf(order.getPhno()));
            O_Date.setText(order.getDate());


            this.key=key;
        }
    }
    class OrderAdapter extends RecyclerView.Adapter<OrdersView>{
        private List<PharmacyD> mOrderList;
        private List<String> mKeys;

        public OrderAdapter(List<PharmacyD> mOrderList, List<String> mKeys) {
            this.mOrderList = mOrderList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public OrdersView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OrdersView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull OrdersView holder, int position) {
            holder.bind(mOrderList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mOrderList.size();
        }
    }
}
