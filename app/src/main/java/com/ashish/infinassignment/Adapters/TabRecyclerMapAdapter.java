package com.ashish.infinassignment.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashish.infinassignment.MapsActivity;
import com.ashish.infinassignment.R;

import java.util.ArrayList;

/**
 * Created by Ashish Yadav on 28-Dec-16.
 */

public class TabRecyclerMapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private ArrayList<String> fnameList,femailList,phoneList,officePhoneList,latitudeList,longitudeList;
    private int position1;

    public TabRecyclerMapAdapter(Activity context, ArrayList<String> fnameList, ArrayList<String> femailList,
                                 ArrayList<String> phoneList, ArrayList<String> officePhoneList, ArrayList<String> latitudeList, ArrayList<String> longitudeList){
        this.context = context;
        this.fnameList = fnameList;
        this.femailList = femailList;
        this.phoneList = phoneList;
        this.officePhoneList = officePhoneList;
        this.latitudeList = latitudeList;
        this.longitudeList = longitudeList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabRecyclerMapAdapter.TabViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_single_map_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TabRecyclerMapAdapter.TabViewHolder) holder).name.setText(fnameList.get(position));
        ((TabRecyclerMapAdapter.TabViewHolder) holder).number.setText(phoneList.get(position));
        ((TabRecyclerMapAdapter.TabViewHolder) holder).email.setText(femailList.get(position));
    }

    @Override
    public int getItemCount() {
        return fnameList.size();
    }
    class TabViewHolder extends RecyclerView.ViewHolder {
        private TextView name,number,email;
        private CardView card;

        public TabViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tab_contact_name_map);
            number = (TextView)itemView.findViewById(R.id.tab_contact_number_map);
            email = (TextView)itemView.findViewById(R.id.tab_email_map);
            card  = (CardView) itemView.findViewById(R.id.single_card_map);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context, MapsActivity.class);
                    intent.putExtra("name",fnameList.get(getAdapterPosition()));
                    intent.putExtra("number",phoneList.get(getAdapterPosition()));
                    intent.putExtra("email",femailList.get(getAdapterPosition()));
                    intent.putExtra("latitude",latitudeList.get(getAdapterPosition()));
                    intent.putExtra("longitude",longitudeList.get(getAdapterPosition()));

                    Log.d("name before",fnameList.get(position1));
                    Log.d("number before",phoneList.get(position1));
                    // Log.d("position ",+position1+" pos");
                    context.startActivity(intent);
                    context.finish();

                }
            });


        }


    }
}
