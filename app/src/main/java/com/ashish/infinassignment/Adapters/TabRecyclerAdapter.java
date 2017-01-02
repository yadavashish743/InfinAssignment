package com.ashish.infinassignment.Adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.ashish.infinassignment.R;

import java.util.ArrayList;

/**
 * Created by Ashish Yadav on 03-Nov-16.
 */

public class TabRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private ArrayList<String> nameList, numberList;
    private int position1;

    public TabRecyclerAdapter(Activity context, ArrayList<String> nameList, ArrayList<String> numberList) {
        this.context = context;
        this.nameList = nameList;
        this.numberList = numberList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_single_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("holder Position",holder.getAdapterPosition()+"ashish");
        ((TabViewHolder) holder).nameOnCard.setText(nameList.get(position));
        ((TabViewHolder) holder).cardNumber.setText(numberList.get(position));

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class TabViewHolder extends RecyclerView.ViewHolder {
        private TextView nameOnCard,cardNumber;
        private CardView card;

        public TabViewHolder(View itemView) {
            super(itemView);
            nameOnCard = (TextView) itemView.findViewById(R.id.tab_contact_name);
            cardNumber = (TextView)itemView.findViewById(R.id.tab_number);
            card  = (CardView) itemView.findViewById(R.id.single_card);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Nothing will come up!",Toast.LENGTH_SHORT).show();

                }
            });


        }


    }

}
