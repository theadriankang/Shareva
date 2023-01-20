package com.example.shareva.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareva.Model.ForYouItems;
import com.example.shareva.R;

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
    }

    @Override
    public int getItemCount() {
        return forYouItemsArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgView_item;

        private TextView txtView_item, txtView_itemDesc, txtView_availability;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            //ImageView
            imgView_item = itemView.findViewById(R.id.imgView_item);

            //TextView
            txtView_item = itemView.findViewById(R.id.txtView_item);
            txtView_itemDesc = itemView.findViewById(R.id.txtView_itemDesc);
            txtView_availability = itemView.findViewById(R.id.txtView_availability);

        }
    }
}
