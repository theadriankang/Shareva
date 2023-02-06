package com.example.shareva.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareva.R;

import java.util.ArrayList;

public class WatchAdapter extends RecyclerView.Adapter<WatchAdapter.CardViewHolder>{

    private Context context;
    private ArrayList watch, size, type, desc, verification;

    public WatchAdapter(Context context, ArrayList watch, ArrayList size, ArrayList type, ArrayList desc, ArrayList verification) {
        this.context = context;
        this.watch = watch;
        this.size = size;
        this.type = type;
        this.desc = desc;
        this.verification = verification;
    }

    @NonNull
    @Override
    public WatchAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_watch, parent, false);

        return new WatchAdapter.CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchAdapter.CardViewHolder holder, int position) {

        holder.txtView_title.setText(String.valueOf(watch.get(position)));
        holder.txtView_desc.setText(String.valueOf(desc.get(position)));

        holder.txtView_availability.setText(String.valueOf(verification.get(position)));

        if (holder.txtView_availability.getText().toString().equals("Verify"))
        {
            holder.txtView_availability.setTextColor(Color.parseColor("#00FF00"));
        }
        else
        {
            holder.txtView_availability.setTextColor(Color.parseColor("#FF0000"));
        }

    }

    @Override
    public int getItemCount() {
        return watch.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_watch;
        private TextView txtView_title, txtView_desc, txtView_availability;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            txtView_title = itemView.findViewById(R.id.txtView_title);
            txtView_desc = itemView.findViewById(R.id.txtView_desc);

            txtView_availability = itemView.findViewById(R.id.txtView_availability);
        }
    }
}
