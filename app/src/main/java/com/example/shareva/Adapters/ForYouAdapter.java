package com.example.shareva.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareva.Model.ForYouItems;
import com.example.shareva.R;
import com.example.shareva.WatchDetail_Activity;

import java.util.ArrayList;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.CardViewHolder> {

    private ArrayList<ForYouItems> forYouItemsArrayList;
    private Context mcontext;

    public ForYouAdapter(Context mcontext, ArrayList<ForYouItems> forYouItemsArrayList) {
        this.forYouItemsArrayList = forYouItemsArrayList;
        this.mcontext = mcontext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private ForYouAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(ForYouAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mcontext;
    }

    @NonNull
    @Override
    public ForYouAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_items, parent, false);

        return new ForYouAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForYouAdapter.CardViewHolder holder, int position) {

        holder.txtView_item.setText(forYouItemsArrayList.get(position).getItemName());
        holder.txtView_itemDesc.setText(forYouItemsArrayList.get(position).getItemDesc());
        holder.imgView_item.setImageResource(forYouItemsArrayList.get(position).getImage());
        holder.txtView_availability.setText(forYouItemsArrayList.get(position).getAvailability());

        holder.cv_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, WatchDetail_Activity.class);
                int pos = holder.getAdapterPosition();
                intent.putExtra("Watch Name", forYouItemsArrayList.get(pos).getItemName());
                intent.putExtra("Watch Desc", forYouItemsArrayList.get(pos).getItemDesc());
                intent.putExtra("Watch Availability", forYouItemsArrayList.get(pos).getAvailability());
                intent.putExtra("Watch More Desc", forYouItemsArrayList.get(pos).getMoreDesc());

                intent.putExtra("Watch Image", forYouItemsArrayList.get(pos).getImage());

                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forYouItemsArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_watch;
        private ImageView imgView_item;
        private TextView txtView_item, txtView_itemDesc, txtView_availability;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            //CardView
            cv_watch = itemView.findViewById(R.id.cv_watch);

            //ImageView
            imgView_item = itemView.findViewById(R.id.imgView_item);

            //TextView
            txtView_item = itemView.findViewById(R.id.txtView_item);
            txtView_itemDesc = itemView.findViewById(R.id.txtView_itemDesc);
            txtView_availability = itemView.findViewById(R.id.txtView_availability);

        }
    }
}
